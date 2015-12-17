import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a logical expression: ");
		String a = input.nextLine();
		Expression s = new Expression(a);
		System.out.println("Enter a logical expression: ");
		String b = input.nextLine();
		Expression z = new Expression(b);
		System.out.println(z.entails(s));
		System.out.println(s.entails(z));
		System.out.println(z.equivalent(s));
		/*
		String r = "ab|";
		ArrayList<Boolean> z = LogicalExpression.evaluate(r);
		for(int i = 0; i < z.size(); i++) {
			System.out.println(z.get(i));
		}
		String t = "(a|b)&c|~d";
		t = LogicalExpression.shunting(t);
		System.out.println(t);
		*/
	}
}