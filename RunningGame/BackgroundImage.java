import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundImage {
	BufferedImage image;
	BufferedImage image1, image2;
	Graphics2D g2;
	int x1, x2;
	static final int SPEED = 10;

	public BackgroundImage() {
		try {
			image1 = ImageIO.read(new File("img/back.png"));
			image2 = ImageIO.read(new File("img/back.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = new BufferedImage(1000, 600, BufferedImage.TYPE_INT_BGR);
		g2 = image.createGraphics();
		x1 = 0;
		x2 = 1000;
		g2.drawImage(image1, x1, 0, null);
		g2.drawImage(image2, x2, 0, null);
	}

	public void roll() {
		x1 -= SPEED;
		x2 -= SPEED;
		if(x1<=-1000) {
			x1=1000;
		}
		if(x2<=-1000) {
			x2=1000;
		}
		g2.drawImage(image1, x1, 0, null);
		g2.drawImage(image2, x2, 0, null);
	}

}
