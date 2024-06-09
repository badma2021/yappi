import com.example.yappi.service.DownloadService;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass {

    public String getFileName(String ref) {

        Pattern pattern = Pattern.compile("(.*)(?=/)");

        Matcher matcher = pattern.matcher(ref);
        String result = null;
        if (matcher.find()) {
            result = matcher.group(0);
            System.out.println("Result: " + result);
        } else {
            System.out.println("No match found.");
        }
        return result;
    }
    @Test
    void testGetFilename(){
        String link="https://cdn-st.rutubelist.ru/media/b0/e9/ef285e0241139fc611318ed33071/fhd.mp4";

       // getFileName(link);

        Path fileName = Paths.get(link);

        System.out.println(fileName.getFileName().toString());
    }

    void getFileN(){

    }
}
