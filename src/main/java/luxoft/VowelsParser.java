package luxoft;

import java.util.*;

public class VowelsParser {

    public static List<String> pruneInput(List<String> inputLines){
        List<String> result = new ArrayList<String>();
        for (String line : inputLines){
            line = line.replaceAll("[^A-Za-z\\s]", "").toLowerCase();
            result.addAll(Arrays.asList(line.split("\\s")));
        }
        return result;
    }

    public static Map<Word, Integer> findSetOfVowels(List<String> words){
        Map<Word, Integer> setOfVowelsWithQuantity = new LinkedHashMap<Word, Integer>();
        Set<Character> englishVowels = createSetOfVowels();
        for (String word : words){
            calculateVowelsQuantity(setOfVowelsWithQuantity, word, englishVowels);
        }
        return setOfVowelsWithQuantity;
    }

    private static void calculateVowelsQuantity(Map<Word, Integer> setOfVowelsWithQuantity,String word, Set<Character> englishVowels){
        Set<Character> vowels = new HashSet<Character>();
        int vowelCount = 0;
        for(int i=0; i < word.length(); i++){
            char letter = word.charAt(i);
            if(englishVowels.contains(letter)){
                vowels.add(letter);
                vowelCount++;
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

    private static Set<Character> createSetOfVowels(){
        Character[] arrayOfVowels = {'a','e', 'i', 'o', 'u' };
        //TODO Refactor
        return new HashSet<Character>(Arrays.asList(arrayOfVowels));
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
