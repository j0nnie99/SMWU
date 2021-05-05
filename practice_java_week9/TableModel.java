import javax.swing.*;
import javax.swing.table.*;
	
class TableModel extends AbstractTableModel {
	Object[][] data = {
			{"Yoon", new Integer(22), "Dev.", new Boolean(false)},
			{"Park", new Integer(23), "Dev.", new Boolean(true)},
			{"Lim", new Integer(20), "Research", new Boolean(false)},			
			{"Lee", new Integer(21), "Finance", new Boolean(true)},			
	};
	
	String[] columnName = {"Name", "Age", "Dept.", "Good ?"};
	
	public int getColumnCount() {
		return columnName.length;
	}
	public int getRowCount() {
		return data.length;
	}
	public String getColumnName (int col)
	{
		return columnName[col];
	}
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	public void setValueAt(Object value, int row, int col) {
		if (data[0][col] instanceof Integer) {
			try {
				data[row][col] = new Integer((String)value);
			} catch (NumberFormatException e) {
				System.err.println("User attemped to enter non-integer");
			}
		} else {
			data[row][col] = value;
		}
	}
}
