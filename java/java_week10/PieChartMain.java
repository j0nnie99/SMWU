import java.awt.*;
import javax.swing.*;

public class PieChartMain {
	public static void main(String[] args) {
	JFrame frame = new JFrame("학과 분포 그래프: 윤정인");
	frame.setLocation(400, 200);
	frame.setPreferredSize(new Dimension(600, 350));
	Container contentPane = frame.getContentPane();
	DrawingPiePanel drawingPanel = new DrawingPiePanel();
	contentPane.add(drawingPanel, BorderLayout.CENTER);
	JPanel controlPanel = new JPanel();
	JTextField text1 = new JTextField(3);
	JTextField text2 = new JTextField(3);
	JTextField text3 = new JTextField(3);
	JTextField text4 = new JTextField(3);
	JButton button = new JButton("그래프 그리기");
	
	controlPanel.add(new JLabel("IT공학전공"));
	controlPanel.add(text1);
	controlPanel.add(new JLabel("경영학부"));
	controlPanel.add(text2);
	controlPanel.add(new JLabel("수학과"));
	controlPanel.add(text3);
	controlPanel.add(new JLabel("미디어학부"));
	controlPanel.add(text4);
	controlPanel.add(button);
	//컨테이너에 컴포넌트 그룹 부착
	
	contentPane.add(controlPanel,BorderLayout.SOUTH);
	frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    //리스너 객체 생성
	
	DrawingPieActionListener listener
     = new DrawingPieActionListener(text1, text2, text3, text4, drawingPanel);
	//리스너 부착
	
	button.addActionListener(listener);
	frame.pack();
	frame.setVisible(true);
	}
}
