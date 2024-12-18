class ThirdApp{
	public static void main(String []args){
		if(args.length != 2){
			System.out.println("two arguments are required");
			return;
		}
		try{
			int times = Integer.parseInt(args[0]);
			if(times <= 0){
				System.out.println("positive, non-zero number is required");
				return;				
			}
			for(int i = 0; i < times; i++){
				System.out.println(args[1]);
			}
		}
		catch(NumberFormatException e){
				System.out.println("first argument should be integer");
		}
	}
}
