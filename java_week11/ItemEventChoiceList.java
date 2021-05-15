import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ItemEventChoiceList implements ActionListener, ItemListener {
	Frame f;
	Choice fruits;
	List color;
	Button exit;
	
	public ItemEventChoiceList(String msg) {
		f = new Frame(msg);
		fruits = new Choice();
		fruits.addItemListener(this);
		fruits.add("Apple");
		fruits.add("Orange");
		fruits.add("Banana");
		
		color = new List(3,true);
		color.addItemListener(this);
		color.addActionListener(this);
		color.add("blue");
		color.add("red");
		color.add("green");
		color.add("yellow");
		
		exit = new Button("Exit");
		exit.addActionListener(this);
		
		f.add("North", fruits);
		f.add("South", color);
		f.add("East", exit);
		
		f.setSize(300,200);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == color) {
			System.out.println("더블클릭이 발생한 아이템");
			System.out.println(e.getActionCommand());
			
		} else if (o == exit) {
			f.setVisible(false);
			f.dispose();
			System.exit(0);
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
		Object o = e.getSource();
		if (o == fruits) {
			System.out.println(fruits.getSelectedItem());
		} else if (o==color) {
			Object si[] = color.getSelectedItems();
			System.out.println("현재 선택된 아이템들");
			for (int i=0; i<si.length; i++) {
				System.out.print(si[i] + " ");
			}
			System.out.println("\n-------------------------");
			System.out.println("이벤트가 발생한 아이템들");
			int index = ((Integer)e.getItem()).intValue();
			System.out.println(color.getItem(index));
			System.out.println("\n-------------------------\n");
		}
			
	}
}