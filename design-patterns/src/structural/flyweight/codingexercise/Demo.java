package structural.flyweight.codingexercise;

import java.util.*;

class Sentence
{
    private final List<WordToken> wordTokens = new ArrayList<>();

    public Sentence(String plainText)
    {
        // todo
        final String[] words = plainText.split(" ");
        Arrays.stream(words).forEach(word -> wordTokens.add(new WordToken(word)));
    }

    public WordToken getWord(int index)
    {
        return wordTokens.get(index);
    }

    @Override
    public String toString()
    {
        // todo
        final StringBuilder sb = new StringBuilder();
        for (WordToken wordToken : wordTokens) {
            sb.append(wordToken.capitalize ? wordToken.word.toUpperCase() : wordToken.word).append(" ");
        }
        String result = sb.toString();

        return result.substring(0, result.length() - 1);
    }

    static class WordToken
    {
        String word;
        public boolean capitalize;

        public WordToken(String word) {
            this.word = word;
        }
    }
}

public class Demo {
}
