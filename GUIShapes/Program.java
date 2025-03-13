import javax.swing.JFrame;

public class Program
{        
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Game());
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
