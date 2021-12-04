
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character {
	int x, y;
	BufferedImage image;
	BufferedImage image1, image2, image3;
	int stepTime = 0;
	int fresh = GamePanel.FRESH;

	 boolean jumpState = false;//초기화

	 int jumpHeight = 300;//jump height
	 final int LOWEST_Y = 300;//character y좌
	
	int jumpValue=0;

	public Character() {
		try {
			 image1 = ImageIO.read(new File("img/player.png"));
			 image2 = ImageIO.read(new File("img/player2.png"));
			 image1 = ImageIO.read(new File("img/player.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = 5;
		y = LOWEST_Y;
	}

	public void move() {
		step();
		if(jumpState) {
			if(y>=LOWEST_Y) {
				jumpValue=-10;
			}
			if(y<=LOWEST_Y-jumpHeight) {
				jumpValue=10;
			}
			y+=jumpValue;
			if(y>=LOWEST_Y) {
				jumpState=false;
			}
		}
	

	}

	void step() {
		 int tmp = stepTime / 150%2;//Dinosaur swing arm speed
		switch (tmp) {
		case 0:
			image = image1;
			break;
		case 1:
			image = image2;
			break;
		default:
			image = image1;
			break;
		}

		stepTime += fresh;
	}

	public void jump() {
		jumpState = true;
	}
	
	public Rectangle getfeetBounds() {
		return new Rectangle(x+40, y+90, 20, 27);
	}
	

}
