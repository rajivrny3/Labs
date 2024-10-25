package questions;

import java.util.Objects;

/**
 * This class represents a general Multiple Choice question on a computer-based test.
 */
public class TrueFalse implements Question {
  private final String questionText;
  private final String correctAnswer;
  private final int typeTf;
  
  /**
   * Creates a True or False question object where questionText is the
   * question being asked and correctAnswer is the correctAnswer to the 
   * true or false question.
   * 
   * @param questionText is the question being asked.
   * @param correctAnswer is the correct answer to the true or false question.
   */
  public TrueFalse(String questionText, String correctAnswer) {
    if (questionText == null || correctAnswer == null) {
      throw new IllegalArgumentException("None of the parameters can be null.");
    }
    this.correctAnswer = correctAnswer;
    this.questionText = questionText;
    this.typeTf = 1;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrueFalse oth = (TrueFalse) o;
    return this.questionText.equals(oth.questionText)
            && this.correctAnswer.equals(oth.correctAnswer);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(questionText, correctAnswer);
  }
  
  @Override
  public int getType() {
    return typeTf;
  }
  
  @Override
  public int compareTo(Question o) {
    int thisType = getType();
    int othType = o.getType();
    if (thisType != othType) {
      return Integer.compare(thisType, othType);
    }
    return this.questionText.compareTo(o.getText());
  }
  
  @Override
  public String answer(String answer) {
    if (answer == null) {
      return INCORRECT;
    }
    String lowerAnswer = answer.toLowerCase();
    boolean boolAnswer = Boolean.parseBoolean(lowerAnswer);
    
    String lowerCorrectAnswer = correctAnswer.toLowerCase();
    boolean boolCorrectAnswer = Boolean.parseBoolean(lowerCorrectAnswer);
    
    if (boolCorrectAnswer == boolAnswer) {
      return CORRECT;
    } else {
      return INCORRECT;
    }
  }

  @Override
  public String getText() {
    return questionText;
  }
}
