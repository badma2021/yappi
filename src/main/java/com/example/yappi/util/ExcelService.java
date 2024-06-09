package com.example.yappi.util;

import com.example.yappi.models.VideoReference;
import com.example.yappi.repository.VideoReferenceRepository;
import com.example.yappi.service.DownloadService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@Service
@AllArgsConstructor
public class ExcelService {
    //    @Value("${spring.jpa.hibernate.jdbc.batch_size:1000}")
    private static final Logger log = LoggerFactory.getLogger(ExcelService.class);

    private final VideoReferenceRepository videoReferenceRepository;

    public void readExcel() throws IOException {
        int batchSize = 10;
        String excelFilePath = "/opt/yappy_hackaton_2024_400k.xlsx";
        int count = 0;

        List<VideoReference> videoReferencesList = new ArrayList<>();

        try (FileInputStream excelFile = new FileInputStream(new File(excelFilePath));) {
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            XSSFSheet worksheet = workbook.getSheetAt(0);
            Iterator<Row> rows = worksheet.iterator();
            // int len = worksheet.getPhysicalNumberOfRows() - 1;
            if (rows.hasNext()) {
                rows.next();
            }
            //  log.info("len: {}", len);
            while (rows.hasNext()) {

                log.info("row");
                var vr = new VideoReference();

                Row row = rows.next();
                Cell linkCell = row.getCell(0);

                Cell descriptionCell = row.getCell(1);
                String link = getCellValueAsString(linkCell);
                log.info("excel link {}", link);
                String description = getCellValueAsString(descriptionCell);
                vr.setLink(link);
                vr.setDescription(description);
                videoReferencesList.add(vr);
                count++;
                if (count % batchSize == 0) {
                    videoReferenceRepository.saveAll(videoReferencesList);
                    videoReferencesList.clear();

                } else if (!rows.hasNext() && count % batchSize > 0) {
                    videoReferenceRepository.saveAll(videoReferencesList);
                }
            }
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}


