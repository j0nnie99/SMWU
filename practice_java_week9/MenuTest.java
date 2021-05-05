import javax.swing.*;

public class MenuTest {
	
	JMenuBar bar;
	JMenu file, style;
	JMenuItem fileNew;
	JCheckBoxMenuItem num;
	JRadioButtonMenuItem dos, unix;
	JFrame jf;
	
	public MenuTest(String msg) {
		jf = new JFrame(msg);
		makeMenu();
		jf.setSize(400,400);
		jf.setVisible(true);
	}
	
	void makeMenu() {
		bar = new JMenuBar();
		file = new JMenu("File");
		file.setMnemonic('F');
		fileNew = new JMenuItem("New");
		file.add(fileNew);
		file.addSeparator();
		num = new JCheckBoxMenuItem("Line Number");
		style = new JMenu("Style");
		file.add(num);
		file.add(style);
		dos = new JRadioButtonMenuItem("Window Style", true);
		unix = new JRadioButtonMenuItem("Linux Style");
		ButtonGroup bg = new ButtonGroup();
		bg.add(dos);
		bg.add(unix);
		style.add(dos);
		style.add(unix);
		bar.add(file);
		jf.setJMenuBar(bar);
	}
	
	
}