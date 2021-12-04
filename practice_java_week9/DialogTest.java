import java.awt.*;

class DialogTest extends Frame {
	Dialog d;
	public DialogTest (String title) {
		super(title);
		d = new Dialog(this, "Dialog", false);
		d.add("Center", new Label("This is a Dialog"));
		d.add("South", new Button(" OK "));
		d.pack();
		d.setVisible(true);
		setSize(300,200);
		setVisible(true);
	}
}