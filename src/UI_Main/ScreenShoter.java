package UI_Main;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.text.SimpleDateFormat;
import java.util.*;


public class ScreenShoter
{
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmss a");

    public String robo() throws Exception
    {
        Calendar now = Calendar.getInstance();
        Robot robot = new Robot();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(screenShot, "JPG", new File("ScreenShots/"+formatter.format(now.getTime())+".jpg"));
        return (formatter.format(now.getTime()));
    }
    
   /* public static void main(String[] args) throws Exception
    {
    	ScreenShoter s2i = new ScreenShoter();
        try{
        	s2i.robo();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }*/
}