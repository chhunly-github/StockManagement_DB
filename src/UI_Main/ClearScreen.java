package UI_Main;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class ClearScreen {
	public void clsr() throws Exception{
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_F10);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_F10);
		robot.keyPress(KeyEvent.VK_R);
		robot.keyRelease(KeyEvent.VK_R);
	
	}
	public ClearScreen(){
		try {
			this.clsr();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
