import libcalc.exceptions.ArgumentIsNotValidNumber;
import libcalc.exceptions.DivideByZeroException;
import libcalc.exceptions.InvalidArgumentCount;
import libcalc.exceptions.InvalidOperator;

public class Calc {
    public static void main(String []args){
        libcalc.Calc c = new libcalc.Calc();
        try{
            double result = c.evaluate(args);
            System.out.println(result);
        }catch(InvalidOperator e){
            System.out.println("Invalid operator found: " + e.getMessage());
        }catch(InvalidArgumentCount e){
            System.out.println(e.getMessage());
        }catch(ArgumentIsNotValidNumber e){
            System.out.println(e.getMessage());
        }catch(DivideByZeroException e){
            System.out.println("Cannot devide by zero");
        }

    }
}
