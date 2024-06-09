package com.example.yappi.util;

import com.example.yappi.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Service
public class Md5HashService {
    private static final Logger log = LoggerFactory.getLogger(Md5HashService.class);
    public String toHashMd5(String path) {
        log.info("toHashMd5 starts");
        //   String videoFilePath = "path/to/video/file"; // Specify the path to your video file
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try (DigestInputStream dis = new DigestInputStream(new FileInputStream(path), md)) {
                while (dis.read() != -1) {
                    // Read the entire file to calculate the MD5 hash
                }
                byte[] digest = md.digest();

                for (byte b : digest) {
                    result.append(String.format("%02x", b));
                }
                System.out.println("MD5 Hash: " + result.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }
}

