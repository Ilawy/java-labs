package libcalc.exceptions;

public class InvalidArgumentCount extends MathException {
    public InvalidArgumentCount(String msg){
        super(msg);
    }
    
    InvalidArgumentCount(String msg, Exception cause){
        super(msg, cause);
    }
}
