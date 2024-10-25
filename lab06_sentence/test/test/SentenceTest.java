package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import sentence.EmptyNode;
import sentence.PunctuationNode;
import sentence.Sentence;
import sentence.WordNode;

/**
 * A class for testing the classes within the
 * sentence package.
 */
public class SentenceTest {
  private Sentence normal;
  private Sentence punctuated;
  private Sentence empty;
  private Sentence ellipse;

  /**
   * Sets up the test environment for the testing the
   * classes within the sentence package.
   */
  @Before
  public void setUp() {
    empty = new EmptyNode();
    
    normal = new WordNode("This",
        new WordNode("is",
            new WordNode("a", 
                new WordNode("sentence", 
                    new PunctuationNode(".",
                        new EmptyNode())))));
    
    punctuated = new PunctuationNode(".",
                  new WordNode("This",
                 new PunctuationNode(",",
                 new WordNode("or",
                    new WordNode("that",
                        new PunctuationNode(".",
                            new EmptyNode()))))));
    
    ellipse = new WordNode("Wait",
              new PunctuationNode(".",
              new PunctuationNode(".",
                new PunctuationNode(".",
                    new WordNode("Done", 
                        new PunctuationNode("!", 
                            new EmptyNode()))))));
  }

  @Test
  public void wordNodeToString() {
    assertEquals("This is a sentence.", normal.toString());
  }
  
  @Test
  public void punctuationNodeToString() {
    assertEquals(". This, or that.", punctuated.toString());
  }
  
  @Test
  public void emptyNodeToString() {
    assertEquals("", empty.toString());
  }
  
  @Test
  public void multiplePunctuatedToString() {
    assertEquals("Wait... Done!", ellipse.toString());
  }
  
  @Test
  public void getNumberOfWordsTestWordNode() {
    assertEquals(4, normal.getNumberOfWords());
  }
  
  @Test
  public void getNumberOfWordsTestPunctuationNode() {
    assertEquals(3, punctuated.getNumberOfWords());
  }
  
  @Test
  public void getNumberOfWordsTestEmptyNode() {
    assertEquals(0, empty.getNumberOfWords());
  }
  
  @Test
  public void getLongestWordTest() {
    assertEquals("sentence", normal.longestWord());
  }
  
  @Test
  public void getLongestWordTestSameLength() {
    assertEquals("Wait", ellipse.longestWord());
  }
  
  @Test
  public void getLongestWordEmpty() {
    assertEquals("", empty.longestWord());
  }
  
  @Test
  public void testMerge() {
    Sentence merged = normal.merge(punctuated);
    assertEquals("This is a sentence.. This, or that.", merged.toString());
  }
  
  @Test
  public void testMergeWithEmpty() {
    Sentence merged = normal.merge(empty);
    assertEquals(normal.toString(), merged.toString());
  }
  
  @Test
  public void testTwoEmptyMerge() {
    Sentence merged = empty.merge(empty);
    assertEquals("", merged.toString());
  }
  
  @Test
  public void testDuplicate() {
    Sentence dupe = normal.duplicate();
    assertEquals(dupe.toString(), normal.toString());
  }
  
  @Test
  public void testDupeWithPunc() {
    Sentence dupe = punctuated.duplicate();
    assertEquals(punctuated.toString(), punctuated.toString());
  }
  
  @Test
  public void testEmptyDupe() {
    Sentence dupe = empty.duplicate();
    assertEquals(dupe.toString(), empty.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullWord() {
    new WordNode(null, empty);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullSen() {
    new WordNode("This", null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullPunc() {
    new PunctuationNode(null, empty);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullmerge() {
    normal.merge(null);
  }
  
}
