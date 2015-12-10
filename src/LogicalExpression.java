interface LogicalExpression {
	
	boolean valid();
	
	boolean satisfiable();
	
	boolean contingent();
	
	static int entails(LogicalExpression l1, LogicalExpression l2) {
		return 1;
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