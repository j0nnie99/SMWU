import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyDialog extends JDialog {
	
	JTextField tf;
	JButton ok;
	
	public MyDialog (JFrame frame, String title) {
		super(frame, title);
		setLayout(new FlowLayout());
		tf = new JTextField(20);
		add(tf);
		
		ok = new JButton("OK");
		add(ok);
		
		setSize(200,200);
		
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
}