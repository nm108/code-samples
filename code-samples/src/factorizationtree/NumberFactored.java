package src.factorizationtree;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Transforms a number into a Factorization Tree, with divisors list.
 *
 * @author Andrzej Wysocki (neomahakala108@gmail.com)
 * 
 **/
public class NumberFactored {

	// state.

	private final long val;
	private final boolean useThreshold;
	private final List<NumberFactored> divisors = new LinkedList<NumberFactored>();

	// constructor methods.

	/**
	 * @author Andrzej Wysocki.
	 * 
	 * @param v number to factor.
	 */
	public NumberFactored(final long v) {
		this(v, true);
	}

	/**
	 * @author Andrzej Wysocki.
	 * 
	 * @param v number to factor.
	 * 
	 * @param useThreshold to find factorization of number v, we do not need to check
	 *		divisors below a value of: Math.floor(Math.sqrt(val)). In these cases we stop
	 *		looking for more divisors once the threshold value is reached.
	 * 
	 * @throws IllegalArgumentException thrown when number to factor is less than 1.
	 */
	public NumberFactored(final long v, final boolean useThreshold) {
		if (v <= 0) {
			throw new IllegalArgumentException("number to factor must be greater than 0");
		}
		this.val = v;
		this.useThreshold = useThreshold;
		this.initiateDivisors();
	}

	public NumberFactored(final NumberFactored in) {
		this(in.val, in.useThreshold);
	}

	// methods.

	public void initiateDivisors() {
		if (val <= 0) {
			throw new IllegalArgumentException();
		}

		final long limit = useThreshold ? (long) Math.floor(Math.sqrt(val)) : val;

		for (long i = 2; i <= limit; i++) {
			if ((val % i) == 0) {
				final NumberFactored divisor = new NumberFactored(i);
				if (divisor.isPrimeNumber()) divisors.add(divisor);
			}
		}
		
		if (divisors.isEmpty()) { divisors.add(this); };
	
	}

	public long getVal() {
		return val;
	}


	public boolean isPrimeNumber() {
		
		for (NumberFactored divisor : divisors) {
			
			if (divisor.getVal() != 1 && divisor.getVal() != getVal() ) return false;
		}
		return true;
	}
	
	public int getDivisorsAmount() {
		return divisors.size();
	}
	
	public StringBuilder getTreeAsStringBuilder() {
		final StringBuilder result = new StringBuilder();

		result.append(getVal());
		result.append(" number factored:\n");

		result.append(getTreeAsStringBuilder(1));

		return result;
	}

	public StringBuilder getGeneralStateAsStringBuilder(boolean cutBounds) {
		final StringBuilder result = new StringBuilder();
		result.append("General state:\n");
		result.append("  Use Threshold: ");
		result.append(useThreshold);
		result.append(";\n");
		result.append("  Cut Bounds: ");
		result.append(cutBounds);
		result.append(";\n");
		result.append(getDivisorsStateAsStringBuilder(cutBounds));
		return result;
	}

	public List<NumberFactored> getClonedLocalDivisorsAsList(final boolean cutBounds) {
		final int amount = this.getDivisorsAmount();
		final List<NumberFactored> result = new LinkedList<NumberFactored>();
		for (int i = 0; i < amount; i++) {
			if (((divisors.get(i).val == val) || (divisors.get(i).val == 1)) && cutBounds) {
				continue;
			}
			final NumberFactored num = new NumberFactored(divisors.get(i)); // 'deep copy'.
			result.add(num);
		}

		return result;
	}

	public StringBuilder getClonedLocalDivisorsAsStringBuilder(final boolean cutBounds) {
		final List<NumberFactored> localClonedDivisorsList =
				getClonedLocalDivisorsAsList(cutBounds);
	
		final int amountOfDivisors = localClonedDivisorsList.size();
		
		final StringBuilder result = new StringBuilder();
		result.append("[");
		for (int i = 0; i < amountOfDivisors; i++) {
			result.append(" ");
			result.append(localClonedDivisorsList.get(i).getVal());
			if (i < amountOfDivisors - 1) {
				result.append(",");
			}
		}
		result.append(" ];\n");

		return result;
	}

	public StringBuilder getDivisorsStateAsStringBuilder(final boolean cutBounds) {
		final StringBuilder result = new StringBuilder();

		result.append("  Divisors amount: ");
		result.append(getClonedLocalDivisorsAsList(cutBounds).size());
		result.append(";\n");

		result.append("  Divisors List: ");
		result.append(getClonedLocalDivisorsAsStringBuilder(cutBounds));

		return result;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(getVal());
		return result.toString();
	}

	private StringBuilder getIndentedNumberToFactorAsStringBuilder(long inputNumber, int halfNumberOfSpaces) {
		final StringBuilder result = new StringBuilder();

		for (long i = 0; i < halfNumberOfSpaces; i++) {
			result.append("  ");
		}
		result.append(inputNumber);

		return result;
	}

	private StringBuilder getTreeAsStringBuilder(final int indent) {
		final StringBuilder result = new StringBuilder();
		for (final NumberFactored divisor : this.divisors) {
			
			if (isPrimeNumber()) {
				result.append(getIndentedNumberToFactorAsStringBuilder(val, indent));
				result.append(" is a prime number;");
				result.append("\n");
				break;
			}

			if (divisor.getVal() == 1 || divisor.getVal() == val) {
				continue;
			}

			result.append(getIndentedNumberToFactorAsStringBuilder(val, indent));
			result.append('/');
			result.append(divisor.val);
			result.append('=');
			result.append(val / divisor.val);
			result.append(";\n");
			
			
			result.append(new NumberFactored(val/divisor.getVal()).getTreeAsStringBuilder(indent+1));
		}
		return result;
	}
}
