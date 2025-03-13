import java.awt.Color;
import java.awt.Point;
import java.lang.Math;
import java.awt.Graphics2D;

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
    
    public void draw(Graphics2D g2) {
        g2.setColor(getColor());
        g2.fillOval(getLocation().x, getLocation().y, (int)major, (int)minor);
    }
}
