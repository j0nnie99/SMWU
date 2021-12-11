import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

import javax.imageio.ImageIO;




public class GamePanel extends JPanel implements KeyListener, ActionListener {
	BufferedImage image;
	Graphics2D g2;
	Character golden;
	boolean finish = false;

	static final int FRESH = 15;

	BackgroundImage background;

	List<Obstacle> list = new ArrayList<Obstacle>();

	int addObstacleTimer = 0;
	
	Timer timer = new Timer(1000, this);
	int time = 60;
		
    Object[] options = {"Home"};


	public GamePanel() {
		image = new BufferedImage(1000, 735, BufferedImage.TYPE_INT_BGR);
		g2 = image.createGraphics();
		golden = new Character();
		background = new BackgroundImage();

		list.add(new Obstacle());
		FreshThread t = new FreshThread(this);
		t.start();
	}

	private void painImage() {
		golden.move();
		background.roll();

		g2.drawImage(background.image, 0, 0, this);
		
		g2.drawImage(golden.image, golden.x, golden.y, this);

		if (addObstacleTimer >= 1000) {
			Random r=new Random();
			 int a=r.nextInt(100);//random number ~100
			 if(a>40) {//장애물 발생 확률
				list.add(new Obstacle());
			}
			addObstacleTimer = 0;
		}

		for (int i = 0; i < list.size(); i++) {
			Obstacle o = list.get(i);
			o.move();
			g2.drawImage(o.image, o.x, o.y, this);
			
			if(o.getBounds().intersects(golden.getfeetBounds())) {
				gameOver();
			}
		}
		
		g2.setColor(Color.RED);
		 g2.setFont(new Font("Bold", Font.BOLD, 24));
		g2.drawString(String.format("%d", time), 950, 30);
		addObstacleTimer+=FRESH;
		
		timer.start();
		
		
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		if (timer == arg0.getSource())
		{
			time--;
			
			if(time <= 0 && finish==false)
			{
				timer.stop();
				finish = true;
				int input = JOptionPane.showOptionDialog(null, "무사히 강의실에 도착했다!\n눈송이는 이번 학기 A+!",
						"Mission Complete!", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

				if(input == JOptionPane.OK_OPTION)
				{
					new Home();
				}
			}
		}
	}

	public void gameOver() {
		timer.stop();
		finish=true;
		int input = JOptionPane.showOptionDialog(null, String.format("넘어지는 바람에 제시간에 강의실에 도착하지 못했다\n기록: %d초", 60-time),
				"Game Over", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		if(input == JOptionPane.OK_OPTION)
		{
			setVisible(false);
		    new Home();
		}
	}

	@Override
	public void paint(Graphics g) {
		painImage();
		g.drawImage(image, 0, 0, this);
	}

	public boolean isFinish() {
		return finish;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if (code == KeyEvent.VK_SPACE) {
			golden.jump();
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}