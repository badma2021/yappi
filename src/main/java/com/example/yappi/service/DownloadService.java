package com.example.yappi.service;

import com.example.yappi.util.Md5HashService;
import lombok.AllArgsConstructor;
import com.example.yappi.models.VideoReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.yappi.repository.VideoReferenceRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class DownloadService {

    private final VideoReferenceRepository videoReferenceRepository;
    private final Md5HashService md5HashService;
    private static final Logger log = LoggerFactory.getLogger(DownloadService.class);

    @Transactional
    public String downloadByUrl() {
        String fileLink = "https://cdn-st.rutubelist.ru/media/87/43/b11df3f344d0af773aac81e410ee/fhd.mp4";

        try {

            String oppath = "/opt/fhd.mp4";
            URL link = new URL(fileLink);
            InputStream ins = link.openStream();
            ReadableByteChannel chh = Channels.newChannel(link.openStream());
            FileOutputStream fos = new FileOutputStream(new File(oppath));
            fos.getChannel().transferFrom(chh, 0, Long.MAX_VALUE);
            fos.close();
            chh.close();
            VideoReference videoReference = VideoReference.builder().
                    link(fileLink).
                    hash(md5HashService.toHashMd5(oppath)).
                    build();
            videoReferenceRepository.save(videoReference);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        log.info("ok");


        return "ok";
    }

    @Transactional
    public String updateHash() {
        String fileLink = "";

        try {

            String oppath = "/opt/fhd.mp4";
            URL link = new URL(fileLink);
            InputStream ins = link.openStream();
            ReadableByteChannel chh = Channels.newChannel(link.openStream());
            FileOutputStream fos = new FileOutputStream(new File(oppath));
            fos.getChannel().transferFrom(chh, 0, Long.MAX_VALUE);
            fos.close();
            chh.close();
            VideoReference videoReference = VideoReference.builder().
                    hash(md5HashService.toHashMd5(oppath)).
                    build();
            videoReferenceRepository.save(videoReference);
            deleteFile(oppath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        log.info("ok");


        return "ok";
    }

    private void deleteFile(String oppath) {

        // create an object of Path
        Path pathOfFile
                = Paths.get(oppath);

        // delete File if file exists
        try {

            boolean result
                    = Files.deleteIfExists(pathOfFile);

            if (result)
                System.out.println("File is deleted");
            else
                System.out.println("File does not exists");
        } catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
