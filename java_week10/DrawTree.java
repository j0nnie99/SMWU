import javax.swing.*;

public class DrawTree extends JFrame {
	
	public DrawTree(String msg) {
		setTitle(msg);
		add(new DrawTreePanel());
		setSize(800, 600);
		setVisible(true);
	}
	
}