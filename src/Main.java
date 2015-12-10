public class Main {
	public static void main(String[] args) {
		Expression l1 = new Expression(null);
		Expression l2 = new Expression(null);
		System.out.println(LogicalExpression.entails(l1, l2));
	}
}