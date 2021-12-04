import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

public class TexturePanel extends JPanel {
	
	Rectangle2D.Double rect;
	BufferedImage		img;
	TexturePaint	texture;
	Rectangle		imgRect;
	
	public TexturePanel() {
		rect = new Rectangle2D.Double(10,10,800,800);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image im = toolkit.getImage("cat.png");
		MediaTracker t = new MediaTracker(this);
		t.addImage(im, 0);
		try {
			t.waitForAll();
		} catch(Exception e) {
			System.out.println("MediaTracker error: 윤정인");
		}
		img = new BufferedImage(im.getWidth(this),
				im.getHeight(this), BufferedImage.TYPE_INT_RGB);
		Graphics2D d2 = img.createGraphics();
		d2.drawImage(im, 0, 0, this);
		imgRect = new Rectangle(0,0,im.getWidth(this),im.getHeight(this));
		texture = new TexturePaint(img, imgRect);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(texture);
		
		Ellipse2D.Double circle = new Ellipse2D.Double(20,20,160,160);
		g2.fill(circle);
		Ellipse2D.Double circle2 = new Ellipse2D.Double(200,20,160,160);
		g2.fill(circle2);
		Rectangle2D.Double rect2 = new Rectangle2D.Double(200,200,600,600);
		g2.fill(rect2);	
	}
}