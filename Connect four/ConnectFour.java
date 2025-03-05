import java.awt.*;

public class ConnectFour {

    private boolean running;
    private static final int GRID_WIDTH = 7;
    private static final int GRID_HEIGHT = 6;
    private int cellWidth;
    private int cellHeight;
    private Color[][] grid;
    private Color player = Color.RED;
    private GUIApp gui;


    
    public ConnectFour(GUIApp g) {
        gui = g;
        running = false;
        cellWidth = GUIFramework.SCREEN_WIDTH/GRID_WIDTH;
        cellHeight = GUIFramework.SCREEN_HEIGHT/GRID_HEIGHT;
        grid = new Color[GRID_HEIGHT][GRID_WIDTH];
        for(int r = 0; r < GRID_HEIGHT; r++) {
            for(int c = 0; c < GRID_WIDTH; c++) {
                grid[r][c] = Color.WHITE;
            }
        }
        
    }

    public void update() {
        
    }

    public void draw(Graphics2D g2) {
        
        for(int r = 0; r < GRID_HEIGHT; r++) {
            for(int c = 0; c < GRID_WIDTH; c++) {
                g2.setColor(grid[r][c]);
                g2.fillOval(c*cellWidth+cellWidth/4,r*cellHeight+cellHeight/4,cellWidth/2,cellHeight/2);
            }
        }
        
    }

    public boolean checkWin(int row, int col) {
        boolean out = false;
        //check vert
        out = checkVert(row, col);
        if(out) {
            return true;
        }
        //check horizantal
        out = checkHorizantal(row, col);
        if(out) {
            return true;
        }
        //check diag
        out = checkDiag();
        if(out) {
            return true;
        }
        return false;
    }
    public boolean checkHorizantal(int row, int col) {
        int count = 0;
        for(int i = 0; i < GRID_WIDTH; i++) {
            if(grid[row][i] == player) {
                count++;
            } else {
                count = 0;
            }
            if(count >= 4) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkVert(int row, int col) {
        int count = 0;
        for(int i = 0; i < GRID_HEIGHT; i++) {
            if(grid[i][col] == player) {
                count++;
            } else {
                count = 0;
            }
            if(count >= 4) {
                return true;
            }
        }
        return false;
    }



    public boolean checkDiag() {
        
        
        for(int row = 3; row < grid.length; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row-1][col+1] == player &&
					grid[row-2][col+2] == player &&
					grid[row-3][col+3] == player){
					return true;
				}
			}
		}
		//check downward diagonal
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col+1] == player &&
					grid[row+2][col+2] == player &&
					grid[row+3][col+3] == player){
					return true;
				}
			}
		}
        
        return false;
        
    }
    
    public void set(Point p) {
        
            int c = (int)(p.x/cellWidth);
            int i = 0;
            while(i < GRID_HEIGHT && grid[i][c] == Color.WHITE) {
                i++;
            }
            if(i >= 1) {
                grid[i-1][c] = player;
            }
            if(checkWin(i-1, c)) {
                if(player == Color.RED) {
                    System.out.println("Red Wins!!");
                } else {
                    System.out.println("Blue Wins");
                }
                gui.exitGame();
                
            }
            if(player == Color.RED) {
                player = Color.BLUE;
            } else {
                player = Color.RED;
            }
            
    }
    
    public void toggleRun(){
        running = !running;
    }
    
    public boolean isRunning(){
        return running;
    }

}
