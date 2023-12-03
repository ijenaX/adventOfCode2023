import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

public class DocumentCalculator {

    private BufferedReader calibrationDocument = null;
    private List<Integer> calibrationValues = new ArrayList<Integer>();
    private int result = 0;

    private String[] spelledDigits = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private Map<String, String> digitWords = Map.of("one", "1", "two", "2", "three", "3", "four", "4", "five", "5", "six", "6", "seven", "7", "eight", "8", "nine", "9");

    public DocumentCalculator(BufferedReader br) {
        calibrationDocument = br;
    }

    private void readValues(BufferedReader br) throws IOException {
        String currentLine = null;

        while((currentLine = br.readLine()) != null) {
            String firstDigit = getFirstDigit(currentLine);
            String lastDigit = getLastDigit(currentLine);
            String value = firstDigit + lastDigit;

            calibrationValues.add(Integer.parseInt(value));
        }
    }

    private String getFirstDigit(String line) {
        String digit = "";
        String remainingLine = line;

        while (remainingLine.length() != 0 && digit.isEmpty()) {
            for(String key : this.digitWords.keySet()) {
                char firstChar = remainingLine.charAt(0);
                boolean isNumber = isNumber(firstChar);

                if(isNumber) {
                    digit = String.valueOf(firstChar);
                }
                else if(remainingLine.startsWith(key)) {
                    digit = this.digitWords.get(key);
                }
            }
            remainingLine = remainingLine.substring(1);
        }
        return digit;
    }

    private String getLastDigit(String line) {
        String digit = "";
        String remainingLine = line;

        while (remainingLine.length() != 0) {
            for(String key : this.digitWords.keySet()) {
                char firstChar = remainingLine.charAt(0);
                boolean isNumber = isNumber(firstChar);

                if(isNumber) {
                    digit = String.valueOf(firstChar);
                }
                else if(remainingLine.startsWith(key)) {
                    digit = this.digitWords.get(key);
                }
            }
            remainingLine = remainingLine.substring(1);
        }
        return digit;
    }

    private boolean isNumber(char ch) {
        return Character.isDigit(ch);
    }

    private int calculateResult() {
        return calibrationValues.stream()
                            .mapToInt(Integer::intValue)
                            .sum();
    }

    public void run() throws IOException{
        readValues(calibrationDocument);
        result = calculateResult();
        System.out.println(result);
    }

}
