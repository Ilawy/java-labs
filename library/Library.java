
// package library;
import liblib.Book;
import liblib.LibItem;
import liblib.Manager;
import liblib.exceptions.DuplicateIdException;
import liblib.exceptions.ItemNotFoundException;
import liblib.exceptions.ShelfNotFoundException;

public class Library {
    public static void main(String[] args) {
        Manager m = new Manager();
        m.addShelf();
        m.addShelf();
        LibItem mybook = new Book(1, "The first role", "Mohammed Amr");
        try {
            m.addItem(0, mybook);
            m.addItem(0, new Book(2, "This is not real", "Mohammed Amr"));
            m.addItem(0, new Book(3, "Kill Bill", "Mohammed Amr"));
            m.addItem(0, new Book(4, "The Godfather", "Mohammed Amr"));
            // m.removeItem(mybook);
            m.removeItem(mybook);
            m.addItem(1, new Book(5, "No country for old men", "Mohammed Amr"));
            m.addItem(1, new Book(6, "Please subscribe", "Mohammed Amr"));
            m.displayAll();
        } catch (ShelfNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DuplicateIdException e) {
            System.out.println(e.getMessage());
        }catch(ItemNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}
