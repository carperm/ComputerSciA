import java.awt.Color;
import java.awt.Point;
import java.lang.Math;

public class Ellipse extends Shape {
    private double major;
    private double minor;
    
    public Ellipse(Color c, Point p, double major, double minor) {
        super(c,p);
        this.major = major;
        this.minor = minor;
    }
    
    public double getArea() {
        return Math.PI*(0.5*major)*(0.5*minor);
    }
}
