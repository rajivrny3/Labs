package sentence;

/**
 * An empty node representing the end of a sentence.
 */
public class EmptyNode implements Sentence {

  @Override
  public int getNumberOfWords() {
    return 0;
  }
  
  @Override
  public int countHelper(int acc) {
    return acc;
  }

  @Override
  public String longestWord() {
    return "";
  }
  
  @Override
  public String toString() {
    return "";
  }

  @Override
  public Sentence merge(Sentence other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot merge a null value.");
    }
    return other;
  }
  
  

  @Override
  public Sentence duplicate() {
    return new EmptyNode();
  }

}
