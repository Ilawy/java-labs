package liblib.exceptions;

public class DuplicateIdException extends Exception {
    public DuplicateIdException(){
        super("Duplicated id found");
    }
}
