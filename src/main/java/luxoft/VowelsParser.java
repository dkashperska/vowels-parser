package luxoft;

import java.util.*;

public class VowelsParser {

    public static List<String> pruneInput(List<String> inputLines){
        List<String> result = new ArrayList<String>();
        for (String line : inputLines){
            line.replaceAll("[^A-Za-z\\s]", "").toLowerCase();
            result.addAll(Arrays.asList(line.split("\\s")));
        }
        return result;
    }

    public static Map<Word, Integer> findSetOfVowels(List<String> words){
        Map<Word, Integer> setOfVowelsWithQuantity = new LinkedHashMap<Word, Integer>();
        for (String word : words){
            calculateVowelsQuantity(setOfVowelsWithQuantity, word);
        }
        return setOfVowelsWithQuantity;
    }

    private static void calculateVowelsQuantity(Map<Word, Integer> setOfVowelsWithQuantity,String word){
        Set<Character> vowels = new HashSet<Character>();
        int vowelCount = 0;
        for(int i=0; i < word.length(); i++){
            switch (word.charAt(i)) {
                case 'a': {
                    vowels.add('a');
                    vowelCount++;
                }
                case 'e': {
                    vowels.add('e');
                    vowelCount++;
                }
                case 'i': {
                    vowels.add('i');
                    vowelCount++;
                }
                case 'o': {
                    vowels.add('o');
                    vowelCount++;
                }
                case 'u': {
                    vowels.add('u');
                    vowelCount++;
                }
            }
        }
        Word wordWithVowels = new Word();
        if(!vowels.isEmpty()){
            wordWithVowels.setVowels(vowels);
            wordWithVowels.setLength(word.length());

        }
        if(!setOfVowelsWithQuantity.isEmpty() && setOfVowelsWithQuantity.containsKey(wordWithVowels))
            setOfVowelsWithQuantity.put(wordWithVowels, setOfVowelsWithQuantity.get(wordWithVowels) + vowelCount);
        setOfVowelsWithQuantity.put(wordWithVowels, vowelCount);
    }

 /*   private enum Vowels {
        A('a'), E('e'), I('i'), O('o'), U('u');

        private final char value;

        Vowels(char value){
            this.value = value;
        }

        public char value(){
            return value;
        }
    }*/
}
