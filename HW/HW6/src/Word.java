import java.util.Set;
import java.util.TreeSet;

public class Word implements Comparable<Word> {
    private String word;
    private Set<Integer> index;
    private int frequency;;

    // TODO implement methods below.


    public Word() {
        this.frequency = 0;
        this.index = new TreeSet<>();
    }

    public Word(String word) {
        if (!isWord(word)) {
            return;
        }
        this.word = word;
        this.index = new TreeSet<>();
        this.frequency = 0;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void addToIndex(Integer line) {
        if (line != null) {
            index.add(line);
        }
    }

    public Set<Integer> getIndex() {
        return index;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Word o) {
        if (o == null) {
            throw new NullPointerException("object is null");
        }
        if (! (o instanceof Word)) {
            throw new ClassCastException(" object's type is not Word");
        }
        return this.word.compareTo(o.word);
    }

    @Override
    public String toString() {
        return word + " " + frequency + " " + index.toString();
    }

    private boolean isWord(String word) {
        return word != null && word.matches("[a-zA-Z]+");
    }

}
