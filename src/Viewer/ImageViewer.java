package Viewer;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class ImageViewer {
	private String src;
	public ImageViewer(String img){
		this.src=img;
	}
    public static void main(String avg[]) throws IOException
    {
       new ImageViewer();
    }
    public void view()throws IOException{
    	BufferedImage img=ImageIO.read(new File(src));
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        int width=(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height=(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100;
        frame.setSize(width,height);
        JLabel lbl=new JLabel();
       
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public ImageViewer() throws IOException
    {
        
    }
    
}