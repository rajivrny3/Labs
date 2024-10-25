package sentence;

/**
 * Represents an instance of punctuation within the sentence.
 */
public class PunctuationNode implements Sentence {
  private final String punctuation;
  private final Sentence rest;
    
  /**
   * Creates an instance of punctuation within the sentence
   * where punctuation is the punctuation as a string and rest
   * is the rest of the sentence.
   * 
   * @param punctuation any form of punctuation within the sentence.
   * @param rest is the rest of the sentence.
   */
  public PunctuationNode(String punctuation, Sentence rest) {
    if (punctuation == null || rest == null) {
      throw new IllegalArgumentException("The punctuation or "
        + "sentence cannot have null params.");
    }
    this.punctuation = punctuation;
    this.rest = rest;
  }
  
  @Override
  public int getNumberOfWords() {
    int acc = 0;
    return countHelper(acc);
  }
  
  @Override
  public int countHelper(int acc) {
    return rest.countHelper(acc);
  }
  
  @Override
  public String longestWord() {
    String longestRest = rest.longestWord();
    return longestRest;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(punctuation);
    if (rest instanceof WordNode) {
      sb.append(" ");
    }
    sb.append(rest.toString());
    return sb.toString();
  }

  @Override
  public Sentence merge(Sentence other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot merge a null value.");
    }
    Sentence duplicated = this.rest.duplicate();
    Sentence finalSentence = new PunctuationNode(this.punctuation, duplicated.merge(other));
    return finalSentence;
  }

  @Override
  public Sentence duplicate() {
    return new PunctuationNode(this.punctuation, this.rest.duplicate());
  }


}
