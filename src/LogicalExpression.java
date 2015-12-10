import java.util.ArrayList;

interface LogicalExpression {
	
	boolean valid();
	
	boolean satisfiable();
	
	boolean contingent();
	
	/**
	 * Converts the inputed string into a reverse polish string
	 * removes parentheses
	 * @param s the inputed string in infix notation
	 * @return a string in reverse polish notation
	 */
	static String shunting(String s) {
		
		return s;
	}
	
	/**
	 * Evaluates all values of the given expression
	 * @param polish String in reverse polish notation
	 * @return an arraylist of booleans that represents the results of the evaluations
	 */
	static ArrayList<Boolean> evaluate(String polish) {
		return null;
	}
	
	/**
	 * Creates a truth table 
	 * @param variables number of variable the truth table is for
	 */
	static void truthTable(int variables) {
		boolean[][] table = new boolean[variables][(int) Math.pow(2, variables)];
	}
	
	/**
	 * Concatenates two logical expressions to b | ~(a)
	 * @param l1 the a expression
	 * @param l2 the b expression
	 * @return a new string of form b | ~(a)
	 */
	static LogicalExpression concat(LogicalExpression l1, LogicalExpression l2) {
		String s = (l2 + "|~(" + l1 + ")");
		Expression r = new Expression(s);
		return r;
	}
	
	int entails(LogicalExpression l);
	
	int equivalent(LogicalExpression l);
	
	String getString();
}