package luxoft;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VowelsCalculator {
    public static void main(String [ ] args){
        System.out.print("\nPlease enter path to input file ");
        //String inputFilePath = System.console().readLine();
        String inputFilePath = "C:\\temp\\input.txt";
        List<String> readLines = new ArrayList<>();
        try {
            readLines = FileReader.readFromFile(inputFilePath);
        } catch (IOException e) {
            System.out.print("\nCouldn't read from file under entered path. Please try again ");
        }

        if(readLines.isEmpty())
           System.out.print("\nYour input file is empty. Please try again ");
        else {
            List<String> words = VowelsParser.pruneInput(readLines);
            Map<Word, Integer> vowelQuantityMap = VowelsParser.findSetOfVowels(words);
            System.out.print("\nPlease enter path to output file ");
            //String outputFilePath = System.console().readLine();
            String outputFilePath = "C:\\temp\\output.txt";
            FileWriter.deleteExistingFile(outputFilePath);
            for(Map.Entry<Word, Integer> entry : vowelQuantityMap.entrySet()){
                StringBuilder line = new StringBuilder();
                line.append(entry.getKey()).append(" -> ").append(calculateAverageNumberOfVowels(entry)).append("\n");
                try {
                    FileWriter.writeLineToFile(outputFilePath, line.toString());
                } catch (IOException e) {
                    System.out.print("\nCouldn't write to file under entered path. Please try again ");
                }
            }
        }
    }

    private static double calculateAverageNumberOfVowels(Map.Entry<Word, Integer> entry){
        return entry.getValue() / entry.getKey().getVowels().size();
    }
}
