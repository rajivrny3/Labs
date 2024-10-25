package polynomial;

public class PolynomialImpl implements Polynomial {
  private int coefficient;
  private int exp;
  PolynomialImpl terms;
	
  public PolynomialImpl(int coefficient, int exp, PolynomialImpl terms) {
    if (exp < 0 ) {
      throw new IllegalArgumentException("For this implementation, negative exponents are not accepted.");
    }
    this.coefficient = 0;
    this.exp = 0;
    this.terms = null;
  }
  
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
     while(temp.exp != -1) {
       output.addTerm(temp.coefficient, temp.exp);
       temp = temp.terms;
     }
     
     temp = otherClone;
     while(temp.exp != -1) {
       output.addTerm(temp.coefficient, temp.exp);
       temp = temp.terms;
     }
     return output;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("For this implementation, negative exponents are not accepted.");
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCoefficient(int power) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDegree() {
		// TODO Auto-generated method stub
		return 0;
	}

}
