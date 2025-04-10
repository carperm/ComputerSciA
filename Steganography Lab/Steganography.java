import java.awt.Color;

public class Steganography
{
    
    
    public static void main(String[] args)
    {
        Picture beach2 = new Picture ("./labs/steganography/beach.jpg");
        beach2.explore();
        Picture copy2 = testSetLow(beach2, Color.PINK);
        copy2.explore();
        Picture copy3 = revealPicture(copy2);
        copy3.explore();
    
    }
    //120,239   171,206,230
    public static void clearLow( Pixel p )
    {
        
        //Copy your code from the Changing Colors (https://codehs.com/editor/1739998/) activity here.
    
        int red = p.getRed() - (p.getRed() % 4);
        int green = p.getGreen() - (p.getGreen() % 4);
        int blue = p.getBlue() - (p.getBlue() % 4);
        
        p.setColor(new Color(red,green,blue));
        
    }
    
    public static void setLow(Pixel p, Color c)
    {
     /* To be implemented */
        clearLow(p);
        int pRed = p.getRed();
        int pGreen = p.getGreen();
        int pBlue = p.getBlue();
        int cRed = c.getRed();
        int cGreen = c.getGreen();
        int cBlue = c.getBlue();
        
        
        pRed += cRed/64;
        pGreen += cGreen/64;
        pBlue += cBlue/64;
        
        p.setColor(new Color(pRed, pGreen, pBlue));
    }
    
    /**
    * Method to test setLow on all pixels in a Picture
    * @param pic picture to be changed
    * @param col color to be used in modification
    * @return modified picture
    */
    public static Picture testSetLow(Picture pic, Color col)
    {
        Picture p = new Picture(pic);
        Pixel[] pics = p.getPixels();
        for(int i = 0; i < pics.length; i++) {
            setLow(pics[i], col);
        }
        
        return p;
    }
    
    /**
    * Sets the highest two bits of each pixel’s colors
    * to the lowest two bits of each pixel’s colors
    * @param hidden picture with hidden image
    * @return revealed picture
    */
    public static Picture revealPicture(Picture hidden)
    {
        Picture copy = new Picture(hidden);
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = hidden.getPixels2D();
        for (int r = 0; r < pixels.length; r++)
        {
            for (int c = 0; c < pixels[0].length; c++)
            {
                Color col = source[r][c].getColor();
                int red = (col.getRed()%64) + ((col.getRed()%4)*64);
                int green = (col.getGreen()%64) + ((col.getGreen()%4)*64);
                int blue = (col.getBlue()%64) + ((col.getBlue()%4)*64);
                
                pixels[r][c].setColor(new Color(red,green,blue));
            }
        }
        return copy;
    }
}

