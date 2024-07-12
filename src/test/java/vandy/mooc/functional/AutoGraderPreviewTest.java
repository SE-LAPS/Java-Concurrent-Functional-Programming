package vandy.mooc.functional;

import io.magnum.autograder.junit.ConsoleFormatter;
import io.magnum.autograder.junit.JUnitEvaluation;
import io.magnum.autograder.junit.JUnitEvaluator;
import org.apache.commons.text.WordUtils;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AutoGraderPreviewTest {

    /**
     * Runs the autograder preview for the provided test classes and saves
     * the results to a file.
     */
    @Test
    public void graderPreview() {
        try {
            run(new File("./build/results.txt"),
                    List.of(
                            ArrayTests.class,
                            FleschKincaidGradeLevelCalculatorTests.class,
                            BardPlayAnalyzerTests.class
                    ));
        } catch (Exception e) {
            throw new RuntimeException("Error running grader preview", e);
        }
    }

    /**
     * Runs the autograder on the provided test classes and saves the results
     * to the specified file.
     *
     * @param resultsFile The file to write the autograder results to.
     * @param classes     The list of test classes to evaluate.
     */
    public static void run(File resultsFile, List<Class<?>> classes) {
        try {
            // Create a JUnitEvaluator instance with the list of test classes
            JUnitEvaluator eval = new JUnitEvaluator(classes);

            // Evaluate the test classes using ConsoleFormatter for output
            JUnitEvaluation estimatedScore = eval.evaluate(new ConsoleFormatter());

            // Display the estimated score and feedback to the console
            System.out.println(WordUtils.wrap("\n\n" +
                    "Your assignment HAS NOT BEEN SUBMITTED. The " +
                    "following test results " +
                    "only estimate your grade.\n\n", 80));

            System.out.println(WordUtils.wrap("Your estimated score is: " +
                    estimatedScore.getScore() + "/" +
                    estimatedScore.getTotalPoints(), 80));

            System.out.println(WordUtils.wrap(
                    "(This is not your actual grade for this " +
                            "assignment, just an estimate. Your official grade will be " +
                            "calculated after you submit your submission.zip file to Coursera.)\n\n",
                    80));

            System.out.println(estimatedScore.getFeedback());

            // Write the feedback to the specified results file
            try (PrintWriter writer = new PrintWriter(resultsFile, StandardCharsets.UTF_8)) {
                writer.print(estimatedScore.getFeedback());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error running grader preview", e);
        }
    }
}
