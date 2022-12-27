package edu.handong.csee.plt;

import java.util.ArrayList;
import java.util.Stack;

import edu.handong.csee.plt.ast.*;
import edu.handong.csee.plt.ast.App;

public class Parser {

	RBMRCFAE parse(String exampleCode) {
		ArrayList<String> subExpressions = splitExpressionAsSubExpressions(exampleCode);

		// num
		if (subExpressions.size() == 1 && isNumeric(subExpressions.get(0))) {

			return new Num(Integer.parseInt(subExpressions.get(0)));
		}

		// add
		if (subExpressions.get(0).equals("+")) {

			return new Add(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}

		// sub
		if (subExpressions.get(0).equals("-")) {

			return new Sub(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}

		// mul
		if (subExpressions.get(0).equals("*")) {

			return new Mul(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}

		// id
		if (subExpressions.size() == 1 && isId(subExpressions.get(0))) {

			return new Id(subExpressions.get(0));
		}

		// with
		if (subExpressions.get(0).equals("with")) {
			ArrayList<String> withIDAndVal = splitExpressionAsSubExpressions(subExpressions.get(1));

			return new App(new Fun(withIDAndVal.get(0), parse(subExpressions.get(2))), parse(withIDAndVal.get(1)));
		}

		// if0
		if (subExpressions.get(0).equals("if0")) {

			return new if0(parse(subExpressions.get(1)), parse(subExpressions.get(2)), parse(subExpressions.get(3)));
		}

		// setvar
		if (subExpressions.get(0).equals("setvar")) {

			return new Setvar(subExpressions.get(1), parse(subExpressions.get(2)));
		}

		// newbox
		if (subExpressions.get(0).equals("newbox")) {

			return new Newbox(parse(subExpressions.get(1)));
		}

		// setbox
		if (subExpressions.get(0).equals("setbox")) {

			return new Setbox(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}

		// openbox
		if (subExpressions.get(0).equals("openbox")) {

			return new Openbox(parse(subExpressions.get(1)));
		}

		// seqn
		if (subExpressions.get(0).equals("seqn")) {

			return new Seqn(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}

		// rec
		if (subExpressions.get(0).equals("rec")) {

			ArrayList<String> recFunAndExpr = splitExpressionAsSubExpressions(subExpressions.get(1));
			return new Rec(recFunAndExpr.get(0), parse(recFunAndExpr.get(1)), parse(subExpressions.get(2)));
		}

		// fun
		if (subExpressions.get(0).equals("fun")) {

			ArrayList<String> funName = splitExpressionAsSubExpressions(subExpressions.get(1));

			return new Fun(funName.get(0), parse(subExpressions.get(2)));
		}

		// refun
		if (subExpressions.get(0).equals("refun")) {

			ArrayList<String> rfunName = splitExpressionAsSubExpressions(subExpressions.get(1));
			
			return new Refun(rfunName.get(0), parse(subExpressions.get(2)));
		}

		// app
		if (subExpressions.size() == 2) {

			return new App(parse(subExpressions.get(0)), parse(subExpressions.get(1)));
		}
		

		return null;
	}

	private ArrayList<String> splitExpressionAsSubExpressions(String exampleCode) {

		// deal with brackets first.
		if ((exampleCode.startsWith("{") && !exampleCode.endsWith("}"))
				|| (!exampleCode.startsWith("{") && exampleCode.endsWith("}"))) {
			System.out.println("Syntax error");
			System.exit(0);
		}

		if (exampleCode.startsWith("{"))
			exampleCode = exampleCode.substring(1, exampleCode.length() - 1);

		return getSubExpressions(exampleCode);
	}

	/**
	 * This method return a list of sub-expression from the given expression. For
	 * example, {+ 3 {+ 3 4} -> +, 2, {+ 3 4} TODO JC was sleepy while implementing
	 * this method...it has complex logic and might be buggy... You can do better or
	 * find an external library.
	 * 
	 * @param code
	 * @return list of sub expressions
	 */
	private ArrayList<String> getSubExpressions(String code) {

		ArrayList<String> subExpressions = new ArrayList<>();
		Stack<Character> stack = new Stack<>();

		String strBuffer = "";

		for (int i = 0; i < code.length(); i++) {
			if (code.charAt(i) == ' ' && stack.isEmpty()) {
				if (!strBuffer.isEmpty()) {
					subExpressions.add(strBuffer.trim());
					strBuffer = "";
				}
			} else {
				if (code.charAt(i) == '{') {
					stack.add('{');
				} else if (code.charAt(i) == '}' && !stack.isEmpty()) {
					stack.pop();
				}
			}

			strBuffer += code.charAt(i);
		}

		subExpressions.add(strBuffer.trim());

		return subExpressions;

	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
	}

	public static boolean isId(String str) {
		return str.matches("[a-zA-Z][a-zA-Z\\d]*$"); //  match an id with the first char in the alphabet and the rest chars in the alphabet or the number
	}

}
