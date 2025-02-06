import java.util.Scanner;

public class TicTacToeTester
{
    //You don't need to alter any of the code in this class!
    //This is just to test that your TicTacToe class is working correctly
    public static void main(String[] args)
    {
        TicTacToe board = new TicTacToe();
        Scanner kbd = new Scanner(System.in);
        
        boolean gameWin = false;
        int turns = 0;
        char player = 'X';
        while(gameWin == false && turns < 9) {
            System.out.println(board);
            System.out.print("Please input your row: ");
            int row = kbd.nextInt();
            System.out.print("Please input your column: ");
            int column = kbd.nextInt();
            if(board.checkValid(row, column)) {
                board.put(row, column, player);
                gameWin = board.checkWin();
                if(gameWin) {
                    System.out.println(board);
                    System.out.println(player + " Wins");
                }
                if(player == 'X') {
                    player = 'O';
                } else {
                    player = 'X';
                }
                turns++;
            }
        }
        if(turns == 9) {
            System.out.println("Draw");
        }
        
    }
}
