public class WordFrequencyRecord implements WordFrequency {

    private final String _word;
    private final int _frequency;

    public WordFrequencyRecord(String word, int frequency) {
        _word = word;
        _frequency = frequency;
    }

    @Override
    public String getWord() {
        return _word;
    }

    @Override
    public int getFrequency() {
        return _frequency;
    }

    @Override
    public String toString() {
        return String.format("(%s, %d)", _word, _frequency);
    }
}
