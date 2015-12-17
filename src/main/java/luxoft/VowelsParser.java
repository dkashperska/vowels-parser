package luxoft;

import java.util.*;

public class VowelsParser {

    public static Set<Character> englishVowels = createSetOfVowels();

    public static List<String> pruneInput(List<String> inputLines){
        List<String> result = new ArrayList<String>();
        for (String line : inputLines){
            //include apostrophe in the word length
            line = line.replaceAll("-"," ").replaceAll("[^A-Za-z'\\s]", "").toLowerCase();
            result.addAll(Arrays.asList(line.split("[\\s]")));
        }
        return result;
    }

    public static Map<Word, Integer> findSetOfVowels(List<String> words){
        Map<Word, Integer> vowelQuantityMap = new LinkedHashMap<Word, Integer>();
        for (String word : words){
            calculateVowelsQuantity(vowelQuantityMap, word);
        }
        return vowelQuantityMap;
    }

    private static void calculateVowelsQuantity(Map<Word, Integer> vowelQuantityMap,String word){
        Set<Character> vowelSet = new HashSet<>();
        int vowelCount = 0;
        for(int i=0; i < word.length(); i++){
            char letter = word.charAt(i);
            if(englishVowels.contains(letter)){
                vowelSet.add(letter);
                vowelCount++;
            }
        }
        addVowelsWithQuantity(vowelQuantityMap, vowelSet, word.length(), vowelCount);
    }

    private static void addVowelsWithQuantity(Map<Word, Integer> vowelQuantityMap, Set<Character> vowelSet, int wordLength, int vowelCount){
        Word wordWithVowels = null;
        if(!vowelSet.isEmpty()){
            wordWithVowels = new Word(vowelSet, wordLength);
        }
        if(wordWithVowels != null)
            if(vowelQuantityMap.containsKey(wordWithVowels))
                vowelQuantityMap.put(wordWithVowels, vowelQuantityMap.get(wordWithVowels) + vowelCount);
            else
                vowelQuantityMap.put(wordWithVowels, vowelCount);
    }

    private static Set<Character> createSetOfVowels(){
        Character[] vowelArray = {'a','e', 'i', 'o', 'u' };
        //TODO Refactor
        return new HashSet(Arrays.asList(vowelArray));
    }
}
