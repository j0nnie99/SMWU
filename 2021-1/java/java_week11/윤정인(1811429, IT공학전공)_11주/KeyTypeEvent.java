import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class KeyTypeEvent extends JPanel {
	
	BufferedImage img = null;
	int img_x = 1, img_y = 1;
	
	public KeyTypeEvent() {
		try {
			img = ImageIO.read(new File("car.gif"));
		} catch (IOException e) {
			System.out.println("no image");
			System.exit(1);
		}
		
		addKeyListener (new KeyListener() {
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				switch (keycode) {
				case KeyEvent.VK_UP: img_y -= 10; break;
				case KeyEvent.VK_DOWN: img_y += 10; break;
				case KeyEvent.VK_LEFT: img_x -= 10; break;
				case KeyEvent.VK_RIGHT: img_x += 10; break;
				}
				repaint();
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		});
		
		this.requestFocus();
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, img_x, img_y, null);
	}
}