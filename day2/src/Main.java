import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("day2/src/input.txt");
            BufferedReader br = new BufferedReader(fileReader);

            //new Part1(br);
            new Part2(br);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}