public class TicTacToe
{
    private char[][] board;
    
    public TicTacToe() {
        board = new char[3][3];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }
    
    public void put(int row, int col, char mark) {
            board[row][col] = mark;
    }
    
    public boolean checkValid(int row, int column) {
           if((row < board.length && column < board[0].length) && board[row][column] == '-') {
               return true;
           }
           System.out.println("Invalid move, try again");
           return false;
    }
    
    public boolean checkWin() {
        for(int i = 0; i < 3; i++) {
            if(checkRow(i) == true) {
                return true;
            }
        }
        for(int i = 0; i < 3; i++) {
            if(checkColumn(i) == true) {
                return true;
            }
        }
        if(checkDiag() == true) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkRow(int row) {
        if(!(board[row][0] == '-')) {
            return ((board[row][0] == board[row][1]) && (board[row][1] == board[row][2]));
        }
        return false;
    }
    
    public boolean checkColumn(int col) {
        if(!(board[0][col] == '-')) {
            return ((board[0][col] == board[1][col]) && (board[1][col] == board[2][col]));
        }
        return false;
    }
    
    public boolean checkDiag() {
        char check = board[0][0];
        boolean out = false;
        out = (check == board[1][1] && check == board[2][2]);
        if(out == true) {
            return out;
        }
        check = board[0][2];
        out = (check == board[1][1] && check == board[2][0]);
        return out;
    }
    
    public String toString() {
        String str = "";
        for(char[] out : board) {
            for(char that : out) {
                str += (that + " ");
            }
            str += "\n";
        }
        return str;
    }
}
