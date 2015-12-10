import java.util.ArrayList;

public class Expression implements LogicalExpression {
	ArrayList<Boolean> truth;
	ArrayList<Character> characters;
	int variables;
	
	
	public Expression(String input) {
		
		truth = new ArrayList<Boolean>(variables);
		characters = new ArrayList<Character>(0);
	}

	@Override
	public boolean valid() {
		if(truth.contains(false)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean satisfiable() {
		if(truth.contains(false) && truth.contains(true)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contingent() {
		if(this.valid() || this.satisfiable()) {
			return true;
		}
		return false;
	}
}
