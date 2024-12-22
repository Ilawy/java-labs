package ma;

import java.util.Arrays;
import java.util.List;

public class Quadratic {

    public static <T extends Number> List<Double> Q( T a, T b, T c){
      double aVal = a.doubleValue();
      double bVal = b.doubleValue();
      double cVal = c.doubleValue();
      
      double d = bVal * bVal - 4 * aVal * cVal;
      double sqrt = Math.sqrt(Math.abs(d));

      if(d==0){
        //same
        double result = (-1 * bVal) / (2 * 1); 
        return Arrays.asList(result);
      }
      if(d > 0){
        //not the same
        double pos =  (-bVal + sqrt) / (2 * aVal);
        double neg =  (-bVal - sqrt) / (2 * aVal);
        return Arrays.asList(neg, pos);
      }
      return Arrays.asList(Double.NaN);
    }
}
