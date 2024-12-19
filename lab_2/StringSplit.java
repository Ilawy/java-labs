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
				paragraph = "ITI ITI ITI ITI develops people and ITI house of developers and ITI for people";
				target = "ITI";
			}
		}
		int result = Estring.findOccurs(paragraph, target);
		int result2 = Estring.findOccurs2(paragraph, target);
		int result3 = Estring.findOccurs3(paragraph, target);
		int result4 = Estring.findOccurs4(paragraph, target);

		System.out.println("Splitting by target: " + result);
		System.out.println("Using indexof: " + result2);
		System.out.println("Using pattern matching: " + result3);
		System.out.println("=======(exact match)======");
		System.out.println("Using space as delimiter: " + result4);

	}
}
