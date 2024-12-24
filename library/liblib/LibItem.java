package liblib;

abstract public class LibItem {
    protected int id;
    protected String title;

    public LibItem(int id, String title){
        this.id = id;
        this.title = title;
    }

    protected String wrapDetails(String details){
        return String.format("~~~~~~\n%s\n~~~~~~", details);
    }
    abstract public String getItemDetails();
}
