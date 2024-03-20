import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RegexWordFrequencyAnalyzerTest {

    private final RegexWordFrequencyAnalyzer _analyzer = new RegexWordFrequencyAnalyzer();

    @ParameterizedTest
    @CsvSource(delimiter = ':', value = {
            "a a a a a a a a:8",
            "a-a,a.D-c.A$^a)a;A~b:7",
            "ab aB Ab AB:4"
            // TODO: add more tests.
    })
    void calculateHighestFrequency_succeeds(String text, int expected) {
        assertEquals(expected, _analyzer.calculateHighestFrequency(text));
    }

    @ParameterizedTest
    @CsvSource(delimiter = ':', value = {
            "aa aa a A a b a b A   :a:5",
            "a-a,a.D-c.A$^a)a;A~b/C:c:2",
            "a-a,a.D-c.A$^a)a;A~b/c:d:1",
            // TODO: add more tests.
    })
    void calculateFrequencyForWord_succeeds(String text, String word, int expected) {
        assertEquals(expected, _analyzer.calculateFrequencyForWord(text, word));
    }

    @ParameterizedTest
    @CsvSource(delimiter = ':', value = {
            "The sun shines over the lake     :3 :[(the, 2), (lake, 1), (over, 1)]",
            "aaa-aaa.aa,aa!aa&aa*a@a!a!!!a^a,a:3 :[(a, 6), (aa, 4), (aaa, 2)]",
            "ccc aAb bbb AAB aab          :2 :[(aab, 3), (bbb, 1)]",
            "a A b                            :99:[(a, 2), (b, 1)]",
            // TODO: add more tests.
    })
    void calculateMostFrequentNWords_succeeds(String text, int n, String expected) {
        assertEquals(expected, _analyzer.calculateMostFrequentNWords(text, n).toString());
    }
}