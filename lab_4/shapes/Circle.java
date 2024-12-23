package shapes;

public class Circle extends Shape {
    private double r;
    Circle(double r){
        this.r = r;
    }
    double area() {
        return Math.PI * Math.pow(r / 2, 2);
    }
    @Override
    void draw() {
        System.out.println("DRAW :: CIRCLE");
    }
}
