import java.util.ArrayList;

public class Expression implements LogicalExpression {
	ArrayList<Boolean> truth;
	int variables;
	
	
	public Expression(String input) {
		
		truth = new ArrayList<Boolean>(variables);
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
	public int entails(LogicalExpression l) {
		if(truth.size() != l.getTruth().size()) {
			return 0;
		}
		else {
			for(int i = 0; i < truth.size(); i++) {
				if(this.truth.get(i) == true && l.getTruth().get(i) != true) {
					return -1;
				}
			}
			return 0;
		}
	}

	@Override
	public int equivalent(LogicalExpression l) {
		if(this.entails(l) == 1 && l.entails(this) == 1) {
			return 1;
		}
		else if(this.entails(l) == -1 || l.entails(this) == -1) {
			return -1;
		}
		return 0;
	}
	
	@Override
	public ArrayList<Boolean> getTruth() {
		return truth;
	}
}
