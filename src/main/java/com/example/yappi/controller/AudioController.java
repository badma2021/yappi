package com.example.yappi.controller;

import com.example.yappi.service.AudioToTextConverter;
import com.example.yappi.service.DownloadService;
import com.example.yappi.service.VideoToAudioConverter;
import com.example.yappi.util.ExcelService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AudioController {

    private final AudioToTextConverter audioToTextConverter;
    private final VideoToAudioConverter videoToAudioConverter;
    private final DownloadService downloadService;
    private final ExcelService csvService;

    private static final Logger log = LoggerFactory.getLogger(AudioController.class);

    @GetMapping("/processAudioTrack")
    public ResponseEntity<?> getTextFromAudio() throws IOException {
        audioToTextConverter.processAudio();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/processVideoTrack")
    public ResponseEntity<?> getAudioTrackFromVideo() throws IOException {
        videoToAudioConverter.processVideo();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/downloadVideo")
    public ResponseEntity<?> getVideo() throws IOException {
      downloadService.downloadByUrl();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/updateHash")
    public ResponseEntity<?> updateHash() throws IOException {
        log.info("updateHash starts");
        downloadService.updateHash();
        log.info("updateHash finish");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/downloadExcelList")
    public ResponseEntity<?> getCSV() throws IOException {

        csvService.readExcel();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
