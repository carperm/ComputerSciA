import java.awt.*;
import java.awt.event.*;

public class GUIApp extends GUIFramework
{
    private double timeSinceUpdate;
    private static final double TIME_TO_UPDATE = 0.25;
    private ConnectFour cof;
    
    public GUIApp(){
        cof = new ConnectFour(this);
         startGame();
    }
    
    public void update(double elapsed){
        
        timeSinceUpdate += elapsed;
        if(cof.isRunning() && timeSinceUpdate >= TIME_TO_UPDATE){
            timeSinceUpdate = 0;
            System.out.println("inside");
            cof.update();
        }
    }

    public void draw(Graphics2D g2){
        cof.draw(g2);
    }

    public void mousePressed(MouseEvent e){
        if(!cof.isRunning())
            cof.set(e.getPoint());
    }


}
