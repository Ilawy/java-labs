package liblib;

public class Client {
    private String name;
    private int age;
    private String email;

    public Client(String name, int age, String email){
        this.name = name;
        this.age = age;
        this.email = email;
    }



    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getClientDetails(){
        return String.format("%s <%s> (%d)", name, email, age);
    }
}
