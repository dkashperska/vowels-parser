package luxoft;

import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class VowelsCalculator {

    private static String nl = System.getProperty("line.separator");

    public static void main(String[] args) {

        Console c = System.console();
        if (c == null) {
            System.out.println("Console not found. Exiting.");
            System.exit(1);
        }
        String input;
        List<String> readLines;
        while (true) {
            input = c.readLine("Please enter path to input file.%s", nl);
            checkExitCmd(input);
            try {
                readLines = FileReader.readFromFile(input);
                if (readLines.isEmpty()) {
                    System.out.println("Your input file is empty. Please try again ");
                    continue;
                }
                break;
            } catch (IOException e) {
                System.out.println("Couldn't read from file under entered path. Please try again.");
            }
        }

        System.out.println("Parsing...");
        VowelsParser parser = new VowelsParser();
        List<String> words = parser.pruneInput(readLines);
        Map<WordInfo, Double> vowelFrequencyMap = parser.createVowelFrequencyMap(words);
        System.out.println("Parsing completed successfully.");

        while (true) {
            input = c.readLine("Please enter path to output file.%s", nl);
            checkExitCmd(input);
            FileWriter.deleteExistingFile(input);
            try {
                FileWriter.writeMapToFile(vowelFrequencyMap, input);
                break;
            } catch (IOException e) {
                System.out.println("Couldn't write to file under entered path. Please try again.");
            }
        }
    }

    private static void checkExitCmd(String cmd) {
        if ("exit".equals(cmd)) {
            System.out.println("Exiting");
            System.exit(0);
        }
    }
}
