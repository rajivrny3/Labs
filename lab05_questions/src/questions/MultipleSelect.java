package questions;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a general Multiple Choice question on a computer-based test.
 */
public class MultipleSelect implements Question {
  private final String questionText;
  private final String[] correctAnswers;
  private final String[] options;
  private final int typeMs;

  /**
   * Creates an instance of a MultipleSelect Question where
   * question text is the framing of the question, correctAnswers
   * is the list of correct answers for that question, and options
   * is the total options available to choose from for that question.
   * 
   * @param questionText is the framing of the question
   * @param correctAnswers are the correct answers for that question
   * @param options are the available options for that question
   */
  public MultipleSelect(String questionText, 
      String correctAnswers, String... options) {
    if (questionText == null || correctAnswers.equals(null) || options.equals(null)) {
      throw new IllegalArgumentException("None of the parameters can be null.");
    }
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("The number of "
        + "choices should be between 3 and 8.");
    }
    this.questionText = questionText;
    this.correctAnswers = correctAnswers.split("\\s+");
    this.options = options;
    this.typeMs = 3;
    
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MultipleSelect oth = (MultipleSelect) o;
    return this.questionText.equals(oth.questionText)
      && Arrays.equals(this.options, oth.options)
      && Arrays.equals(this.correctAnswers, oth.correctAnswers);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(questionText, Arrays.hashCode(options), 
        Arrays.hashCode(correctAnswers));
  }
  
  @Override
  public int getType() {
    return typeMs;
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
  
  
  private static void duplicateChecker(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i] == array[i - 1]) {
        throw new IllegalArgumentException("Options or "
               + "answers may not have duplicates.");
      }
    }
  }
  
  private boolean enteredCheck(int[] entered) {
    if (entered.length < 1 || entered.length > options.length
           || entered.length != correctAnswers.length) {
      return true;
    }
    return false;
  }
  
  private boolean enteredBound(int[] entered) {
    for (int i = 0; i < entered.length; i++) {
      if (entered[i] < 1 || entered[i] > options.length) {
        return true;
      }
    }
    return false;
  }

  @Override
    public String answer(String answer) {
    if (answer == null) {
      return INCORRECT;
    }
    String[] entered = answer.split("\\s+");
    int[] enteredInt = new int[entered.length];
    
    try {
      for (int i = 0; i < entered.length; i++) {
        enteredInt[i] = Integer.parseInt(entered[i]);
      }
      duplicateChecker(enteredInt);
      if (enteredCheck(enteredInt)) {
        return INCORRECT;
      }
      
      if (enteredBound(enteredInt)) { 
        return INCORRECT;
      }
      Arrays.sort(enteredInt);
      Arrays.sort(options);
      Arrays.sort(correctAnswers);
      
      for (int i = 0; i < enteredInt.length; i++) {
        int tempInt = Integer.parseInt(correctAnswers[i]);
        if (!options[enteredInt[i] - 1].equals(options[tempInt - 1])) {
          return INCORRECT;
        }
      } 
      
    } catch (NumberFormatException e) {
      return INCORRECT;
    }
    return CORRECT;
  }

  @Override
  public String getText() {
    return questionText;
  }

}
