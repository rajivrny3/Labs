package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import polynomial.Polynomial;
import polynomial.PolynomialImpl;

/**
 * Creates a test class for Polynomial.
 */
public class PolynomialTest {
  private Polynomial poly;
  private Polynomial empty;
  private Polynomial differentDegree;
  private Polynomial negative;
  private Polynomial numericTerm;
  
  /**
   * Sets up objects to test on for PolynomialTest.
   */
  @Before
  public void setup() {
    empty = new PolynomialImpl();
    
    poly =  new PolynomialImpl(4, 3, 
        new PolynomialImpl(3, 1, 
            new PolynomialImpl(-5, 0, new 
                PolynomialImpl())));
    
    numericTerm = new PolynomialImpl(-102, 0, new PolynomialImpl());
    
    negative = new PolynomialImpl(-3, 4, 
        new PolynomialImpl(-2, 5, 
            new PolynomialImpl(-5, 0, new PolynomialImpl(-2, 4, 
                new PolynomialImpl(11, 1, 
                    new PolynomialImpl())))));
    
    differentDegree = new PolynomialImpl(3, 5, 
        new PolynomialImpl(2, 2, new PolynomialImpl()));
  }
  
  @Test
  public void emptyPolynomialImpl() {
    assertEquals("0", empty.toString());
  }
  
  @Test
  public void testPoly() {
    assertEquals("4x^3 + 3x^1-5", poly.toString());
  }
}
