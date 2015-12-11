import java.util.ArrayList;

interface LogicalExpression {
	final String letters = "abcdefghijklmnopqrstuvwxyz";
	final String operators = "~|&";
	
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
		String uniques = numUnique(polish);
		ArrayList<Boolean> truth = new ArrayList<Boolean>(uniques.length());
		boolean[][] table = truthTable(uniques.length());
		for(int i = 0; i < Math.pow(2, uniques.length()); i++) {
			String tempString = new String(polish);
			for(int j = 0; j < uniques.length(); j++) {
				String s;
				if(table[i][j]) {
					s = new String("T");
				}
				else {
					s = new String("F");
				}
				tempString.replaceAll(String.valueOf(uniques.charAt(j)), s);
			}
			truth.add(i, simplify(tempString));
		}

		return truth;
	}
	
	static boolean simplify(String polish) {
		String polish1 = polish;
		while(true) {
			for(int i = 0; i < polish1.length() - 2; i++) {
				if(polish1.charAt(i) == 'T' || polish1.charAt(i) == 'F') {
					if(polish1.charAt(i + 1) == 'T' || polish1.charAt(i + 1) == 'F') {
						if(operators.contains(String.valueOf(polish1.charAt(i + 2)))) {
							if(polish1.charAt(i + 2) == '|') {
								if(polish1.charAt(i) == 'F' || polish1.charAt(i+1) == 'F') {
									polish1 = "F" + polish1.substring(i + 3);
								}
								else {
									polish1 = "T" + polish1.substring(i + 3);
								}
							}
							else {
								if(polish1.charAt(i) == 'T' && polish1.charAt(i+1) == 'T') {
									polish1 = "T" + polish1.substring(i + 3);
								}
								else {
									polish1 = "F" + polish1.substring(i + 3);
								}
							}
						}
					}
				}
				else if(polish1.charAt(i + 1) == '~') {
					if(polish1.charAt(i) == 'T') {
						polish1 = "F" + polish1.substring(i + 2);
					}
					else {
						polish1 = "T" + polish1.substring(i + 2);
					}
				}
			}
			if(polish1.length() <= 1) {
				break;
			}
		}
		if(polish1 == "T") {
			return true;
		}
		return false; 
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