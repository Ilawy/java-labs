package libcalc.exceptions;

public class DivideByZeroException extends ArithmeticException {
    DivideByZeroException(String arg){
        super(arg);
    }
    
    public DivideByZeroException(){
    }
}
