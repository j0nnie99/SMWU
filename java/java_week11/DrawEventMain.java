import javax.swing.*;

public class DrawEventMain{
	JFrame jf;
	
	public DrawEventMain(String msg) {
		jf = new JFrame(msg);
		jf.add(new DrawEvent());
		jf.setSize(900,1000);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new DrawEventMain("Mouse Draw Event: 윤정인");
	}
}