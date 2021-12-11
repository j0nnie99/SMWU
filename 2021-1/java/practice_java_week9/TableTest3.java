import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;


public class TableTest3 {
	
	JTable table;
	
	public TableTest3(String msg) {
		JFrame jf = new JFrame(msg);
		TableModel model = new TableModel();
		table = new JTable(model);
		setUpDeptColumn(table.getColumnModel().getColumn(2));
		jf.getContentPane().add(new JScrollPane(table), "Center");
		jf.setSize(300,200);
		jf.setVisible(true);
	}
	
	public void setUpDeptColumn(TableColumn deptColumn) {
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Dev.");		
		comboBox.addItem("Research");
		comboBox.addItem("Finance");
		deptColumn.setCellEditor(new DefaultCellEditor(comboBox));

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setToolTipText("Click for combo box");
		deptColumn.setCellRenderer(renderer);
		
		TableCellRenderer headerRenderer = deptColumn.getHeaderRenderer();
		if (headerRenderer instanceof DefaultTableCellRenderer) {
			((DefaultTableCellRenderer)headerRenderer).setToolTipText("Click to");
		}
	}
	
}