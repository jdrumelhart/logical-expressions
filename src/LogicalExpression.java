import java.util.ArrayList;

interface LogicalExpression {
	
	boolean valid();
	
	boolean satisfiable();
	
	boolean contingent();
	
	String getString();
	
	static ArrayList<Character> shunting(String s) {
		return null;
	}
	
	static ArrayList<Boolean> evaluate(ArrayList<Character> input) {
		return null;
	}
	
	static int entails(LogicalExpression l1, LogicalExpression l2) {
		String s = new String(l1.getString() + l2.getString());
		return 0;
	}
	
	public static int equivalent(LogicalExpression l1, LogicalExpression l2) {
		if(LogicalExpression.entails(l1,l2) == 1 && LogicalExpression.entails(l2,l1) == 1) {
			return 1;
		}
		else if(LogicalExpression.entails(l1,l2) == -1 || LogicalExpression.entails(l2,l1) == -1) {
			return -1;
		}
		return 0;
	}
}