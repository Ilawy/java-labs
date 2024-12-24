// package lab_3;
import ma.Quadratic;
import java.util.List;



// class QuadraticResult


public class Lab32 {
  
    public static void main(String[] args){
      if(args.length != 3){
        System.out.println("Exactly 3 arguments are required");
        return;
      }
      try{
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double c = Double.parseDouble(args[2]);
        List<Double> result = Quadratic.Q(1, -5, 6);
      }catch(Exception e){
        
      }
    }
    
}