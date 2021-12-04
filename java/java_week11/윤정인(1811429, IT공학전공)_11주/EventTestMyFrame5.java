import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EventTestMyFrame5 extends JFrame {
	
	private JButton button;
	private JLabel label;
	
	public EventTestMyFrame5() {
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Event Programming Style 5: 윤정인");
		
		JPanel panel = new JPanel();
		button = new JButton("Button");
		label = new JLabel("Please Click the Button");
		button.addActionListener(e -> {
			label.setText("Clicked Button");
		});
		panel.add(button);
		panel.add(label);
		
		this.add(panel);
		this.setVisible(true);
	}
}