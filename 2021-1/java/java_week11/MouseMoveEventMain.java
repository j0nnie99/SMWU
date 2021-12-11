import javax.swing.*;

public class MouseMoveEventMain {
	JFrame jf;
	
	public MouseMoveEventMain(String msg) {
		jf = new JFrame(msg);
		jf.setSize(900, 1000);
		jf.add(new MouseMoveEvent());
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new MouseMoveEventMain("Mouse Move Event Test: 윤정인");
	}
}