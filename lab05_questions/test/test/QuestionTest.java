package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import questions.Likert;
import questions.LikertOptions;
import questions.MultipleChoice;
import questions.MultipleSelect;
import questions.Question;
import questions.TrueFalse;


/**
 * Class to test question objects.
 */
public class QuestionTest {
  private Question likert;
  private Question multipleChoice;
  private Question multipleSelect;
  private Question trueOrFalse;
  private Question[] questionArray;
  
  /**
  *  Sets up objects for the test cases.
 */
  @Before
  public void setup() {
    likert = new Likert("Rate on a scale?");
    multipleChoice = new MultipleChoice("What is the right answer?", 
      "1", "A", "B", "C", "D");
    multipleSelect = new MultipleSelect("What are the right answers?", 
       "1 2 3", "A", "B", "C", "D");
    trueOrFalse = new TrueFalse("True or False", "True");
    questionArray = new Question[] {likert, multipleChoice, multipleSelect, trueOrFalse};
  }
  
  @Test
  public void  likertCreationTest() {
    Question likertTemp = new Likert("Rate on a scale?");
    assertEquals(likert, likertTemp);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLikert() {
    Question likertTemp = new Likert(null);
  }
  
  @Test
  public void likertComparisonSame() {
    Question likertTemp = new Likert("Rate on a scale?");
    int comparison = likert.compareTo(likertTemp);
    assertEquals(0, comparison);
  }
  
  @Test
  public void likertComparisonDifferent() {
    int comparison = likert.compareTo(multipleChoice);
    assertEquals(1, comparison);
  }
  
  @Test
  public void likertAnswerCorrect() {
    String answer = "1";
    assertEquals("Correct", likert.answer(answer));
  }
  
  @Test
  public void likertAnswerIncorrect() {
    String answer = "0";
    assertEquals("Incorrect", likert.answer(answer));
  }
  
  @Test
  public void likertGetText() {
    String testString = "Rate on a scale?";
    assertEquals(testString, likert.getText());
  }
  
  @Test
  public void multipleChoiceCreationTest() {
    Question mcTemp = new MultipleChoice("What is the right answer?", 
        "1", "A", "B", "C", "D");
    assertEquals(mcTemp, multipleChoice);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void mcNullParameter() {
    Question mcTemp = new MultipleChoice(null, 
        "1", "A", "B", "C", "D");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void mcTooManyOptions() {
    Question mcTemp = new MultipleChoice(null, 
        "1", "A", "B", "C", "D", "E", "F", "G", "H", "I");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void mcTooFewOptions() {
    Question mcTemp = new MultipleChoice(null, 
        "1", "A", "B");
  }
  
  @Test
  public void mcCompareSame() {
    Question mcTemp = new MultipleChoice("What is the right answer?", 
         "1", "A", "B", "C", "D");
    int compare = multipleChoice.compareTo(mcTemp);
    assertEquals(0, compare);
  }
  
  @Test
  public void mcCompareLess() {
    assertEquals(-1, multipleChoice.compareTo(likert));
  }
  
  @Test
  public void mcCompareMore() {
    assertEquals(1, multipleChoice.compareTo(trueOrFalse));
  }
  
  @Test
  public void mcAnswerCorrect() {
    String answer = "1";
    assertEquals("Correct", multipleChoice.answer(answer));
  }
  
  @Test
  public void mcAnswerIncorrectInvalid() {
    String answer = "0";
    assertEquals("Incorrect", multipleChoice.answer(answer));
  }
  
  @Test
  public void mcAnswerIncorrectFormat() {
    String answer = "four";
    assertEquals("Incorrect", multipleChoice.answer(answer));
  }
  
  @Test
  public void mcAnswerIncorrect() {
    String answer = "3";
    assertEquals("Incorrect", multipleChoice.answer(answer));
  }
  
  @Test
  public void mcGetText() {
    String multcQt = "What is the right answer?";
    assertEquals(multcQt, multipleChoice.getText());
  }
  
  @Test
  public void multipleSelectCreation() {
    Question multipleSelectTemp = new MultipleSelect("What are the right answers?", 
        "1 2 3", "A", "B", "C", "D");
    assertEquals(multipleSelectTemp, multipleSelect);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void multipleSelectCreationFailNull() {
    Question multipleSelectTemp = new MultipleSelect(null, 
        "1 2 3", "A", "B", "C", "D");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void multipleSelectCreationFailBounds() {
    Question multSeleTemp = new MultipleSelect("What are the right answers?",
         "1 2 3", "A", "B", "C", "D", "E", "F", "G", "H", "I");
  }
  
  @Test
  public void multipleSelectCompareSame() {
    Question msTemp = new MultipleSelect("What are the right answers?", 
        "1 2 3", "A", "B", "C", "D");
    assertEquals(0, multipleSelect.compareTo(msTemp));
  }
  
  @Test
  public void multipleSelectCompareLess() {
    assertEquals(-1, multipleSelect.compareTo(likert));
  }
  
  @Test
  public void multipleSelectCompareMore() {
    assertEquals(1, multipleSelect.compareTo(trueOrFalse));
  }
  
  @Test
  public void multipleSelectAnswerCorrect() {
    String answer = "1 2 3";
    assertEquals("Correct", multipleSelect.answer(answer));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void multipleSelectAnswerDupe() {
    String answer = "1 1 3";
    assertEquals("Incorrect", multipleSelect.answer(answer));
  }
  
  @Test
  public void multipleSelectAnswerOutBounds() {
    String answer = "1 0 3";
    assertEquals("Incorrect", multipleSelect.answer(answer));
  }
  
  @Test
  public void multipleSelectIncorrect() {
    String answer = "1 4 3";
    assertEquals("Incorrect", multipleSelect.answer(answer));
  }
  
  @Test
  public void multipleSelectTooMany() {
    String answer = "1 4 3 2";
    assertEquals("Incorrect", multipleSelect.answer(answer));
  }
  
  @Test
  public void multipleSelectAnswerNull() {
    String answer = null;
    assertEquals("Incorrect", multipleSelect.answer(answer));
  }
  
  @Test
  public void multipleSelectTooFew() {
    String answer = "1";
    assertEquals("Incorrect", multipleSelect.answer(answer));
  }
  
  @Test
  public void multipleSelectFormat() {
    String answer = "four";
    assertEquals("Incorrect", multipleSelect.answer(answer));
  }
  
  @Test
  public void multipleSelectGetText() {
    assertEquals("What are the right answers?", multipleSelect.getText());
  }
  
  @Test
  public void trueOrFalseCreation() {
    Question tempToF = new TrueFalse("True or False", "True");
    assertEquals(tempToF, trueOrFalse);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void trueOrFalseNull() {
    Question tempToF = new TrueFalse(null, "True");
  }
  
  @Test
  public void trueOrFalseCompareSame() {
    Question tempToF = new TrueFalse("True or False", "True");
    assertEquals(0, trueOrFalse.compareTo(tempToF));
  }
  
  @Test
  public void trueOrFalseCompareLess() {
    assertEquals(-1, trueOrFalse.compareTo(multipleChoice));
  }
  
  @Test
  public void trueOrFalseAnswerCorrect() {
    String answer = "True";
    assertEquals("Correct", trueOrFalse.answer(answer));
  }
  
  @Test
  public void trueOrFalseAnswerNull() {
    String answer = null;
    assertEquals("Incorrect", trueOrFalse.answer(answer));
  }
  
  @Test
  public void trueOrFalseAnswerIncorrect() {
    String answer = "False";
    assertEquals("Incorrect", trueOrFalse.answer(answer));
  }
  
  @Test
  public void testSortObjects() {
    Question[] questionArrayTemp = new Question[] {
      trueOrFalse, multipleChoice, multipleSelect, likert};
    Arrays.sort(questionArray);
    assertArrayEquals(questionArrayTemp, questionArray);
  }
  
}


