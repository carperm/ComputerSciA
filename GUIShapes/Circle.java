import java.awt.Color;
import java.awt.Point;

public class Circle extends Ellipse {
    private double width;
    
    public Circle(Color c, Point p, double width) {
        super(c,p,width,width);
    }
}
