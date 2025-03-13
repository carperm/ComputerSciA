import java.awt.Graphics2D;

/******************************************
 * An interface specifying when methods must be in every GameComponent
 ******************************************/
public interface GameObject
{
	/******************************************
     * Draw method for the GameObject.
     * @param g2 The graphics object which will be drawn on.
     ******************************************/
    void draw(Graphics2D g2);
    
    /******************************************
     * Called constantly during the game. (about 60 times per second)
     * This method should be used to update the game object and any
     * other variables that change every frame of the game.
     * @param elapsed The number of milliseconds since the last time update was called.
     ******************************************/
    void update(double elapsed, GameFramework gf);
}
