import javax.swing.*;

class BasicStrokeTest extends JFrame {
	
	public BasicStrokeTest() {
		setSize(300, 330);
		setTitle("Java 2D BasicStroke Test: 윤정인");
		JPanel panel = new BasicStrokePanel();
		add(panel);
		setVisible(true);
	}
}
