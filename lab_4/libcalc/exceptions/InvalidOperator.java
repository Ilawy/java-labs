package libcalc.exceptions;

public class InvalidOperator extends MathException {
    public InvalidOperator(String message, Exception cause){
		super(message, cause);
	}
    public InvalidOperator(String msg){
		super(msg);
	}
}
