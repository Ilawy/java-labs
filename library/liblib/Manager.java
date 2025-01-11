package liblib;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.relation.RelationNotFoundException;

import liblib.exceptions.DuplicateIdException;
import liblib.exceptions.ItemAlreadyExists;
import liblib.exceptions.ItemNotAvailable;
import liblib.exceptions.ItemNotFoundException;
import liblib.exceptions.RelationAlreadyExists;

public class Manager {
    // {
    // category: [{}, {}]
    // }
    // ArrayList<Client> clients;
    // (ITEM, USER)
    ArrayList<Relation> relations;

    TreeMap<Integer, LibItem> items = new TreeMap<>();
    TreeMap<Integer, Client> clients = new TreeMap<>();

    private int itemIDS = 0;
    private int clientIDS = 0;
    int ids = 0;

    public Manager() {
        relations = new ArrayList<>();

    }

    // -------- client ------------
    //#region client
    public Optional<Client> getClientByID(int id) {
        if (clients.containsKey(id)) {
            return Optional.of(clients.get(id));
        }
        return Optional.empty();
    }

    public void addClient(Client c) throws ItemAlreadyExists {
        int newid = clientIDS++;
        c.setID(newid);
        clients.put(newid, c);
    }

    public List<Relation> getRelationsByClientID(int clientID){
        return relations.stream().filter(rel->rel.userID == clientID).collect(Collectors.toList());
    }

    //#endregion client


    public void removeClient(Client c) throws ItemAlreadyExists {
        if (!getClientByID(c.getID()).isPresent()) {
            throw new ItemAlreadyExists();
        }
        clients.remove(c.getID());
    }

    public int genID() {
        return this.ids++;
    }

    public void addItem(LibItem item) throws DuplicateIdException {
        int newid = itemIDS++;
        item.setID(newid);
        items.put(newid, item);
    }

    public void removeItemByID(int id) throws ItemNotFoundException {
        if (!isInsertedItem(id))
            throw new ItemNotFoundException();
        Optional<LibItem> itemOpt = getItemByID(id);
        if (itemOpt.isPresent())
            items.remove(id);
    }

    public Optional<LibItem> getItemByID(int id) throws ItemNotFoundException {
        items.forEach((key, value)->{
            System.out.format("xx %d: %s\n", key, value);
        });
        clients.forEach((key, value)->{
            System.out.format("xx %d: %s\n", key, value);
        });
        if (items.containsKey(id)) {
            return Optional.of(items.get(id));
        }
        return Optional.empty();
    }

    public void borrowItem(int clientID, int itemID)
            throws ItemNotFoundException, RelationAlreadyExists, ItemNotAvailable {
        if (isExistRelation(clientID, itemID).isPresent()) {
            throw new RelationAlreadyExists();
        }
        if (!isInsertedItem(itemID) || !isInsertedClient(clientID)) {
            throw new ItemNotFoundException();
        }
        Optional<LibItem> itemOpt = getItemByID(itemID);
        if (!itemOpt.isPresent()) {
            throw new ItemNotFoundException();
        }
        LibItem item = itemOpt.get();
        if (item.stock == 0) {
            throw new ItemNotAvailable();
        }
        relations.add(new Relation(clientID, itemID));
        item.stock--;
    }

    public void returnItem(int clientID, int itemID) throws RelationNotFoundException, ItemNotFoundException {
        if (!isExistRelation(clientID, itemID).isPresent()) {
            throw new RelationNotFoundException();
        }
        Optional<LibItem> itemOpt = getItemByID(itemID);
        if (!itemOpt.isPresent()) {
            throw new ItemNotFoundException();
        }
        LibItem item = itemOpt.get();
        relations.removeIf(rel -> rel.itemID == itemID && rel.userID == clientID);
        item.stock++;
    }

    private boolean isInsertedItem(int id) {
        return items.containsKey(id);
    }

    private boolean isInsertedClient(int id) {
        return clients.containsKey(id);
    }

    private Optional<Relation> isExistRelation(int userID, int itemID) {
        Optional<Relation> result = relations.stream()
                .filter(rel -> rel.userID == userID && rel.itemID == itemID)
                .findFirst();

        return result;
    }

    Stream<LibItem> streamItems() {
        return items.values().stream();
    }

    public void displayAll() {

    }
}
