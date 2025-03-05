import java.awt.Color;
import java.awt.Point;

public class Square extends Rectangle {
    private double width;
    
    public Square(Color c, Point p, double width) {
        super(c,p,width,width);
    }
}
