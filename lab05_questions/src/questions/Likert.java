package questions;

import java.util.Objects;

/**
 * This class represents a general Likert question on a computer-based test.
 */
public class Likert implements Question {
  private final String questionText;
  private final int typeLikert;

  /**
   * Makes an LikertQuestion object with text representing
   * the text that forms the main question being asked.
   * 
   * @param text is the question being asked to determine something's
   *     likert value.
   */
  public Likert(String text) {
    if (text == null) {
      throw new IllegalArgumentException("The text parameter cannot be null.");
    }
    this.questionText = text;
    this.typeLikert = 4;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Likert oth = (Likert) o;
    return this.questionText.equals(oth.questionText);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(questionText);
  }
  
  @Override
  public int getType() {
    return typeLikert;
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
    try {
      LikertOptions entered = stringToLikert(answer);
      return CORRECT;
    } catch (IllegalArgumentException e) {
      return INCORRECT;
    }
  }
  
  /**
   * Helper method to convert a string to a valid Likert option.
   * 
   * @param option is an option entered by the user based on the 1-5 Likert scale.
   * @return returns the String's corresponding Likert option.
   */
  public static LikertOptions stringToLikert(String option) {
    switch (option) {
      case "1":
        return LikertOptions.STRONGLY_AGREE;
      case "2":
        return LikertOptions.AGREE;
      case "3":
        return LikertOptions.NEITHER;
      case "4":
        return LikertOptions.DISAGREE;
      case "5":
        return LikertOptions.STRONGLY_DISAGREE;
      default:
        throw new IllegalArgumentException("Number is not valid.");
    }
  }

  @Override
  public String getText() {
    return questionText;
  }

}
