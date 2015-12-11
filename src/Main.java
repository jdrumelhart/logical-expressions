public class Main {
	public static void main(String[] args) {
		/*
		Expression l1 = new Expression("a");
		Expression l2 = new Expression("b");
		System.out.println(LogicalExpression.concat(l1,l2).getString());
		boolean[][] f = LogicalExpression.truthTable(4);
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(f[i][j] + " ");
			}
			System.out.println();
		}
		*/
		String s = "abcc7";
		System.out.println(LogicalExpression.numUnique(s));
	}
}