package com.example.yappi.service;


import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AudioToTextConverter {

    private static final Logger logger = LoggerFactory.getLogger(AudioToTextConverter.class);
    String audioFilePath = "https://cdn-st.rutubelist.ru/media/87/43/b11df3f344d0af773aac81e410ee/fhd.mp4";

public void processAudio() throws IOException {
    try (SpeechClient speechClient = SpeechClient.create()) {
        Path path = Paths.get(audioFilePath);
        byte[] audioBytes = Files.readAllBytes(path);
        ByteString audioByteString = ByteString.copyFrom(audioBytes);

        // Configure recognition request
        RecognitionConfig config =
                RecognitionConfig.newBuilder()
                        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                        .setLanguageCode("en-US")
                        .setLanguageCode("ru-RU")
                        .build();
        RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(audioByteString).build();

        // Perform the speech recognition request
        RecognizeResponse response = speechClient.recognize(config, audio);
        List<SpeechRecognitionResult> results = response.getResultsList();

        // Display the transcription
        for (SpeechRecognitionResult result : results) {
            // The transcript
            String transcript = result.getAlternatives(0).getTranscript();
            System.out.printf("Transcript: %s%n", transcript);
        }
    }
}
}
