import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
 
public class Home implements ActionListener{
	
	static JFrame frame = new JFrame("눈송RUN");
	ImageIcon title, bgImg, txt;
	JLabel img, manual, text;
 
	Home() {
		frame.setSize(1000,600);
		frame.setLocationRelativeTo(null);	//화면 중앙에 배치
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //X버튼 누를시 종료
        
        frame.getContentPane().setLayout(null);
        
		JButton start = new JButton("Start");
		start.setBounds(350, 370, 300, 60);
		
		JButton exit = new JButton("Exit");
		exit.setBounds(350, 440, 300, 60);
		
		manual = new JLabel("조작키: 스페이스바");
		manual.setForeground(Color.white);
		manual.setFont(new Font("굴림",Font.PLAIN, 20));
		manual.setBounds(425, 530, 300, 20);
		title = new ImageIcon("img/home.png");		
		img = new JLabel(title);
		img.setBounds(400, 20, title.getIconWidth(), title.getIconHeight());
		txt = new ImageIcon("img/announce.png");		
		text = new JLabel(txt);
		text.setBounds(0, 60, txt.getIconWidth(), txt.getIconHeight());
		

		Color c = new Color(53,82,125);
		
        frame.getContentPane().add(start);
        frame.getContentPane().add(exit);
        frame.getContentPane().add(manual);
        frame.getContentPane().add(img);
        frame.getContentPane().add(text);
        frame.getContentPane().setBackground(c);

        frame.setVisible(true); //프레임 보여주기

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	new MainFrame();
            	frame.setVisible(false);
            }
        });
        
        exit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                System.exit(0); //프로그램 종료
            }
        });


    }
    
    public static void main(String[] args) {
    	new Home();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
 
}
