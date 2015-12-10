import java.util.ArrayList;

public class Expression implements LogicalExpression {
	String input;
	ArrayList<Boolean> truth;
	String output;
	
	/**
	 * Constructor that creates a logical expression
	 * @param input The string representing a logical expression
	 */
	public Expression(String input) {
		this.input = input;
		output = LogicalExpression.shunting(this.input);
		truth = LogicalExpression.evaluate(output, 0);
	}

	/**
	 * Determines if the logical expression is valid
	 * Valid means true for all possibilities
	 */
	@Override
	public boolean valid() {
		if(truth.contains(false)) {
			return false;
		}
		return true;
	}

	/**
	 * Determines if the logical expression is satisfiable
	 * Satisfiable means that some input will evaluate to true and some to false
	 */
	@Override
	public boolean satisfiable() {
		if(truth.contains(false) && truth.contains(true)) {
			return true;
		}
		return false;
	}

	
	/**
	 * Determines if the logical expression is contingent
	 * Contingent means that it is ever true
	 * Implemented with satisfiable and valid
	 */
	@Override
	public boolean contingent() {
		if(this.valid() || this.satisfiable()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if this logical expression entails the parameter 
	 * Concatenates the expressions and checks valid on the new expression
	 * Combination is of the form l | ~(this)
	 */
	@Override
	public int entails(LogicalExpression l) {
		Expression combined = (Expression) LogicalExpression.concat(this, l);
		if(combined.valid()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Determines if this expression and the parameter are equivalent
	 * Uses two entailments run opposite ways
	 */
	@Override
	public int equivalent(LogicalExpression l) {
		if(this.entails(l) == 1 && l.entails(this) == 1) {
			return 1;
		}
		else if(this.entails(l) == -1 || l.entails(this) == -1) {
			return -1;
		}
		return 0;
	}

	/**
	 * Returns the string that was inputed in the constructor
	 */
	@Override
	public String getString() {
		return input;
	}
}
