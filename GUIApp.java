import java.awt.*;
import java.awt.event.*;

public class GUIApp extends GUIFramework
{
    private GameOfLife gol;
    private double timeSinceUpdate;
    
    public GUIApp(){
         gol= new GameOfLife();
         startGame();
    }
    
    public void update(double elapsed){
        timeSinceUpdate += elapsed;
        if(gol.isRunning() && timeSinceUpdate >= 0.25){
            timeSinceUpdate = 0;
            gol.update();
        }
    }

    public void draw(Graphics2D g2){
        gol.draw(g2);
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            gol.toggleRun();
        }
    }

    public void mousePressed(MouseEvent e){
        if(!gol.isRunning())
            gol.set(e.getPoint());
    }
}
