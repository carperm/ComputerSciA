import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.LinkedList;
import java.util.ListIterator;

public abstract class GameFramework extends JPanel implements Runnable, KeyListener, MouseListener
{
	//http://groups.google.com/group/Google-Web-Toolkit/browse_thread/thread/2f5beac8572cfbe6
	//private static final long serialVersionUID = 1L;
	
     /* Keeps track of which keys are pressed and which aren't */
    private boolean[] keys;

    /* Keep a list of all classes created by the user which
     * need to be drawn and updated automatically */
    private LinkedList<GameObject> gameObjects;

    /* Keep a list of the order in which the game objects are to be drawn */
    private LinkedList<Double> layerDepths;

    /* Create a new thread for the Drawing, Updating, and Listeners to operate on */
    private Thread gameLoop = null;

    private Image background = null;
    
    /* Is the game loop thread active and currently updating and drawing */
    private boolean isGameActive = true;
      
      
      /******************************************
     * Default Constructor
     * @param width The width of the client area of the screen
     * @param height The height of the client area of the screen
     ******************************************/
    public GameFramework(int width, int height)
    {
        // Set the size of the window (working area)
        setPreferredSize(new Dimension(width,height));

        // Initialize lists and arrays
        gameObjects = new java.util.LinkedList<GameObject>();
        layerDepths = new java.util.LinkedList<Double>();
        keys = new boolean[256];

        // Add listeners for mouse and keyboard
        addMouseListener(this);
        addKeyListener(this);

        // Make sure the window shows up active
        setFocusable(true);
        requestFocus(true);

        // Start the game in another thread
        gameLoop = new Thread(this);
    }
    
    
    
    /* Abstract Methods Which MUST Be Implemented In Your Own Game */    
    /******************************************
     * Called constantly during the game. (about 60 - 80 times per second)
     * This method should be used to update the game objects and any
     * other variables that change every frame of the game.
     * @param elapsed The number of milliseconds since the last time update was called.
     ******************************************/
    public abstract void update(double elapsed);
    
    /******************************************
     * Called constantly during the game. (about 60 - 80 times per second)
     * This method should be used to monitor key strokes.
     ******************************************/
    public abstract void processKeys();
    
    
    
    /******************************************
     * Draw method for the game itself.
     * This may be overridden but doesn't have to be.
     * @param g2 The graphics object which will be drawn on.
     ******************************************/
    public void draw(Graphics2D g2) {}
    
    /******************************************
     * Set the background of the window.
     * @param filePath The location where the image is located based from the project directory.
     * @see #setBackground(Image)
     ******************************************/
    public void setBackground(String filePath)
    {
        background = new ImageIcon(getClass().getResource(filePath)).getImage();
    }
    
    /******************************************
     * Set the background of the window to the specified image.
     * @param image The Image to set as the background.
     * @see #setBackground(String)
     ******************************************/
    public void setBackground(Image image)
    {
        background = image;
    }

    /******************************************
     * Add a GameObject to the list of objects to be drawn and draw it above all currently added objects.
     * @param object The GameObject to add.
     * @see #addGameObject(GameObject, double)
     ******************************************/
    public void addGameObject(GameObject object)
    {
        addGameObject(object, 0);
    }

    /******************************************
     * Add a GameObject to the list of objects to be drawn with the specified layer depth.
     * Layer depths of higher values draw above all layer depths of lower values.
     * @param object The GameObject to add.
     * @param layerDepth The layer depth of the added object.
     * @see #addGameObject(GameObject)
     ******************************************/
    public void addGameObject(GameObject object, double layerDepth)
    {
        // Make sure only one class can access each GameObject at a time
        synchronized(gameObjects)
        {
            // Create objects to move through the linked lists one by one
            ListIterator<GameObject> objectsIterator = gameObjects.listIterator(0);
            ListIterator<Double> layerDepthIterator = layerDepths.listIterator(0);
            boolean added = false;

            // While there is still more objects in the list to move through
            while(objectsIterator.hasNext())
            {
                // If the specified depth has been reached, insert the object at the current index
                if(layerDepth < layerDepthIterator.next())
                {
                    objectsIterator.add(object);
                    layerDepthIterator.add(layerDepth);

                    // Note that the correct layer depth has been found
                    added = true;
                    break;
                }
                
                objectsIterator.next();
            }

            // If no layer depth has been found, the object is added to the bottom of the list
            if(!added)
            {
                objectsIterator.add(object);
                layerDepthIterator.add(layerDepth);
            }
        }
    }
    
    /******************************************
     * Remove the specified GameObject from the list to be drawn and updated.
     * @param object The GameObject to be removed.
     ******************************************/
    public void removeGameObject(GameObject object)
    {
        // Make sure only one class can access each GameObject at a time
        synchronized(gameObjects)
        {
            gameObjects.remove(object);
        }
    }
    
    /******************************************
     * Start the main game loop.
     * @see #stopGame()
     * @see #exitGame()
     ******************************************/
    public void startGame()
    {
        if(gameLoop.isAlive() == false)
        {
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
        //  Make sure only one class can access each GameObject at a time
        synchronized(gameObjects)
        {
            // Create a Graphics2D object to draw onto
            Graphics2D g2 = (Graphics2D)g;
            // Call paintComponent in JPanel
            super.paintComponent(g2);
            // If a background image was provided
            if(background != null)
            {
                // Draw the background at (0, 0) and fill the size of the client area
                g2.drawImage(background, 0, 0, getWidth(), getHeight(), null);
 			               
            }
            else
            {
                // Clear the background to a default background color
                setBackground(new Color(100, 149, 237)); // CornflowerBlue
            }



            // Draw the game itself
            draw(g2);

            // For every GameObject, draw the GameObject
            for(GameObject object : gameObjects)
            {
                object.draw(g2);
            }

            // Dispose of graphics object to avoid memory leaks
            g2.dispose();
        }
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

            // Make sure only one class can access each GameObject at a time
            synchronized(gameObjects)
            {
            	// Call update method located in all GameObjects
                for(int i = gameObjects.size()-1; i >= 0; i--)
                {
                    gameObjects.get(i).update(elapsed, this);
                }
            }
            
            
            
            // Monitor key strokes
        
                processKeys();
            
            

            // Update the game itself
            update(elapsed);

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
    
    
    /* Sign contract with KeyListener */
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }
    public void keyTyped(KeyEvent e)
    {
    }
    public boolean isKeyPressed(int k)
    {
        // Make sure that k does not exceed the number of elements in keys[]
        if(k < keys.length)
        {
            return keys[k];
        }
        else
        {
            return false;
        }
    }
    
    
    /* Sign contract with MouseListener */
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
