import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class ColorPanel extends JPanel implements ActionListener {
	
	JButton button;
	Color color = new Color(0, 0, 0);
	
	public ColorPanel() {
		setLayout(new BorderLayout());
		button = new JButton("Color Change: 윤정인");
		button.addActionListener(this);	//이벤트 처리
		add(button, BorderLayout.SOUTH);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillRect(10, 10, 210, 220);
	}
	public void actionPerformed(ActionEvent e) {
		color = new Color((int) (Math.random()*255.0),
				(int) (Math.random()*255.0), (int)(Math.random()*255.0));
		repaint();
	}
}