package sentence;

/**
 * Represents a single word in a sentence.
 */
public class WordNode implements Sentence {
  private final String word;
  private final Sentence rest;

  /**
   * Creates a word object within a sentence and rest is the
   * rest of the components of that sentence.
   * 
   * @param word is a word that is part of a sentence.
   * @param rest is the rest of the sentence including punctuation
   *     and words.
   */
  public WordNode(String word, Sentence rest) {
    if (word == null || rest == null) {
      throw new IllegalArgumentException("The word or "
        + "sentence cannot have null params.");
    }
    this.word = word;
    this.rest = rest;
  }

  @Override
  public int getNumberOfWords() {
    int acc = 0;
    return countHelper(acc);
  }
  
  @Override
  public int countHelper(int acc) {
    acc += 1;
    return rest.countHelper(acc);
  }

  @Override
  public String longestWord() {
    String longestRest = rest.longestWord();
    return checkWordLength(longestRest);
  }
  
  private String checkWordLength(String longestRest) {
    if (word.length() > longestRest.length()) {
      return word;
    } else if  (word.length() == longestRest.length() && longestRest.length() != 0) {
      return word;
    } else {
      return longestRest;
    }
  }
  
  @Override 
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(word);
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
    Sentence finalSentence = new WordNode(this.word, duplicated.merge(other));
    return finalSentence;
  }

  @Override
  public Sentence duplicate() {
    return new WordNode(this.word, this.rest.duplicate());
  }
}
