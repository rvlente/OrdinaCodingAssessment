//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        var analyzer = new RegexWordFrequencyAnalyzer();
        var form = new WordFrequencyAnalyzerForm(analyzer);
        form.setVisible(true);
    }
}