import stdx.Estring;


class StringSplit{

	public static void main(String[] args){
		String paragraph, target;
		if(args.length == 2){
			paragraph = args[1];
			target = args[0];
		}else{
			if(args.length > 0){
				System.out.println("non or 2 arguments are required");
				return;
			}else{
				paragraph = "ITIITI ITI ITI develops people and ITI house of developers and ITI for people";
				target = "ITI";
			}
		}
		System.out.println(paragraph + ", " + target);
		int result = Estring.FindOccurs(paragraph, target);
		int result2 = Estring.FindOccurs2(paragraph, target);
		int result3 = Estring.FindOccurs3(paragraph, target);
		int result4 = Estring.FindOccurs4(paragraph, target);

		System.out.println("Result 1: " + result);
		System.out.println("Result 2: " + result2);
		System.out.println("Result 3: " + result3);
		System.out.println("Result 4: " + result4);

	}
}
