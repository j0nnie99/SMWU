import javax.swing.*;

public class LineShapes extends JFrame {
	
	public LineShapes () {
		setSize(600,130);
		setTitle("Line Shapes: 윤정인");
		JPanel panel = new MyShapePanel();
		add(panel);
		setVisible(true);
	}
}
