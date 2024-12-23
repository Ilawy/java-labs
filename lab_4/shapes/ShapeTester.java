package shapes;

import java.util.ArrayList;

public class ShapeTester<T extends Shape> {
    ArrayList<T> shapes;

    public ShapeTester(){
        this.shapes = new ArrayList<T>();
    }

    public void addShape(T s){
        shapes.add(s);
    }


    public double calculateAVGArea(){
        double total = 0;
        for(T s : shapes){
            total += s.area();
        }
        return total / shapes.size();
    }
}
