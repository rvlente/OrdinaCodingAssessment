import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WordFrequencyAnalyzerForm extends JFrame {

    private final WordFrequencyAnalyzer analyzer;

    public WordFrequencyAnalyzerForm(WordFrequencyAnalyzer analyzer) {
        this.analyzer = analyzer;
        initializeComponents();
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(mainPanel);

        // Text input controls.
        JLabel textInputLabel = new JLabel("Enter your input text and press the \"Load\" button.", SwingConstants.LEFT);
        textInputLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // why does this not work?

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);

        mainPanel.add(textInputLabel);
        mainPanel.add(textArea);

        // Make text area vertically scrollable.
        JScrollPane scroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(scroll);

        // Add a panel with a button for each function.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(buttonPanel);

        // Highest frequency
        JButton highestFrequencyButton = new JButton();
        highestFrequencyButton.setText("Calculate Highest Frequency");
        highestFrequencyButton.addActionListener(e -> handleHighestFrequency(textArea.getText()));

        // Word frequency
        JButton wordFrequencyButton = new JButton();
        wordFrequencyButton.setText("Calculate Frequency for Word");
        wordFrequencyButton.addActionListener(e -> handleWordFrequency(textArea.getText()));

        // Top-N words
        JButton topNWordsButton = new JButton();
        topNWordsButton.setText("Calculate Most Frequent Words");
        topNWordsButton.addActionListener(e -> handleMostFrequentNWords(textArea.getText()));


        buttonPanel.add(highestFrequencyButton);
        buttonPanel.add(wordFrequencyButton);
        buttonPanel.add(topNWordsButton);

        // Set fixed window properties.
        this.setSize(800, 300);
        this.setResizable(false);
    }

    private void handleHighestFrequency(String text) {
        try {
            var result = analyzer.calculateHighestFrequency(text);
            JOptionPane.showMessageDialog(this,
                    String.format("The highest word frequency in this text is %s.", result));
        }
        catch (Exception ex) {
            showErrorMessage(String.format("An error occurred: %s", ex.getMessage()));
        }
    }

    private void handleWordFrequency(String text) {
        var word = JOptionPane.showInputDialog("Type your word.");

        try {
            var result = analyzer.calculateFrequencyForWord(text, word);
            JOptionPane.showMessageDialog(this,
                    String.format("The frequency of word %s is %s.", word, result));
        }
        catch (Exception ex) {
            showErrorMessage(String.format("An error occurred: %s", ex.getMessage()));
        }
    }

    private void handleMostFrequentNWords(String text) {
        var input = JOptionPane.showInputDialog("How many words?");

        try {
            var n = Integer.parseInt(input);

            var result = analyzer.calculateMostFrequentNWords(text, n);

            // Create a nice display string from the result data.
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("Top-%d words:\n", n));

            for (var item : result) {
                builder.append(String.format("%d: %s\n", item.getFrequency(), item.getWord()));
            }

            JOptionPane.showMessageDialog(this, builder.toString());
        }
        catch (NumberFormatException ex) {
            showErrorMessage("Invalid number entered!");
        }
        catch (RuntimeException ex) {
            showErrorMessage(String.format("An error occurred: %s", ex.getMessage()));
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this,
                message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
