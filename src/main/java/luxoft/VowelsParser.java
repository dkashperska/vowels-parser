package luxoft;

import java.util.*;

public class VowelsParser {

    public static Set<Character> englishVowels = createSetOfVowels();

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
        for (String word : words){
            calculateVowelsQuantity(setOfVowelsWithQuantity, word);
        }
        return setOfVowelsWithQuantity;
    }

    private static void calculateVowelsQuantity(Map<Word, Integer> setOfVowelsWithQuantity,String word){
        Set<Character> setOfVowels = new HashSet<Character>();
        int vowelCount = 0;
        for(int i=0; i < word.length(); i++){
            char letter = word.charAt(i);
            if(englishVowels.contains(letter)){
                setOfVowels.add(letter);
                vowelCount++;
            }
        }
        addVowelsWithQuantity(setOfVowelsWithQuantity, setOfVowels, word.length(), vowelCount);
    }

    private static void addVowelsWithQuantity(Map<Word, Integer> setOfVowelsWithQuantity, Set<Character> setOfVowels, int wordLength, int vowelCount){
        Word wordWithVowels = null;
        if(!setOfVowels.isEmpty()){
            wordWithVowels = new Word(setOfVowels, wordLength);
        }
        if(wordWithVowels != null)
            if(setOfVowelsWithQuantity.containsKey(wordWithVowels))
                setOfVowelsWithQuantity.put(wordWithVowels, setOfVowelsWithQuantity.get(wordWithVowels) + vowelCount);
            else
                setOfVowelsWithQuantity.put(wordWithVowels, vowelCount);
    }

    private static Set<Character> createSetOfVowels(){
        Character[] arrayOfVowels = {'a','e', 'i', 'o', 'u' };
        //TODO Refactor
        return new HashSet(Arrays.asList(arrayOfVowels));
    }
}
