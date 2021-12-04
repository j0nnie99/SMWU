import javax.swing.*;
import java.awt.*;

class RotateTest extends JFrame {
	
	public RotateTest() {
		setSize(300,330);
		setTitle("Java 2D Rotate Test: 윤정인");
		JPanel panel = new RotatePanel();
		add(panel);
		setVisible(true);
	}	
}