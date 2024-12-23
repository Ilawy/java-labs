import shapes.ShapeTester;
import shapes.Rect;

public class Shapes {
    public static void main(String[] args){
        ShapeTester<Rect> tester = new ShapeTester<Rect>();
        tester.addShape(new Rect(8, 12)); 
        tester.addShape(new Rect(60, 80));
        System.out.println(tester.calculateAVGArea()); 
    }
}
