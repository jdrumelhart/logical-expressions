interface LogicalExpression {
	
	boolean valid();
	
	boolean satisfiable();
	
	boolean contingent();
	
	int entails(LogicalExpression l);
	
	int equivalent(LogicalExpression l);
}