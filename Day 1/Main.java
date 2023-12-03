import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

 public class Main {

    public static void main(String[] args) {

        String file = "calibrationDocument.txt";

        try {
            FileReader fileReader = new FileReader(new File(file));
            BufferedReader br = new BufferedReader(fileReader);

            DocumentCalculator dc = new DocumentCalculator(br);

            dc.run();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
