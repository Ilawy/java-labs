
public class LetterValidator {
    static boolean isAllLetters(String data){
        boolean found = true;
        for(char code: data.toCharArray()){
            if(!((code >= 97) && (code <= 122)) || ((code >= 65) && (code <= 90))){
                found = false;
                break;
            }
        }
        return found;
    }


    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("only one argument is required");
            return;
        }
        System.out.println(isAllLetters(args[0]) ? "yes" : "no");
    }
}
