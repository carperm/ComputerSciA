import java.awt.Color;
import java.awt.Point;

public abstract class Shape implements GameObject, Comparable<Shape>{
    private Color color;
    private Point location;
    

    // a default shape is the color Color.BLACK and located at (0,0)
    public Shape() {
        color = Color.BLACK;
        location = new Point(0,0);
    }
    // provide a second constructor that takes a Color and a Point
    public Shape(Color c, Point p) {
        color = c;
        location = p;
    }
    // add accessors and mutators for the color and location
    public Color getColor() {
        return this.color;
    }
    
    public void changeColor(Color c) {
        color = c;
    }
    
    public void changeColor(int r, int g, int b) {
        color = new Color(r,g,b);
    }
    
    public void changeLocation(Point p) {
        location = p;
    }
    
    public Point getLocation() {
        return this.location;
    }
    // provide a toString method that returns a String with the color and location
    public String toString() {
        return (this.getClass().getName()) + " :" + "\t" + "location: " + location.getX() + "," + location.getY() + "\t" + "Area: " + getArea();
    }
    
    public void update(double elapsed, GameFramework gf) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        
        if(r > 0) {
            r--;
        }
        if(g > 0) {
            g--;
        }
        if(b > 0) {
            b--;
        }
        if(r == 0 && g == 0 && b == 0) {
            gf.removeGameObject(this);
        }
        
        changeColor(r, g, b);
    }
    
    public abstract double getArea();
    //implement the compareTo() method
    public int compareTo(Shape other){
        if(this.getArea() > other.getArea()) {
            return 1;
        } else if(this.getArea() == other.getArea()) {
            return 0;
        } else {
            return -1;
        }
    }
}
