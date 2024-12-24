package liblib;

public class Book extends LibItem {
    private String author;
    private String category;
    
    public Book(int id, String title, String author, String category){
        super(id, title);
        this.author = author;
        this.category = category;
    }
    
    public Book(int id, String title, String author){
        super(id, title);
        this.author = author;
        this.category = "General";
    }

    public String getItemDetails() {
        return this.wrapDetails(
            String.format("ID: %d\nTitle: %s\nAuthor: %s", id, title, author)
        );        
    }
}
