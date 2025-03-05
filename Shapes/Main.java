import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class Main {
    
    public static void main(String[] args) {
        // create an ArrayList that can hold objects of type Shape
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        // put 20 Shape into the ArrayList with a random color, size and location
        for(int i=0; i<20; ++i){
            int x = (int)(Math.random()*500);
            int y = (int)(Math.random()*500);
            Color c = new Color((int)(Math.random()*Integer.MAX_VALUE));
            int length = (int)(Math.random()*100)+1;
            int width = (int)(Math.random()*100)+1;
            int type = (int)(Math.random()*5)+1;
            Shape p;
            
            if(type == 1) {
                p = new Rectangle(c, new Point(x,y), length, width);
            } else if(type == 2) {
                p = new Square(c, new Point(x,y), length);
            } else if(type == 3) {
                p = new Ellipse(c, new Point(x,y), length, width);
            } else if(type == 4) {
                p = new Circle(c, new Point(x,y), length);
            } else {
                p = new Triangle(c, new Point(x,y), length, width);
            }
            
            
            
            shapes.add(p);
        }

        for(Shape s : shapes){
            System.out.println(s);          
        }

        System.out.println("\nNow in Sorted Order\n");
        Collections.sort(shapes);

        for(Shape s : shapes){
            System.out.println(s);        
        }       

    }

}
