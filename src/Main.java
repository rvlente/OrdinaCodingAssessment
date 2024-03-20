import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        final var analyzer = new RegexWordFrequencyAnalyzer();
        final var text = "a a A a b c c  cc d : d";

        System.out.println("calculateFrequencyForWord(text, \"a\")");
        System.out.println(analyzer.calculateFrequencyForWord(text, "a"));

        System.out.println("calculateFrequencyForWord(text, \"c\")");
        System.out.println(analyzer.calculateFrequencyForWord(text, "c"));

        System.out.println("calculateHighestFrequency(text)");
        System.out.println(analyzer.calculateHighestFrequency(text));

        System.out.println("calculateMostFrequentNWords(text, 2)");
        System.out.println(analyzer.calculateMostFrequentNWords(text, 2));
    }
}