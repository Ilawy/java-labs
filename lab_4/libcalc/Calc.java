package libcalc;

import libcalc.exceptions.ArgumentIsNotValidNumber;
import libcalc.exceptions.DivideByZeroException;
import libcalc.exceptions.InvalidArgumentCount;
import libcalc.exceptions.InvalidOperator;
import libcalc.exceptions.PowerOfNegative;
import libcalc.exceptions.SqrtOfNegative;
// import libcalc.exceptions.SqrtOfNegative;

public class Calc {
	public double evaluate(String[] args) throws InvalidArgumentCount, ArgumentIsNotValidNumber, InvalidOperator, DivideByZeroException {
		// System.out.println(Arrays.toString(args));
		if (args.length == 0)
			throw new InvalidArgumentCount("No arguments found");
		if (args[0].equals("sqrt")) {
			try {
				if (args.length != 2)
					throw new InvalidArgumentCount("required 2 arguments, found " + args.length);
				double arg = asDouble(args[1]);
				if (arg < 0)
					throw new SqrtOfNegative();
				return Math.sqrt(asDouble(args[1]));
			} catch (NumberFormatException | InvalidOperator e) {
				throw new ArgumentIsNotValidNumber("Argument is not a number", e);
			}
		} else if (args[0].equals("pow")) {
			try {
				if (args.length != 2)
					throw new InvalidArgumentCount("required 2 arguments, found " + args.length);
				double arg = asDouble(args[1]);
				if (arg < 0)
					throw new PowerOfNegative();
				return Math.pow(asDouble(args[1]), 2);
			} catch (NumberFormatException | InvalidOperator e) {
				throw new ArgumentIsNotValidNumber("Argument is not a number", e);
			}
		} else {
			if (args.length != 3)
				throw new InvalidArgumentCount("required 3 arguments, found " + args.length);
			try {
				double lOperand = asDouble(args[0]);
				String operator = args[1];
				double rOperand = asDouble(args[2]);
				switch (operator) {
					case "+":
						return lOperand + rOperand;
					case "-":
						return lOperand - rOperand;
					case "x":
						return lOperand * rOperand;
					case "/":{
						if(rOperand == 0){
							throw new DivideByZeroException();
						}
						return lOperand / rOperand;
					}
					default:
						throw new InvalidOperator("Operator " + operator + "");
				}

			} catch (InvalidOperator e) {
				throw e;
			}
		}

	}

	static double asDouble(String value) throws InvalidOperator {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			throw new InvalidOperator("Argument of value " + value + " is not a number", e);
		}
	}
}
