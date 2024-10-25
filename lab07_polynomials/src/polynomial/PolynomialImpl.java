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
    return null;
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
		// TODO Auto-generated method stub
		return false;
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
