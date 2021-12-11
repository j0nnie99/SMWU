import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import java.awt.Container;

import javax.swing.JOptionPane;

public class FreshThread extends Thread {
	GamePanel p;
	
	public FreshThread(GamePanel p) {
		this.p=p;
	}

	@Override
	public void run() {
		 while(!p.isFinish()) {//Judge whether to restart the game
			p.repaint();
			try {
				Thread.sleep(p.FRESH);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Container c=p.getParent();
		while(!(c instanceof MainFrame)) {
			c=c.getParent();
		}
		MainFrame f=(MainFrame) c;
		
		//JOptionPane.showMessageDialog(f, "Game Over" );
		//f.restart();
	}

}

