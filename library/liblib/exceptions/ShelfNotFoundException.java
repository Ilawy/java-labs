package liblib.exceptions;

public class ShelfNotFoundException extends Exception {
    public ShelfNotFoundException(int idx){
        super("Shelf " + idx + " not found");
    }
}
