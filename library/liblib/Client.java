package liblib;

public class Client {
    private String name;
    private int age;
    private String email;
    private int id;

    public Client(String name, int age, String email){
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getClientDetails(){
        return String.format("%s <%s> (%d)", name, email, age);
    }
}
