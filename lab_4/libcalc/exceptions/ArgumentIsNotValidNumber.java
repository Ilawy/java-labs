package libcalc.exceptions;

public class ArgumentIsNotValidNumber extends MathException {
    ArgumentIsNotValidNumber(String message){
        super(message);
    }
    
    public ArgumentIsNotValidNumber(String message, Exception cause){
        super(message);
    }
}
