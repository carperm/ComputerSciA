import java.awt.*;
import java.util.Random;

public class GameOfLife{
    
    private boolean running;
    private int step;
    private static final int GRID_WIDTH = 60;
    private static final int GRID_HEIGHT = 40;
    private int cellWidth;
    private int cellHeight;
    private boolean[][] grid;

    private boolean random = false;
    
    public GameOfLife(){
        running = false;
        Random rand = new Random();
        cellWidth = GUIFramework.SCREEN_WIDTH/GRID_WIDTH;
        cellHeight = GUIFramework.SCREEN_HEIGHT/GRID_HEIGHT;
        grid = new boolean[GRID_HEIGHT][GRID_WIDTH];
        if(random) {
            for(int r = 0; r < grid.length; r++) {
                for(int c = 0; c<grid[r].length; c++) {
                    grid[r][c] = rand.nextBoolean();
                }
            }
        } 
    }
    
    public void update(){
        boolean[][] temp = new boolean[GRID_HEIGHT][GRID_WIDTH];
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[r].length; c++) {
                int check = findNeighbors(r,c);
                
                if(grid[r][c]) {
                    if(check > 3) {
                        temp[r][c] = false;
                    } else if(check > 1) {
                        temp[r][c] = true;
                    } else {
                        temp[r][c] = false;
                    }
                } else if(check == 3) {
                        temp[r][c] = true;
                    }
                    
                }
            }
            
        grid = temp;
        ++step;
        System.out.println(step);
    }
        
    
    private int findNeighbors(int r, int c) {
        int count = 0;
        if(r > 0) {
            if(c > 0 && grid[r-1][c-1]) {
                count++;
            }
            if(grid[r-1][c]) {
                count++;
            }
            if(c < GRID_WIDTH-1 && grid[r-1][c+1]) {
                count++;
            }
        }
        if(c < GRID_WIDTH-1 && grid[r][c+1]) {
            count++;
        }
        if(c > 0 && grid[r][c-1]) {
            count++;
        }
        if(r < GRID_HEIGHT-1) {
            if(c > 0 && grid[r+1][c-1]) {
                count++;
            }
            if(grid[r+1][c]) {
                count++;
            }
            if(c < GRID_WIDTH-1 && grid[r+1][c+1]) {
                count++;
            }
        }
        return count;
    }
    
    public void draw(Graphics2D g2){
        g2.setColor(Color.YELLOW);
        
        for(int r = 0; r < GRID_HEIGHT; r++) {
            for(int c = 0; c < GRID_WIDTH; c++) {
                g2.drawRect(c*cellWidth,r*cellHeight,cellWidth,cellHeight);
            }
        }
        g2.setColor(Color.WHITE);
        for(int r = 0; r < GRID_HEIGHT; r++) {
            for(int c = 0; c < GRID_WIDTH; c++) {
                if(grid[r][c]) {
                g2.fill(new Rectangle(c*cellWidth+1,r*cellHeight+1,cellWidth-1,cellHeight-1));
            
                }    
            }
        }
        
    }
    
    public void toggleRun(){
        running = !running;
    }
    
    public boolean isRunning(){
        return running;
    }
    
    public void set(Point p){
        int r = (int)(p.getY()/cellHeight);
        int c = (int)(p.getX()/cellWidth);
        grid[r][c] = !grid[r][c];
    }
    
}
