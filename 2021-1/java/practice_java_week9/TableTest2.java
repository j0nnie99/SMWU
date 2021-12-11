import java.awt.*;
import javax.swing.*;

public class TableTest2 extends JFrame {
	
	JTable table;
	
	public TableTest2(String msg) {
		
		JFrame jf = new JFrame(msg);
		
		Object[][] data = {
				{"Yoon", new Integer(30), "Yoon@sm.ac.kr"},
				{"Lim", new Integer(40), "Lim@sm.ac.kr"},
				{"Park", new Integer(50), "Park@sm.ac.kr"},
				{"Lee", new Integer(60), "Lee@sm.ac.kr"}
				
		};
		
		String columnName[] = { "Name", "Age", "E-Mail" };
		
		table = new JTable(data, columnName);
		jf.getContentPane().add(new JScrollPane(table), "Center");
		
		jf.setSize(300,200);
		jf.setVisible(true);
	}
	
}