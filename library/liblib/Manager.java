package liblib;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.Optional;

import liblib.exceptions.DuplicateIdException;
import liblib.exceptions.ItemNotFoundException;
import liblib.exceptions.ShelfNotFoundException;

public class Manager {
    ArrayList<ArrayList<LibItem>> shelfs;
    ArrayList<Integer> usedIDS;

    public Manager(){
        this.shelfs = new ArrayList<>();
        this.usedIDS = new ArrayList<>();
    }

    public void addShelf(){
        this.shelfs.add(new ArrayList<>());
    }

    public void addItem(int shelf, LibItem item) throws ShelfNotFoundException, DuplicateIdException{
        if(shelf < 0 || shelf >= shelfs.size()){
            throw new ShelfNotFoundException(shelf);
        }
        if(isIDDuplicated(item.id)){
            throw new DuplicateIdException();
        }
        usedIDS.add(item.id);
        shelfs.get(shelf).add(item);
    }

    public void removeItem(LibItem target) throws ItemNotFoundException{
        for(ArrayList<LibItem> shelf : shelfs){
            boolean found = shelf.stream().anyMatch((item)->item.equals(target));
            if(found){
                shelf.remove(target);
                return;
            }
        }
        throw new ItemNotFoundException();
    }


    public LibItem getBookByID(int id) throws ItemNotFoundException{
        for(ArrayList<LibItem> shelf : shelfs){
            Optional<LibItem> maybeLibItem = shelf.stream().filter(i->i.id == id).findFirst();
            if(maybeLibItem.isPresent()){
                return maybeLibItem.get();
            }
        }
        throw new ItemNotFoundException();
    }

    // private Integer getTallestShelf(){
    //     return shelfs.stream().max((o1, o2) -> o1.size()).get().size();
    // }

    private boolean isIDDuplicated(int id){
        boolean result = usedIDS.stream()
        .parallel()
        .anyMatch(item -> item == id);
        return result;
    }

    public void displayAll(){
        shelfs.forEach(shelf->{
            shelf.forEach(item->{
                System.out.println(item.title);
            });
        });
    }
}
