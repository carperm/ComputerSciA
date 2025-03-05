import java.awt.Color;
import java.awt.Point;

public abstract class Shape implements Comparable<Shape>{
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
