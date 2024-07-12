package vandy.mooc.functional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;

public class FleschKincaidGradeLevelCalculator {
    public static double calculate(String text) {
        if (text == null || text.isBlank()) {
            return 0.0;
        }

        int sentenceCount = countSentences(text);
        int wordCount = countWords(text);
        int syllableCount = countSyllables(text);

        return computeFleschKincaidGradeLevelScore(sentenceCount,
                wordCount,
                syllableCount);
    }

    public static double computeFleschKincaidGradeLevelScore
            (int sentenceCount,
             int wordCount,
             int syllableCount) {
        return (0.39 * (double) wordCount / sentenceCount)
                + (11.8 * (double) syllableCount / wordCount)
                - 15.59;
    }

    static int countSentences(String text) {
        return countItems(text,
                BreakIterator.getSentenceInstance(Locale.US),
                s -> true,
                s -> 1);
    }

    static int countWords(String text) {
        return countItems(text,
                BreakIterator.getWordInstance(Locale.US),
                s -> Character.isLetterOrDigit(s.charAt(0)),
                s -> 1);
    }

    static int countSyllables(String text) {
        return countItems(text,
                BreakIterator.getWordInstance(Locale.US),
                s -> Character.isLetterOrDigit(s.charAt(0)),
                FleschKincaidGradeLevelCalculator::countSyllablesInWord);
    }

    static int countItems(String text,
                          @NotNull BreakIterator iterator,
                          @Nullable Predicate<String> predicate,
                          @Nullable Function<String, Integer> mapper) {
        iterator.setText(text);
        int start = iterator.first();
        int count = 0;

        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            String item = text.substring(start, end);
            if (predicate == null || predicate.test(item)) {
                count += (mapper != null) ? mapper.apply(item) : 1;
            }
        }

        return count;
    }

    private static int countSyllablesInWord(String word) {
        if (word == null || word.isEmpty()) {
            return 0;
        }

        word = word.toLowerCase();
        Predicate<Character> isVowel =
                c -> "aeiouy".indexOf(Character.toLowerCase(c)) != -1;

        int syllableCount = 0;
        boolean previousCharIsVowel = false;

        for (char c : word.toCharArray()) {
            boolean currentCharIsVowel = isVowel.test(c);
            if (currentCharIsVowel && !previousCharIsVowel) {
                syllableCount++;
            }
            previousCharIsVowel = currentCharIsVowel;
        }

        if (word.endsWith("e")
                && !previousCharIsVowel && syllableCount > 0) {
            syllableCount--;
        }

        return syllableCount;
    }
}