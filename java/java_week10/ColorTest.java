import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
	
public class ColorTest extends JFrame {
	public ColorTest() {
		setSize(240,300);
		setTitle("Color Test: 윤정인");
		JPanel panel = new ColorPanel();
		add(panel);
		setVisible(true);
	}
}