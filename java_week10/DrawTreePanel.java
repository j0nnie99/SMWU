import java.awt.*;
import javax.swing.*;

public class DrawTreePanel extends JPanel {
	
	public DrawTreePanel() {
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		drawTree(g, 400, 600, -90, 8);
	}
	private void drawTree(Graphics g, int x1, int y1, double angle, int depth) {
		if (depth == 0) return;
		int x2 = x1 + (int) (Math.cos(Math.toRadians(angle)) * depth * 10.0 );
		int y2 = y1 + (int) (Math.sin(Math.toRadians(angle)) * depth * 10.0 );
		g.drawLine(x1, y1, x2, y2);
		drawTree(g, x2, y2, angle - 20, depth - 1);
		drawTree(g, x2, y2, angle + 20, depth - 1);
	}
	
}
