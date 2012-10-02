import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;

public class MyGUIProgram extends Frame {
	private MyGUIProgram myGUI;
	public MyGUIProgram() {
		Panel panel = new Panel();
		Button btn  = new Button();
		panel.add(btn);
		Label lblInput;
		lblInput = new Label("Enter ID");
		add(lblInput);
		lblInput.setText("Enter Passwd");
		lblInput.getText();
	}


	public static void main(String[] args) {
		MyGUIProgram m = new MyGUIProgram();
		}
}