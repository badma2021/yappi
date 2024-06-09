package com.example.yappi.service;


    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.stereotype.Service;

    import java.io.File;
@Service
    public class VideoToAudioConverter {
    private static final Logger log = LoggerFactory.getLogger(VideoToAudioConverter.class);

        public void processVideo() {
            log.info("before videoFilePath");
            // Replace 'YOUR_VIDEO_FILE_PATH' with the path to your video file
            String videoFilePath = "https://cdn-st.rutubelist.ru/media/87/43/b11df3f344d0af773aac81e410ee/fhd.mp4";
            log.info("before outputAudioFilePath");
            // Replace 'OUTPUT_AUDIO_FILE_PATH' with the desired path for the output audio file
            String outputAudioFilePath = "/opt/audio";
            log.info("before ffmpegCommand");
            // Command to extract audio from video using FFmpeg
            String[] ffmpegCommand = {"ffmpeg", "-i", videoFilePath, "-vn", "-acodec", "pcm_s16le", "-ar", "44100", "-ac", "2", outputAudioFilePath};
            log.info("before try catch");
            try {
                log.info("before Execute FFmpeg command");
                // Execute FFmpeg command
                Process process = new ProcessBuilder(ffmpegCommand).start();
                log.info("before process.waitFor");
                process.waitFor();
                System.out.println("Audio extraction completed successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error occurred during audio extraction.");
            }
        }
    }


