import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Game extends GameFramework 
{
	private double keyTimer = 0;
    public Game() 
    {
    	// this needs to be the first line!
        super(500,500);
        
		// put your initialization code here
        
        // this should be the last line!
        this.startGame();
    }

    public void update(double elapsed)
    {
        keyTimer += elapsed;
    }

    public void processKeys()
    {
			    if(isKeyPressed(32) && keyTimer > 0.25) {
			        
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
			        
			        addGameObject(p);
			        keyTimer = 0;
			    }					
    }

    @Override
    public void draw(Graphics2D g2)
    {
		
    }
}
