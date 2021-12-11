import javax.swing.*;
import java.awt.*;

class SnowManPanel extends JPanel {
	
	public SnowManPanel() {
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.PINK);
		g.fillOval(20, 30, 200, 200);
		g.setColor(Color.BLACK);
		g.drawArc(60, 80, 50, 50, 180, -180);
		g.drawArc(150, 80, 50, 50, 180, -180);
		g.drawArc(70, 130, 100, 70, 180, 180);
	}
}