import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.geom.Rectangle2D.Double;

public class BasicStrokePanel extends JPanel {
	
	Rectangle2D.Double rect;
	Ellipse2D.Double   circle;
	
	public BasicStrokePanel() {
		rect = new Rectangle2D.Double(20,20,220,220);
		circle = new Ellipse2D.Double(20,20,220,220);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.draw(rect);
		float[] dashPattern = { 30, 10, 10, 10 };
		g2.setStroke(new BasicStroke(8, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, dashPattern, 0));
		g2.draw(circle);
	}
}