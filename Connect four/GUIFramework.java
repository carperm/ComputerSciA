import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class GUIFramework extends JPanel implements Runnable, KeyListener, MouseListener
{
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 400;
    
    /* Create a new thread for the Drawing, Updating, and Listeners to operate on */
    private  Thread gameLoop = null;
    
    /* Is the game loop thread active and currently updating and drawing */
    private boolean isGameActive = false;
    private Color backgroundColor;
    
      
      /******************************************
     * Default Constructor
     * @param width The width of the client area of the screen
     * @param height The height of the client area of the screen
     ******************************************/
    public GUIFramework()
    {
        // Set the size of the window (working area)
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));

        backgroundColor = new Color(100, 149, 237);

        // Add listeners for mouse and keyboard
        addMouseListener(this);
        addKeyListener(this);

        // Make sure the window shows up active
        setFocusable(true);
        requestFocus(true);

        // Start the game in another thread
        gameLoop = new Thread(this);
        //gameLoop.start();
    }

    /******************************************
     * Start the main game loop.
     * @see #stopGame()
     * @see #exitGame()
     ******************************************/
    public void startGame()
    {
        if(!gameLoop.isAlive())
        {
            isGameActive = true;
            gameLoop.start();
        }
    }
    
    /******************************************
     * Stop the game completely and exits out of the main game loop.
     * @see #startGame()
     * @see #exitGame()
     ******************************************/
    public void stopGame()
    {
        isGameActive = false;
    }
    
    /******************************************
     * Exit the application without an error when the game is over.
     * @see #stopGame()
     * @see #exitGame()
     ******************************************/
    public void exitGame()
    {
        // Stop existing thread with game loop
        stopGame();
        
        System.exit(0);
    }
    
    
    
    /******************************************
     * Draws the background and all objects in the game.
     * @param g The Graphics object to draw on.
     ******************************************/
    @Override
    public void paintComponent(Graphics g)
    {

        // Create a Graphics2D object to draw onto
        Graphics2D g2 = (Graphics2D)g;
        // Call paintComponent in JPanel
        super.paintComponent(g2);

        setBackground(backgroundColor);

        // Draw the game itself
        draw(g2);

        // Dispose of graphics object to avoid memory leaks
        g2.dispose();

    }
    
    
    /******************************************
     * Allows GameFramework to implement Runnable
     * and be run in another thread. This is
     * also referred to as the game loop.
     ******************************************/
    public void run()
    {
        long startTime = System.currentTimeMillis(); // starting time stamp
        double elapsed; // time elapsed since last iteration through the game loop

        // While the game is running
        while(isGameActive)
        {
            // Get the time in seconds since the last time the game loop executed
            elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

            // Reset the start time
            startTime = System.currentTimeMillis();
            
            // Update the game itself
            update(elapsed);

            //moves to front
            
            // Tell the window to redraw itself
            repaint();

            // Try to stop the game for 10 milliseconds to save CPU cycles
            try
            {
            	Thread.sleep(10);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public abstract void draw(Graphics2D g2);

    public abstract void update(double elapsed);
    
    
    /* Sign contract with KeyListener */
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

    
    /* Sign contract with MouseListener */
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
