import java.awt.Color;
import java.awt.Point;

public class Triangle extends Shape {
    private double length;
    private double width;
    
    public Triangle(Color c, Point p, double length, double width) {
        super(c, p);
        this.length = length;
        this.width = width;
    }
    
    public double getArea() {
        return length*width*0.5;
    }
}
