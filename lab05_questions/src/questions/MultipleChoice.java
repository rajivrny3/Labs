package questions;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a general Multiple Choice question on a computer-based test.
 */
public class MultipleChoice implements Question {
  private final String questionText;
  private final String correctAnswer;
  private final String[] options;
  private final int typeMc;

  /**
   * Creates a Multiple Choice question and stores information about the
   * correct answer, the text that forms the question and the multiple
   * choices that are available to choose from.
   * 
   * @param questionText is the question that is being asked.
   * @param correctAnswer is the correct answer to that question.
   * @param options are the answer choices for the multiple choice question.
   * @throws IllegalArgumentException when the number of choices
   *     is less than 3 or greater than 8.
   */
  public MultipleChoice(String questionText,
      String correctAnswer, String... options) {
    if (questionText == null || correctAnswer == null || options.equals(null)) {
      throw new IllegalArgumentException("None of the parameters can be null.");
    }
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("The number of "
        + "choices should be between 3 and 8.");
    }

    this.questionText = questionText;
    this.correctAnswer = correctAnswer;
    this.options = options;
    this.typeMc = 2;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MultipleChoice oth = (MultipleChoice) o;
    return this.questionText.equals(oth.questionText)
      && this.correctAnswer.equals(oth.correctAnswer)
      && Arrays.equals(this.options, oth.options);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(questionText, correctAnswer, Arrays.hashCode(options));
  }
  
  @Override
  public int getType() {
    return typeMc;
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
  
  private boolean enteredCheck(int entered) {
    if (entered < 1 || entered > options.length) {
      return true;
    }
    return false;
  }
  
  private boolean enteredIndex(int entered) {
    int correct = Integer.parseInt(correctAnswer);
    if (options[entered - 1].equals(options[correct - 1])) {
      return true;
    }
    return false;
  }

  @Override
  public String answer(String answer) {
    try {
      int entered = Integer.parseInt(answer);
      if (enteredCheck(entered)) {
        return INCORRECT;
      }
      if (enteredIndex(entered)) {
        return CORRECT;
      } else {
        return INCORRECT;
      }
      
    } catch (NumberFormatException e) {
      return INCORRECT;
    }
  }

  @Override
  public String getText() {
    return questionText;
  }

}
