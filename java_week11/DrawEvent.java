import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DrawEvent extends JPanel {
	int startX, startY, w, h;
	JPanel jp;
	
	public void DrawEvent() {
	}
	
	void drawPanel() {
		
		addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				startX = e.getX();
				startY = e.getY();
			}
			public void mouseReleased(MouseEvent e) {
				w = Math.abs(startX - e.getX());
				h = Math.abs(startY - e.getY());
				repaint();
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				w = Math.abs(startX - e.getX());
				h = Math.abs(startY - e.getY());
				repaint();
			}
			public void mouseMoved(MouseEvent e) {}
		});
	}
	
	public void paintComponenet(Graphics g) {
		super.paintComponent(g);
		g.drawOval(startX, startY, w, h);
		drawPanel();
	}
}