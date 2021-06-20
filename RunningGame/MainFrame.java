import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{
	
	GamePanel p;
	
	public MainFrame() {
		

		super("Now Playing..");

		setSize(1000,600);
		setLocationRelativeTo(null);	//화면 중앙에 배치

		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);//Set the exit method
		p = new GamePanel();
		Container c = getContentPane();
		c.add(p);
		
		addKeyListener(p);
	}

	public void restart() {
		Container c=getContentPane();
		c.removeAll();
		
		GamePanel np=new GamePanel();
		c.add(np);
		addKeyListener(np);
		
		c.validate();
		
	}

}