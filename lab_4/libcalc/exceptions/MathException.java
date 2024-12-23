package libcalc.exceptions;

public class MathException extends Exception {
    MathException(String message){
        super(message);
    }
    MathException(String message, Exception cause){
        super(message, cause);
    }

}
