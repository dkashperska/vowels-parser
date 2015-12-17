package luxoft;

import org.apache.commons.math3.util.Precision;

import java.util.*;

public class VowelsParser {

    private Set<Character> knownVowels;

    public VowelsParser() {
        this(getEnglishVowels());
    }

    public VowelsParser(Collection<Character> vowels) {
        knownVowels = new HashSet<>(vowels);
    }

    public List<String> pruneInput(List<String> inputLines) {
        List<String> result = new ArrayList<String>();
        for (String line : inputLines) {
            //include apostrophe in the word length
            line = line.replaceAll("-", " ").replaceAll("[^A-Za-z'\\s]", "").toLowerCase();
            result.addAll(Arrays.asList(line.split("[\\s]")));
        }
        return result;
    }

    public Map<WordInfo, Double> createVowelFrequencyMap(List<String> words) {
        Map<WordInfo, VowelQuantity> vowelFrequencyMap = new LinkedHashMap<>();
        for (String word : words) {
            Set<Character> vowels = extractVowels(word);
            int count = getVowelCount(word);

            if (!vowels.isEmpty()) {
                WordInfo wordInfo = new WordInfo(vowels, word.length());

                VowelQuantity quantity = vowelFrequencyMap.get(wordInfo);
                if (quantity == null) {
                    quantity = new VowelQuantity();
                }
                quantity.vowelCount += count;
                quantity.wordCount += 1;
                vowelFrequencyMap.put(wordInfo, quantity);
            }
        }

        return transformMap(vowelFrequencyMap);
    }

    public Set<Character> extractVowels(String word) {
        Set<Character> vowels = new HashSet<>(word.length());
        for (char letter : word.toCharArray()) {
            if (knownVowels.contains(letter)) {
                vowels.add(letter);
            }
        }
        return vowels;
    }

    public int getVowelCount(String word) {
        int count = 0;
        for (char letter : word.toCharArray()) {
            if (knownVowels.contains(letter)) {
                count++;
            }
        }
        return count;
    }

    private Map<WordInfo, Double> transformMap(Map<WordInfo, VowelQuantity> input) {
        Map<WordInfo, Double> result = new LinkedHashMap<>(input.size());
        for (Map.Entry<WordInfo, VowelQuantity> entry : input.entrySet()) {
            double frequency = (double) entry.getValue().vowelCount / entry.getValue().wordCount;
            result.put(entry.getKey(), Precision.round(frequency, 1));
        }
        return result;
    }

    private static Collection<Character> getEnglishVowels() {
        return Arrays.asList('a', 'e', 'i', 'o', 'u');
    }

    private class VowelQuantity {
        int vowelCount;
        int wordCount;
    }
}
