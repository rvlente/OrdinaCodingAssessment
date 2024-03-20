import java.util.*;
import java.util.stream.Collectors;


public class RegexWordFrequencyAnalyzer implements WordFrequencyAnalyzer {

    /**
     * The regex pattern for word separators. The assignment states that words consist of characters a-z and A-Z, and
     * that these do not occur elsewhere. Hence, we can describe all separators as a non-empty sequence of non-[a-zA-Z]
     * characters.
     */
    public final String SEPARATOR_PATTERN = "[^a-zA-Z]+";

    /**
     * Creates a map of words and their corresponding occurrence frequency in {@code text}.
     * @param text The input text.
     * @return A {@code Map} with a key for every word in {@code text} and its frequency as the corresponding value.
     */
    private Map<String, Integer> getCounts(String text) {
        // Split the input text by separators.
        var words = text.split(SEPARATOR_PATTERN);

        // Create a word counting map, where each entry is a word with its corresponding count.
        var counts = new HashMap<String, Integer>();

        for (var word : words) {
            // Store the words in lowercase to get the desired case-insensitive behavior.
            var lowerWord = word.toLowerCase();
            counts.put(lowerWord, counts.getOrDefault(lowerWord, 0) + 1);
        }

        return counts;
    }

    /**
     * Calculates the highest word frequency in {@code text}.
     * @param text The input text.
     * @return The highest word frequency.
     */
    @Override
    public int calculateHighestFrequency(String text) {
        var counts = getCounts(text);

        if (counts.isEmpty()) {
            throw new RuntimeException("The text does not contain any words.");
        }

        return counts.entrySet().stream()
                // Find the entry with the highest value (frequency).
                .max(Comparator.comparing(Map.Entry::getValue)).get()
                // Return this value.
                .getValue();
    }

    /**
     * Calculates the frequency of {@code word} in {@code text}.
     * @param text The input text.
     * @param word The input word.
     * @return The frequency of {@code word} in {@code text}
     */
    @Override
    public int calculateFrequencyForWord(String text, String word) {
        var counts = getCounts(text);

        if (counts.isEmpty()) {
            throw new RuntimeException("The text does not contain any words.");
        }

        if (!counts.containsKey(word)) {
            throw new RuntimeException("Word not contained in text.");
        }

        // Look up the frequency value for the word in the counts.
        return counts.get(word);
    }

    /**
     * Calculate the top-N most frequent words in {@code text}.
     * @param text The input text.
     * @param n N.
     * @return An {@code N}-length list of {@code WordFrequency} records.
     */
    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        var counts = getCounts(text);

        if (counts.isEmpty()) {
            throw new RuntimeException("The text does not contain any words.");
        }

        return counts.entrySet().stream()
                // Create WordFrequency objects from the counts.
                .map((e) -> new WordFrequencyRecord(e.getKey(), e.getValue()))
                // Sort by descending frequency and alphabetically.
                .sorted(Comparator.comparingInt(WordFrequencyRecord::getFrequency).reversed().thenComparing(WordFrequencyRecord::getWord))
                // Return first n items.
                .limit(n).collect(Collectors.toList());
    }
}
