package polynomial;

/**
 * Represents a term within a polynomial.
 */
public class PolynomialImpl implements Polynomial {
  private PolynomialImpl terms;
  private int coefficient;
  private int exp;
  
  /**
  * Creates an instance of a polynomial object and represents a term.
   * 
   * @param coefficient is the coefficient for this term in the polynomial.
   * @param exp is the exponent for this term in the polynomial.
   * @param terms is the rest of the terms in the rest of the polynomial.
   */
  public PolynomialImpl(int coefficient, int exp, PolynomialImpl terms) {
    if (terms == null) {
      throw new IllegalArgumentException("Polynomial cannot be null.");
    }
    if (exp < 0) {
      throw new IllegalArgumentException("For this "
        + "implementation, negative exponents are not accepted.");
    }
    this.coefficient = coefficient;
    this.exp = exp;
    this.terms = terms;
  }
  
  /**
   * Constructor for an empty polynomial.
   */
  public PolynomialImpl() {
    this.coefficient = 0;
    this.exp = -1;
    this.terms = null;
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("other must be of type PolynomialImpl");
    }
    Polynomial output = new PolynomialImpl();
    PolynomialImpl otherClone = (PolynomialImpl) other;
    
    PolynomialImpl temp = this;
    while (temp.exp != -1) {
      output.addTerm(temp.coefficient, temp.exp);
      temp = temp.terms;
    }
     
    temp = otherClone;
    while (temp.exp != -1) {
      output.addTerm(temp.coefficient, temp.exp);
      temp = temp.terms;
    }
    return output;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("For this implementation, "
         + "negative exponents are not accepted.");
    }
    if (coefficient == 0) {
      return;
    }
    if (this.exp == -1) {
      this.exp = power;
      this.coefficient = coefficient;
      this.terms = new PolynomialImpl();

    } else if (this.exp == power) {
      this.coefficient = this.coefficient + coefficient;
      if (this.coefficient == 0) {
        this.exp = this.terms.exp;
        this.coefficient = this.terms.coefficient;
        this.terms = this.terms.terms;
      }
        
    } else if (this.exp < power) {
      this.terms = new PolynomialImpl(this.coefficient, this.exp, this.terms);
      this.coefficient = coefficient;
      this.exp = power;
        
    } else {
      this.terms.addTerm(coefficient, power);
    }
  }

  @Override
  public boolean isSame(Polynomial poly) {
    if (poly == null) {
      throw new IllegalArgumentException("Polynomial cannot be null.");
    }
    if (!(poly instanceof PolynomialImpl)) {
      return false;
    }
    PolynomialImpl other = (PolynomialImpl) poly;
    
    return isSameCheck(this, other);
  }
  
  private boolean isSameCheck(PolynomialImpl poly1, PolynomialImpl poly2) {
    if (poly1 == null && poly2 == null) {
      return true;
    }
    
    if (poly1 == null || poly2 == null) {
      return false;
    }
    
    if (poly1.coefficient != poly2.coefficient || poly1.exp != poly2.exp) {
      return false;
    }
    
    return isSameCheck(poly1.terms, poly2.terms);
  }

  @Override
  public double evaluate(double x) {
    if (this.exp == -1) {
      return 0;
    }
    double total = 0;
    double firstTerm = this.coefficient * Math.pow(x, this.exp);                                
    total = total + firstTerm;
    
    if (this.terms != null) {
      double rest = this.terms.evaluate(x);
      total = total + rest;
    }
    return total;
  }

  @Override
  public int getCoefficient(int power) {
    if (this.exp == power) {
      return this.coefficient;
    }
    
    if (this.terms == null) {
      return 0;
    }
    return this.terms.getCoefficient(power);
  }

  @Override
  public int getDegree() {
    if (this.exp == -1) {
      throw new IllegalStateException("The degree of this polynomial is undefined.");
    }
    int degree = this.exp;
   
    if (this.terms != null) {
      int restDegree = this.terms.getDegree();
      if (restDegree > degree) {
        degree = restDegree;
      }
    }
    return degree;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    PolynomialImpl temp = this;
    
    while (temp != null) {
      if (temp.coefficient != 0) {
        if (sb.length() > 0 && temp.coefficient > 0) {
          sb.append(" + ");
        }
        sb.append(temp.coefficient);
        if (temp.exp > 0) {
          sb.append("x^").append(temp.exp);
        }
      }
      temp = temp.terms;
    }
    if (sb.length() == 0) {
      return "0";
    }
    return sb.toString().trim();
  }
}

