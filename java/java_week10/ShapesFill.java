import javax.swing.*;

public class ShapesFill extends JFrame {
	
	public ShapesFill() {
		setSize(600,130);
		setTitle("JAVA 2D Shapes Fill Test: 윤정인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new ShapesFillPanel();
		add(panel);
		setVisible(true);
	}
	
}