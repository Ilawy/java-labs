package liblib;

public class Book extends LibItem {
    public String author;
    private String category;
    
    public Book(int id, String title, String author, String category){
        super(title);
        this.author = author;
        this.category = category;
    }
    
    public Book(String title, String author){
        super(title);
        this.author = author;
        this.category = "General";
    }

    public String getItemDetails() {
        return this.wrapDetails(
            String.format("ID: %d\nTitle: %s\nAuthor: %s", id, title, author)
        );        
    }
}
