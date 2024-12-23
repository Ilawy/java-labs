package shapes;

public class Rect extends Shape {
    double w, h;

    public Rect(double w, double h) {
        this.w = w;
        this.h = h;
    }

    double area() {
        return w * h;
    }

    @Override
    void draw() {
        System.out.println("DRAW :: RECT");
    }
}
