package liblib;
public class Pair<T, U> {
    public  T key;
    public U value;

    public Pair(T key, U value) {
        this.key = key;
        this.value = value;
    }


    

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}
