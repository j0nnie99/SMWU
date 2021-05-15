import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TextEventTest implements TextListener, ActionListener {
	TextArea edit;
	Button exit;
	Frame f;
	
	public TextEventTest(String msg) {
		Frame f = new Frame(msg);
		edit = new TextArea();
		edit.addTextListener(this);
		exit = new Button("Exit");
		exit.addActionListener(this);
		
		f.add("Center", edit);
		f.add("South", exit);
		f.setSize(300, 250);
		f.setVisible(true);
	}
	
	public void textValueChanged(TextEvent e) {
		System.out.println("Text Value 내용이 변경되었습니다.");
	}
	
	public void actionPerformed(ActionEvent e) {
		f.setVisible(false);
		f.dispose();
		System.exit(0);
	}
}