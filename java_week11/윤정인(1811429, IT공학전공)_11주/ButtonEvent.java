import java.awt.*;
import java.awt.event.*;

public class ButtonEvent implements ActionListener
{
	Frame f;
	Button test, exit;
	
	public ButtonEvent(String msg) {
		
		f = new Frame(msg);
		test = new Button("Test");
		test.addActionListener(this);
		
		exit = new Button("Exit");
		exit.addActionListener(this);
		
		f.add("North", test);
		f.add("South", exit);
		
		f.setSize(300, 200);
		f.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == test) {
			System.out.println("Button Event Test Message");
		} else if (e.getSource() == exit) {
			f.setVisible(false);
			f.dispose();
			System.exit(0);
		}
	}
	
}
