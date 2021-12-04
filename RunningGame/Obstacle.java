import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Obstacle {
	int x,y;
	BufferedImage image;	
	BufferedImage obs1,obs2;
	int speed;
	
	public Obstacle() {
		try {
			obs1=ImageIO.read(new File("img/obs1.png"));
			obs2=ImageIO.read(new File("img/obs2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Random r=new Random();
		 int temp=r.nextInt(5);//Create a random number and an obstacle randomly appears
		if(temp==0) {
			image=obs1;
		}
		if(temp==1) {
			image=obs1;
		}
		if(temp==2) {
			image=obs1;
		}
		else {
			image=obs2;
		}
		 speed=BackgroundImage.SPEED;//The speed of background scrolling
		x=1000;
		y=320;
		
		
	}
	
	public void move() {
		x-=speed;
	}
	
	public Rectangle getBounds() { 
		if(image==obs2) {
			return new Rectangle(x, y,image.getWidth(), image.getHeight());
		}else {
			return new Rectangle(x, y,image.getWidth()-17, image.getHeight());
		}

	}
}