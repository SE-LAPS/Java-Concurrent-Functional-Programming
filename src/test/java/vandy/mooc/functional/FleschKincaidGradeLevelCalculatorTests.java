package vandy.mooc.functional;

import io.magnum.autograder.junit.Rubric;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

public class FleschKincaidGradeLevelCalculatorTests {

    @Rule
    public Timeout timeout = new Timeout(3, TimeUnit.SECONDS);

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#calculate(String)}
     */
    @Test
    @Rubric(
            value = "testCalculate",
            goal = "To ensure that the Flesch-Kincaid grade level is calculated correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCalculate() {
        // Arrange
        String text = "Text";

        // Act
        double actualCalculateResult =
                FleschKincaidGradeLevelCalculator.calculate(text);

        // Assert
        assertEquals(-3.40, actualCalculateResult, 0.01);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#calculate(String)}
     */
    @Test
    @Rubric(
            value = "testCalculate2",
            goal = "To ensure that the Flesch-Kincaid grade level is calculated correctly for a " +
                    "string with only vowels.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCalculate2() {
        // Arrange
        String text = "aeiouy";

        // Act
        double actualCalculateResult =
                FleschKincaidGradeLevelCalculator.calculate(text);

        // Assert
        assertEquals(-3.40, actualCalculateResult, 0.01);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#calculate(String)}
     */
    @Test
    @Rubric(
            value = "testCalculate3",
            goal = "To ensure that the Flesch-Kincaid grade level is calculated correctly for a " +
                    "string with a single character.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCalculate3() {
        // Arrange
        String text = "e";

        // Act
        double actualCalculateResult =
                FleschKincaidGradeLevelCalculator.calculate(text);

        // Assert
        assertEquals(-3.40, actualCalculateResult, 0.01);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#calculate(String)}
     */
    @Test
    @Rubric(
            value = "testCalculate4",
            goal = "To ensure that the Flesch-Kincaid grade level is calculated correctly for a null " +
                    "string.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCalculate4() {
        // Arrange
        String text = null;

        // Act
        double actualCalculateResult =
                FleschKincaidGradeLevelCalculator.calculate(text);

        // Assert
        assertEquals(0.0, actualCalculateResult, 0.0);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#calculate(String)}
     */
    @Test
    @Rubric(
            value = "testCalculate5",
            goal = "To ensure that the Flesch-Kincaid grade level is calculated correctly for an " +
                    "empty string.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCalculate5() {
        // Arrange
        String text = "";

        // Act
        double actualCalculateResult =
                FleschKincaidGradeLevelCalculator.calculate(text);

        // Assert
        assertEquals(0.0, actualCalculateResult, 0.0);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#computeFleschKincaidGradeLevelScore(int, int, int)}
     */
    @Test
    @Rubric(
            value = "testComputeFleschKincaidGradeLevelScore",
            goal = "To ensure that the Flesch-Kincaid grade level score is computed correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testComputeFleschKincaidGradeLevelScore() {
        // Arrange
        int sentenceCount = 3;
        int wordCount = 3;
        int syllableCount = 3;

        // Act
        double actualComputeFleschKincaidGradeLevelScoreResult =
                FleschKincaidGradeLevelCalculator.computeFleschKincaidGradeLevelScore(
                        sentenceCount,
                        wordCount,
                        syllableCount);

        // Assert
        assertEquals(-3.40, actualComputeFleschKincaidGradeLevelScoreResult, 0.01);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#computeFleschKincaidGradeLevelScore(int, int, int)}
     */
    @Test
    @Rubric(
            value = "testComputeFleschKincaidGradeLevelScore2",
            goal = "To ensure that the Flesch-Kincaid grade level score is computed correctly for a " +
                    "simple input.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testComputeFleschKincaidGradeLevelScore2() {
        // Arrange
        int sentenceCount = 1;
        int wordCount = 1;
        int syllableCount = 1;

        // Act
        double actualComputeFleschKincaidGradeLevelScoreResult =
                FleschKincaidGradeLevelCalculator.computeFleschKincaidGradeLevelScore(
                        sentenceCount,
                        wordCount,
                        syllableCount);

        // Assert
        assertEquals(-3.40, actualComputeFleschKincaidGradeLevelScoreResult, 0.01);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#computeFleschKincaidGradeLevelScore(int, int, int)}
     */
    @Test
    @Rubric(
            value = "testComputeFleschKincaidGradeLevelScore3",
            goal = "To ensure that the Flesch-Kincaid grade level score is computed correctly for a " +
                    "more complex input.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testComputeFleschKincaidGradeLevelScore3() {
        // Arrange
        int sentenceCount = 1;
        int wordCount = 3;
        int syllableCount = 3;

        // Act
        double actualComputeFleschKincaidGradeLevelScoreResult =
                FleschKincaidGradeLevelCalculator.computeFleschKincaidGradeLevelScore(
                        sentenceCount,
                        wordCount,
                        syllableCount);

        // Assert
        assertEquals(-2.62, actualComputeFleschKincaidGradeLevelScoreResult, 0.01);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#computeFleschKincaidGradeLevelScore(int, int, int)}
     */
    @Test
    @Rubric(
            value = "testComputeFleschKincaidGradeLevelScore4",
            goal = "To ensure that the Flesch-Kincaid grade level score is computed correctly when " +
                    "the sentence count is 0.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testComputeFleschKincaidGradeLevelScore4() {
        // Arrange
        int sentenceCount = 0;
        int wordCount = 3;
        int syllableCount = 3;

        // Act
        double actualComputeFleschKincaidGradeLevelScoreResult =
                FleschKincaidGradeLevelCalculator.computeFleschKincaidGradeLevelScore(
                        sentenceCount,
                        wordCount,
                        syllableCount);

        // Assert
        assertEquals(
                Double.POSITIVE_INFINITY,
                actualComputeFleschKincaidGradeLevelScoreResult,
                0.0);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSentences(String)}
     */
    @Test
    @Rubric(
            value = "testCountSentences",
            goal = "To ensure that the number of sentences in the text is counted correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSentences() {
        // Arrange
        String text = "This is a sentence. This is another sentence.";

        // Act
        int actualCountSentencesResult =
                FleschKincaidGradeLevelCalculator.countSentences(text);

        // Assert
        assertEquals(2, actualCountSentencesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countWords(String)}
     */
    @Test
    @Rubric(
            value = "testCountWords",
            goal = "To ensure that the number of words in the text is counted correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountWords() {
        // Arrange
        String text = "This is a test.";

        // Act
        int actualCountWordsResult =
                FleschKincaidGradeLevelCalculator.countWords(text);

        // Assert
        assertEquals(4, actualCountWordsResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables2",
            goal = "To ensure that the number of syllables in a word is counted correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables2() {
        // Arrange
        String word = "hello";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countSyllables(word);

        // Assert
        assertEquals(2, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables3",
            goal = "To ensure that the number of syllables in a single-character word is counted " +
                    "correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables3() {
        // Arrange
        String word = "a";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countSyllables(word);

        // Assert
        assertEquals(1, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables4",
            goal = "To ensure that the number of syllables in a sentence with multiple words is " +
                    "counted correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables4() {
        // Arrange
        String sentence = "This is a test.";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countSyllables(sentence);

        // Assert
        assertEquals(4, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables5",
            goal = "To ensure that the number of syllables in a null string is handled correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables5() {
        // Arrange
        String text = null;

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countSyllables(text);

        // Assert
        assertEquals(0, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables6",
            goal = "To ensure that the number of syllables in an empty string is handled correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables6() {
        // Arrange
        String text = "";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countSyllables(text);

        // Assert
        assertEquals(0, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Predicate)}
     */
    @Test
    @Rubric(
            value = "testCountSentences2",
            goal = "To ensure that the number of sentences in a text with multiple sentences is " +
                    "counted correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSentences2() {
        // Arrange
        String text = "This is a sentence. This is another sentence.";

        // Act
        int actualCountSentencesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        sentence -> sentence.endsWith("."));

        // Assert
        assertEquals(2, actualCountSentencesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Predicate)}
     */
    @Test
    @Rubric(
            value = "testCountSentences3",
            goal = "To ensure that the number of sentences in a null string is handled correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSentences3() {
        // Arrange
        String text = null;

        // Act
        int actualCountSentencesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        sentence -> sentence.endsWith("."));

        // Assert
        assertEquals(0, actualCountSentencesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Predicate)}
     */
    @Test
    @Rubric(
            value = "testCountSentences4",
            goal = "To ensure that the number of sentences in an empty string is handled correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSentences4() {
        // Arrange
        String text = "";

        // Act
        int actualCountSentencesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        sentence -> sentence.endsWith("."));

        // Assert
        assertEquals(0, actualCountSentencesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Function)}
     */
    @Test
    @Rubric(
            value = "testCountWords2",
            goal = "To ensure that the number of words in a text with special characters is counted " +
                    "correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountWords2() {
        // Arrange
        String text = "This is a, test!";

        // Act
        int actualCountWordsResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        word -> word.matches("\\w+"));

        // Assert
        assertEquals(4, actualCountWordsResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Function)}
     */
    @Test
    @Rubric(
            value = "testCountWords3",
            goal = "To ensure that the number of words in a null string is handled correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountWords3() {
        // Arrange
        String text = null;

        // Act
        int actualCountWordsResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        word -> word.matches("\\w+"));

        // Assert
        assertEquals(0, actualCountWordsResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Function)}
     */
    @Test
    @Rubric(
            value = "testCountWords4",
            goal = "To ensure that the number of words in an empty string is handled correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountWords4() {
        // Arrange
        String text = "";

        // Act
        int actualCountWordsResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        word -> word.matches("\\w+"));

        // Assert
        assertEquals(0, actualCountWordsResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Function)}
     */
    @Test
    @Rubric(
            value = "testCountWords5",
            goal = "To ensure that the number of words in a text with only non-word characters is " +
                    "counted correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountWords5() {
        // Arrange
        String text = "!@#$%^&*()";

        // Act
        int actualCountWordsResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        word -> word.matches("\\w+"));

        // Assert
        assertEquals(0, actualCountWordsResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables7",
            goal = "To ensure that the number of syllables in a text with multiple words and " +
                    "special characters is counted correctly.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables7() {
        // Arrange
        String text = "Hello, world!";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        FleschKincaidGradeLevelCalculator::countSyllables);

        // Assert
        assertEquals(3, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Function)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables8",
            goal = "To ensure that the number of syllables in a null string is handled correctly " +
                    "when using a function to count items.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables8() {
        // Arrange
        String text = null;

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        FleschKincaidGradeLevelCalculator::countSyllables);

        // Assert
        assertEquals(0, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Function)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables9",
            goal = "To ensure that the number of syllables in an empty string is handled correctly " +
                    "when using a function to count items.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables9() {
        // Arrange
        String text = "";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        FleschKincaidGradeLevelCalculator::countSyllables);

        // Assert
        assertEquals(0, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countItems(String, Function)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables10",
            goal = "To ensure that the number of syllables in a text with only non-word characters " +
                    "is counted correctly when using a function to count items.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables10() {
        // Arrange
        String text = "!@#$%^&*()";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        FleschKincaidGradeLevelCalculator::countSyllables);

        // Assert
        assertEquals(0, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables11",
            goal = "To ensure that the number of syllables in a text with multiple words and " +
                    "special characters is counted correctly when using a function to count syllables.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables11() {
        // Arrange
        String text = "Hello, world!";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        FleschKincaidGradeLevelCalculator::countSyllables);

        // Assert
        assertEquals(3, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables12",
            goal = "To ensure that the number of syllables in a null string is handled correctly " +
                    "when using a function to count syllables.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables12() {
        // Arrange
        String text = null;

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        FleschKincaidGradeLevelCalculator::countSyllables);

        // Assert
        assertEquals(0, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables13",
            goal = "To ensure that the number of syllables in an empty string is handled correctly " +
                    "when using a function to count syllables.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables13() {
        // Arrange
        String text = "";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        FleschKincaidGradeLevelCalculator::countSyllables);

        // Assert
        assertEquals(0, actualCountSyllablesResult);
    }

    /**
     * Method under test:
     * {@link FleschKincaidGradeLevelCalculator#countSyllables(String)}
     */
    @Test
    @Rubric(
            value = "testCountSyllables14",
            goal = "To ensure that the number of syllables in a text with only non-word characters " +
                    "is counted correctly when using a function to count syllables.",
            points = 1.0,
            reference = "FleschKincaidGradeLevelCalculatorTests.java"
    )
    public void testCountSyllables14() {
        // Arrange
        String text = "!@#$%^&*()";

        // Act
        int actualCountSyllablesResult =
                FleschKincaidGradeLevelCalculator.countItems(
                        text,
                        FleschKincaidGradeLevelCalculator::countSyllables);

        // Assert
        assertEquals(0, actualCountSyllablesResult);
    }
}
