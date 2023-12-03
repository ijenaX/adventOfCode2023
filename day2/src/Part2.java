import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public Part2(BufferedReader bufferedReader) throws IOException {
        calculateResult(collectLines(bufferedReader));
    }

    private List<Integer> collectLines(BufferedReader br) throws IOException {
        String currentLine;
        List<Integer> multipliedCubes = new ArrayList<>();

        while ((currentLine = br.readLine()) != null) {
            Map<String, Integer> parsedGame = parseGame(currentLine);
            multipliedCubes.add(parsedGame.get("red") * parsedGame.get("green") * parsedGame.get("blue"));

        }
        System.out.println("Multiplied Games: " + multipliedCubes);
        return multipliedCubes;
    }

    private Map<String, Integer> parseGame(String line){
        int gameId = Integer.parseInt(line.substring(0, line.indexOf(":")).replaceAll("\\D+", ""));
        int reds = parseColors(line)[0];
        int greens = parseColors(line)[1];
        int blues = parseColors(line)[2];

        Map<String, Integer> game = new HashMap<>() {{
            put("gameId", gameId);
            put("red", reds);
            put("green", greens);
            put("blue", blues);
        }};
        System.out.println("Game: " + gameId + ", Reds " + reds + ", Greens " + greens + ", Blues " + blues);
        return game;
    }

    private int[] parseColors(String line){
        int resultred = 0;
        int resultgreen = 0;
        int resultblue = 0;

        Pattern patternRed = Pattern.compile("\\b(\\d+) red\\b");
        Pattern patternGreen = Pattern.compile("\\b(\\d+) green\\b");
        Pattern patternBlue = Pattern.compile("\\b(\\d+) blue\\b");

        Matcher matcherRed = patternRed.matcher(line);
        while(matcherRed.find()){
            int currentCube = Integer.parseInt(matcherRed.group(1));
            resultred = Math.max(resultred, currentCube);
        }

        Matcher matcherGreen = patternGreen.matcher(line);
        while(matcherGreen.find()){
            int currentCube = Integer.parseInt(matcherGreen.group(1));
            resultgreen = Math.max(resultgreen, currentCube);
        }

        Matcher matcherBlue = patternBlue.matcher(line);
        while(matcherBlue.find()){
            int currentCube = Integer.parseInt(matcherBlue.group(1));
            resultblue = Math.max(resultblue, currentCube);
        }

        return new int[]{resultred, resultgreen, resultblue};
    }

    private boolean isMeetCondition(Map<String, Integer> game) {
        int countRedCubes = 12;
        int countGreenCubes = 13;
        int countBlueCubes = 14;

        return game.get("red") <= countRedCubes && game.get("green") <= countGreenCubes && game.get("blue") <= countBlueCubes;
    }

    public void calculateResult(List<Integer> games) {
        int gamesIdSum = games.stream().mapToInt(a -> a).sum();
        System.out.println(gamesIdSum);
    }
}
