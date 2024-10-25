package polynomial;

public class PolynomialImpl implements Polynomial {
  private int coefficient;
  private int exp;
  PolynomialImpl terms;
	
  public PolynomialImpl(int coefficient, int exp, PolynomialImpl terms) {
    if (exp < 0 ) {
      throw new IllegalArgumentException("For this implementation, negative exponents are not accepted.");
    }
    this.coefficient = coefficient;
    this.exp = exp;
    this.terms = terms;

	@Override
	public Polynomial add(Polynomial other) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTerm(int coefficient, int power) throws IllegalArgumentException {
		// TODO Auto-generated method stub

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
