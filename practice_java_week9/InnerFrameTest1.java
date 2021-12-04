import java.awt.*;
import javax.swing.*;

class InnerFrameTest1 {
	
	JDesktopPane desktop;
	JInternalFrame inframe;
	JButton ok;
	
	public InnerFrameTest1(String msg) {
		JFrame jf = new JFrame(msg);
		desktop = new JDesktopPane();
		inframe = new JInternalFrame("Inner Frame1", true, true, false, false);
		ok = new JButton("OK");
		inframe.getContentPane().add(ok, BorderLayout.SOUTH);
		inframe.setSize(200,100);
		inframe.setVisible(true);
		desktop.add(inframe);
		jf.getContentPane().add(desktop, BorderLayout.CENTER);
		jf.setSize(300,200);
		jf.setVisible(true);
	}
}