import java.sql.Array;
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
	static ArrayList<Boolean> evaluate(String polish, int variables) {
		boolean[][] table = truthTable(variables, (int)Math.pow(2, variables));
		return null;
	}
	
	/**
	 * Creates a truth table 
	 * @param variables number of variable the truth table is for
	 */
	static boolean[][] truthTable(int x, int y) {
		boolean[][] table = new boolean[x][y];
		for(int i = 0; i < y; i++) {
			if(i < ((y)/ 2)) {
				table[0][i] = true;
			}
			else {
				table[0][i] = false;
			}
		}
		if(y > 1) {
			boolean[][] temp = truthTable(x,y/2);
			for(int i = 0; i < y/2; i++) {
				table[1][i] = temp[0][i];
				table[1][i + y/2] = temp[0][i];
			}
		}
		
		return table;
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