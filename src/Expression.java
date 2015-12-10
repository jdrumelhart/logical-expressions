import java.util.ArrayList;

public class Expression implements LogicalExpression {
	String input;
	ArrayList<Boolean> truth;
	ArrayList<Character> characters;
	
	
	public Expression(String input) {
		this.input = input;
		characters = LogicalExpression.shunting(this.input);
		truth = LogicalExpression.evaluate(characters);
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

	@Override
	public String getString() {
		return null;
	}
}
