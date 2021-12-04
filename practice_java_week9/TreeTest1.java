import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.tree.*;

public class TreeTest1 {
	
	public TreeTest1 (String msg){
		
		JFrame jf = new JFrame(msg);
		DefaultMutableTreeNode jcomponent = new DefaultMutableTreeNode("JComponent");
		DefaultMutableTreeNode jpanel = new DefaultMutableTreeNode("JPanel");
		DefaultMutableTreeNode jtextcomponent = new DefaultMutableTreeNode("JTextComponent");
		DefaultMutableTreeNode jtree = new DefaultMutableTreeNode("JTree");

		jcomponent.add(jpanel);
		jcomponent.add(jtextcomponent);
		jcomponent.add(jtree);
		
		DefaultMutableTreeNode jtextarea = new DefaultMutableTreeNode("JTextArea");
		DefaultMutableTreeNode jtextfield = new DefaultMutableTreeNode("JTextField");

		jtextcomponent.add(jtextarea);
		jtextcomponent.add(jtextfield);
		
		JTree tree = new JTree(jcomponent);
		JScrollPane sp = new JScrollPane(tree);
		jf.getContentPane().add(sp, BorderLayout.CENTER);
		jf.setSize(300,200);
		jf.setVisible(true);
	}
	
}