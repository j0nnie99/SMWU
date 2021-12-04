import java.awt.event.*;
import javax.swing.*;

public class MenuTestWithEvent implements ActionListener {
	
	JMenuBar bar;
	JMenu file, style;
	JMenuItem fileNew;
	JCheckBoxMenuItem num;
	JRadioButtonMenuItem dos, unix;
	JFrame jf;
	
	public MenuTestWithEvent(String msg) {
		jf = new JFrame(msg);
		makeMenu();
		jf.setSize(400,400);
		jf.setVisible(true);
	}
	
	protected void makeMenu() {
		bar = new JMenuBar();
		file = new JMenu("File");
		file.setMnemonic('F');
		fileNew = new JMenuItem("New");
		fileNew.addActionListener(this);
		file.add(fileNew);
		file.addSeparator();
		
		num = new JCheckBoxMenuItem("Line Number");
		num.addActionListener(this);
		style = new JMenu("Style");
		file.add(num);
		file.add(style);
		
		dos = new JRadioButtonMenuItem("Window Style", true);
		dos.addActionListener(this);
		unix = new JRadioButtonMenuItem("Linux Style");
		unix.addActionListener(this);
		ButtonGroup bg = new ButtonGroup();
		bg.add(dos);
		bg.add(unix);
		style.add(dos);
		style.add(unix);
		
		bar.add(file);
		jf.setJMenuBar(bar);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == fileNew) {
			System.out.println("New");
		}
		else if(o == num) {
			if(num.isSelected())
				System.out.println("Line Number");
		}
		else if (o == dos) {
			System.out.println("Windows");
		}
		else if (o == unix) {
			System.out.println("Linux");
		}
	}
	
	
}