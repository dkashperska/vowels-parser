package luxoft;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class WordInfo {

    public WordInfo(Set<Character> vowels, int length) {
        this.vowels = vowels;
        this.length = length;
    }

    private Set<Character> vowels;

    private int length;

    public Set<Character> getVowels() {
        return vowels;
    }

    public void setVowels(Set<Character> vowels) {
        this.vowels = vowels;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordInfo wordInfo = (WordInfo) o;

        if (length != wordInfo.length) return false;
        if (vowels != null ? wordInfo.vowels == null : wordInfo.vowels != null) return false;
        if (vowels != null && vowels.size() != wordInfo.vowels.size()) return false;
        if (vowels != null && !vowels.containsAll(wordInfo.vowels)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = vowels != null ? vowels.hashCode() : 0;
        result = 31 * result + length;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("({");
        result.append(StringUtils.join(vowels, ", "));
        result.append("}, ").append(length).append(")");
        return result.toString();
    }
}
