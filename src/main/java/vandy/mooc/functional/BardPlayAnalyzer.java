package vandy.mooc.functional;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static vandy.mooc.functional.ActiveObject.ActiveObjectArray;
import static vandy.mooc.functional.ExceptionUtils.rethrowSupplier;
import static vandy.mooc.functional.OrdinalSuffix.getOrdinalSuffix;

public class BardPlayAnalyzer
        implements Runnable {
    private static final Map<String, String> sBardMap = FileUtils
            .loadMapFromFolder("plays", ".txt");

    private static boolean sDebug;

    private static final String sNON_ESSENTIAL_PORTIONS_REGEX =
            "(?i)(ACT [IVX]+\\.|Scene [IVX]+\\.|\\[.*?\\]|\\d+\\.|SCENE\\.)";

    private static final String sWORD_REGEX =
            "^  [A-Za-z]+\\.";

    public static final String sEOL_REGEX = "[\\r\\n]+";

    static public void main(String[] args) {
        System.out.println("Starting Concurrent BardPlayAnalyzer");

        new BardPlayAnalyzer().run();

        sDebug = true;
        RunTimer
                .timeRun(() ->
                                new BardPlayAnalyzer().run(),
                        "Concurrent BardPlayAnalyzer");

        display(RunTimer.getTimingResults());

        System.out.println("Ending Concurrent BardPlayAnalyzer");
    }

    @Override
    public void run() {
        var results = runAndReturnResults();
        sortAndPrintResults(results);
    }

    public Array<String> runAndReturnResults() {
        var activeObjects = makeActiveObjects();
        startActiveObjects(activeObjects);
        return processActiveObjects(activeObjects);
    }

    protected ActiveObjectArray<String, String, Double> makeActiveObjects() {
        return ActiveObject.makeActiveObjects(this::processInput, sBardMap);
    }

    protected void startActiveObjects
            (ActiveObjectArray<String, String, Double> activeObjects) {
        activeObjects.forEach(ActiveObject::start);
    }

    protected Array<String> processActiveObjects
            (ActiveObjectArray<String, String, Double> activeObjects) {
        var results = new Array<String>();

        activeObjects.forEach(ao -> {
            try {
                Double score = ao.get();
                results.add(makeStringResult(ao, score));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return results;
    }

    protected Double processInput(Map.Entry<String, String> entry) {
        var play = entry.getValue();
        var strippedPlay = stripNonEssentialPortions(play);
        return FleschKincaidGradeLevelCalculator.calculate(strippedPlay);
    }

    private void sortAndPrintResults(Array<String> results) {
        var list = results.asList();
        list.sort(Collections.reverseOrder());
        list.forEach(BardPlayAnalyzer::display);
    }

    @NotNull
    private static String makeStringResult
            (ActiveObject<Map.Entry<String, String>, Double> activeObject,
             Double gradeLevelScore) {
        return String.format("%.2f", gradeLevelScore)
                + " ("
                + getOrdinalSuffix(gradeLevelScore)
                + " grade) is the score for "
                + activeObject.getParams().getKey();
    }

    public String stripNonEssentialPortions(String play) {
        String strippedPlay = play
                .replaceAll(sNON_ESSENTIAL_PORTIONS_REGEX, "");

        Pattern pattern = Pattern
                .compile(sWORD_REGEX, Pattern.MULTILINE);

        Matcher matcher = pattern.matcher(strippedPlay);
        strippedPlay = matcher.replaceAll("");

        strippedPlay = strippedPlay
                .replaceAll(sEOL_REGEX, System.lineSeparator());

        return strippedPlay.trim();
    }

    private static void display(String string) {
        if (sDebug) {
            System.out.println("["
                    + Thread.currentThread().threadId()
                    + "] "
                    + string);
        }
    }
}