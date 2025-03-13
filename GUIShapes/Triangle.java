import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;


public class Triangle extends Shape {
    private double height;
    private double width;

    
    public Triangle(Color c, Point p, double length, double width) {
        super(c, p);
        this.height = length;
        this.width = width;
    }

    
    public double getArea() {
        return height*width*0.5;
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(getColor());
        g2.fillPolygon(new int[] {getLocation().x, getLocation().x+(int)width, getLocation().x+(int)width/2}, new int[] {getLocation().y, getLocation().y, getLocation().y-(int)height}, 3);
    }
}
