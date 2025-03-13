import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics2D;

public class Rectangle extends Shape {
    private int length;
    private int width;
    
    public Rectangle(Color c, Point p, int length, int width) {
        super(c, p);
        this.length = length;
        this.width = width;
    }
    
    public double getArea() {
        return length*width;
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(getColor());
        g2.fillRect(getLocation().x,getLocation().y,width,length);
    }
}
