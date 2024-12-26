package liblib;

abstract public class LibItem {
    protected int id;
    protected String title;
    protected int stock = 5;
    protected String category = "General";
    public LibItem(String title){
        this.title = title;
    }

    public void setID(int id){
        this.id = id;
    }

    protected String wrapDetails(String details){
        return String.format("~~~~~~\n%s\n~~~~~~", details);
    }
    abstract public String getItemDetails();
}
