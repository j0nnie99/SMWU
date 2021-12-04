import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel {
	public MyPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public void paintComponent(Graphics g) {
		g.drawRect(50, 50, 50, 50);
		g.drawOval(200, 50, 50, 50);
	}
}