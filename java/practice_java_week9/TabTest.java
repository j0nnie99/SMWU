import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class TabTest
{
	JFrame jf;
	JTabbedPane tabpane;
	
	public TabTest(String msg)
	{
		jf = new JFrame(msg);
		tabpane = new JTabbedPane();
		
		JPanel one = new JPanel();
		JPanel two = new JPanel();
		JPanel three = new JPanel();
		JPanel four = new JPanel();
		
		one.setBackground(Color.red);
		two.setBackground(Color.green);
		three.setBackground(Color.blue);
		four.setBackground(Color.white);

		//Tab1의 Panel에 SMU 로고 붙이기
		ImageIcon logo = new ImageIcon("Images/" + "symcol02_s_01.gif");
		JLabel label = new JLabel("SMU Logo", logo, JLabel.RIGHT);
		label.setFont(new Font("굴림", Font.BOLD,30));
		one.add(label);
		
		//Tab2의 Panel에 사진 붙이기
		ImageIcon cat = new ImageIcon("Images/" + "cat.jpeg");
		JLabel label2 = new JLabel("Cat", cat, JLabel.RIGHT);
		two.add(label2);
		
		//Tab3의 Panel에 과일 선택 붙이기
		CheckBoxTest cbt = new CheckBoxTest();
		three.add(cbt);
		//Tab4의 Panel에 주문 사이즈 붙이기
		RadioButtonEvent rbe = new RadioButtonEvent();
		four.add(rbe);
			
		tabpane.addTab("SMU", one);
		tabpane.addTab("CAT", two);
		tabpane.addTab("FRUITS", three);
		tabpane.addTab("ORDER", four);

		jf.getContentPane().add(tabpane,BorderLayout.CENTER);
		jf.setSize(400,400);
		jf.setVisible(true);
	}
}
