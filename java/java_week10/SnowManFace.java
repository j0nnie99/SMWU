import javax.swing.*;

public class SnowManFace {
	
	SnowManFace (String msg) {
		JFrame f = new JFrame(msg);
		f.add(new SnowManPanel());
		f.setSize(300, 300);
		f.setVisible(true);
	}
	
}
