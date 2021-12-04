import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

public class ColorChooser extends JFrame implements ChangeListener {
	public JColorChooser color;
	
	public ColorChooser() { 
		setTitle("Color Chooser: 윤정인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		color = new JColorChooser();
		color.getSelectionModel().addChangeListener(this);
		color.setBorder(BorderFactory.createTitledBorder("Color Select: 윤정인"));
		JPanel panel = new JPanel();
		panel.add(color);
		add(panel);
		pack();
		this.setVisible(true);
	}
	
	public void stateChanged(ChangeEvent e) {
		Color newColor = color.getColor();
	}
}