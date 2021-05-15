import javax.swing.*;
	
public class MoreShapes extends JFrame {
	
	public MoreShapes() {
		setSize(600,130);
		setTitle("Java 2D Shapes: 윤정인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new MyShapePanel2();
		add(panel);
		setVisible(true);
	}
	
}