import javax.swing.JFrame;

public class MyProgram
{        
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUIApp());
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.toFront();
    }
}
