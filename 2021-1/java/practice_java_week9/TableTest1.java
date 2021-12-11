import java.awt.*;
import javax.swing.*;

public class TableTest1 {
	
	public TableTest1(String msg) {
		
		JFrame jf = new JFrame(msg);
		
		String columnName[] = {
				"이름", "전화번호", "E-Mail"
		};
		
		String data[][] = {
				{"윤정인", "02-1234-5678", "yoonjeongin@sookmyung.ac.kr"},
				{"윤정인2", "010-1234-5678", "yoonjeongin@sm.ac.kr"},
				{"윤정인3", "02-9876-5432", "yoonjeongin@naver.com"}
		};
		
		JTable table = new JTable(data, columnName);
		JScrollPane sp = new JScrollPane(table);
		jf.getContentPane().add(sp, BorderLayout.CENTER);
		
		jf.setSize(400,400);
		jf.setVisible(true);
	}
	
}