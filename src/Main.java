public class Main {
	public static void main(String[] args) {
		Expression l1 = new Expression("a");
		Expression l2 = new Expression("b");
		System.out.println(LogicalExpression.concat(l1,l2).getString());
	}
}