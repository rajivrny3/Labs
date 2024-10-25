package questions;

/**
 * defines the correct answer options for a Likert scale question.
 */
public enum LikertOptions {
  STRONGLY_AGREE("1"),
  AGREE("2"),
  NEITHER("3"),
  DISAGREE("4"),
  STRONGLY_DISAGREE("5");
  
  private final String option;
  
  LikertOptions(String choice) {
    this.option = choice;
  }
  
  @Override
  public String toString() {
    return option;
  }
}
