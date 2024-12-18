import stdx.Estring;
import java.util.StringTokenizer;


class IPParse{

	public static void main(String[] args){
	  String [] = new String[4];
	  if(args.length == 0){
	    System.out.println("please enter IP address");
	    return;
	  }
	  if(args.length > 1){
	    System.out.println("only one argument is required");
	    return;
	  }
	  boolean isOK = args[0].matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
	  if(!isOK){
	    System.out.println("ERROR: invalid IP address");
	    return;
	  }
	  StringTokenizer tkn = new StringTokenizer(args[0], ".");
	  while(tkn.hasMoreTokens()){
	    try{
	        String token = tkn.nextToken();
	        int asInt = Integer.parseInt(token);
	        if(asInt < 0 || asInt > 255){
	          System.out.println("Error: invalid octet detected");
	          System.out.println(args[0]);
	          System.out.println(" ".repeat(args[0].indexOf(token)) + "^".repeat(token.length()));
	          return;
	        }
        		System.out.println(asInt);
	    }catch(NumberFormatException e){
	      System.out.println("Error: bad IP address");
	    }
	  }
	}
}
