import javax.swing.*;
import java.awt.*;

class TextureTest extends JFrame {
	
	public TextureTest() {
		setSize(300,330);
		setTitle("Java 2D Texture Test: 윤정인");
		JPanel panel = new TexturePanel();
		add(panel);
		setVisible(true);
	}	
}