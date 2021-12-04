import javax.swing.*;

public class KeyTypeEventMain {
	JFrame jf;
	
	public KeyTypeEventMain(String msg) {
		jf = new JFrame(msg);
		jf.setSize(800, 900);
		jf.add(new KeyTypeEvent());
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		KeyTypeEventMain s = new KeyTypeEventMain("Key Type Event Test: 윤정인");
	}
}