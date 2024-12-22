
public class Lab31 {
    public static void main(String args[]){
        if(args.length != 1){
            System.out.println("One argument is required");
            return;
        }
        try{
            double input = Double.parseDouble(args[0]);
            ma.ITempConv<Double> convert = t-> t.doubleValue() * (9/5) + 32;
            // ma.TempConv<Double> t = new ma.TempConv<Double>();
            System.out.println(convert.apply(input));;
            return;
        }catch(NumberFormatException e){
            System.out.println("Bad number, please enter a valid one");
            return;
        }

    }
}
