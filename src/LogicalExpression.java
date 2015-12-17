import java.util.ArrayList;
import java.util.Stack;

interface LogicalExpression {
	final String letters = "abcdefghijklmnopqrstuvwxyz";
	final String operators = "~&|()";
	
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
		s.replaceAll("\\s","");
		char[] c = s.toCharArray();
		String output = new String();
		Stack<Character> opStack = new Stack<Character>();
		for(int i = 0; i < c.length; i++) {
			if(letters.indexOf(c[i]) != -1) {
				output += (c[i]);
			}
			else if(operators.indexOf(c[i]) != -1 && operators.indexOf(c[i]) < 3) {
				while(opStack.size() > 0 && (operators.indexOf(c[i]) >= operators.indexOf(opStack.peek()))) {
					output += opStack.pop();
				}
				opStack.push(c[i]);
			}
			else if(operators.indexOf(c[i]) == 3) {
				opStack.push(c[i]);
			}
			else if(operators.indexOf(c[i]) == 4) {
				while(operators.indexOf(opStack.peek()) != 3) {
					output += opStack.pop();
				}
				if(opStack.size() > 0 && operators.indexOf(opStack.peek()) == 3) {
					opStack.pop();
				}
				else {
					return null;
				}
			}
		}
		while(opStack.size() > 0) {
			output += opStack.pop();
		}
		return output;
	}
	
	/**
	 * Evaluates all values of the given expression
	 * @param polish String in reverse polish notation
	 * @return an arraylist of booleans that represents the results of the evaluations
	 */
	static ArrayList<Boolean> evaluate(String polish) {
		String uniques = numUnique(polish);
		ArrayList<Boolean> truth = new ArrayList<Boolean>(uniques.length());
		boolean[][] table = truthTable(uniques.length());
		for(int i = 0; i < Math.pow(2, uniques.length()); i++) {
			String s = replace(table, polish, uniques, i);
			truth.add(i, simplify(s));
		}

		return truth;
	}
	
	static String replace(boolean[][] table, String polish, String uniques, int i) {
		String tempString = new String(polish);
		for(int j = 0; j < uniques.length(); j++) {
			String s;
			if(table[i][j]) {
				s = new String("T");
			}
			else {
				s = new String("F");
			}
			tempString = tempString.replaceAll(String.valueOf(uniques.charAt(j)), s);
		}
		return tempString;
	}
	
	static Boolean simplify(String s) {
		Stack<Character> result = new Stack<Character>();
		char[] c = s.toCharArray();
		for(int i = 0; i < c.length; i++) {
			if(c[i] == 'T' || c[i] == 'F') {
				result.push(c[i]);
			}
			else if(c[i] == '~') {
				result.push(not(result.pop()));
			}
			else if(c[i] == '&') {
				result.push(and(result.pop(), result.pop()));
			}
			else if(c[i] == '|') {
				result.push(or(result.pop(), result.pop()));
			}
		}
		if(result.size() > 1) {
			return null;
		}
		else {
			if(result.pop() == 'T') {
				return true;
			}
			return false;
		}
	}
	
	static Character not(Character pop) {
		if(pop == 'T') {
			return 'F';
		}
		else if(pop == 'F') {
			return 'T';
		}
		return null;
	}
	
	static Character and(Character p1, Character p2) {
		if(p1 == 'T' && p2 == 'T') {
			return 'T';
		}
		return 'F';
	}
	
	static Character or(Character p1, Character p2) {
		if(p1 == 'F' && p2 == 'F') {
			return 'F';
		}
		return 'T';
	}

	static String numUnique(String polish) {
		String letter1 = new String();
		String letter2 = new String(letters);
		for(int i = 0; i < polish.length(); i++) {
			if(letter2.contains(polish.substring(i,i+1)) && !(letter1.contains(polish.substring(i,i+1)))) {
				letter1+=polish.substring(i,i+1);
			}
		}
		return letter1;
	}
	
	/**
	 * Creates a truth table 
	 * @param variables number of variable the truth table is for
	 */
	static boolean[][] truthTable(int unique) {
		boolean[][] table = new boolean[(int) Math.pow(2,unique)][unique];
		
		boolean alternator = true;
		for(int c = 1; c <= unique; c++) {
			for(int i = 0; i < Math.pow(2,c); i++) {
				for(int j = 0; j < Math.pow(2, unique - c); j++) {
					table[(int) (i *  Math.pow(2, unique - c) + j)][c - 1] = alternator;
				}
				alternator = !(alternator);
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