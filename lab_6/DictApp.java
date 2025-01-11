import dict.Dict;
import dict.exceptions.SectionNotFoundException;
import dict.exceptions.TermNotFoundException;

public class DictApp {
    
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Exactly one argument is required");
            return;
        }
        Dict d = new Dict();
        String word = args[0].toLowerCase();
        try{
            d.define(word);
        }catch(SectionNotFoundException | TermNotFoundException e){
            System.out.println("Term cannot be found, try another one");
        }
    }
}