import java.awt.*;
import java.awt.event.*;

public class AWTAccumulator extends Frame implements ActionListener, WindowListener {
	private Label lblInput;
	private Label lblOutput;
	private TextField tfInput;
	private TextField tfOutput;
	private int numberIn;
	private int sum = 0;
	
	public AWTAccumulator() {
		setLayout(new FlowLayout());
		
		lblInput = new Label("Enter an Integer:");
		add(lblInput);
		
		tfInput = new TextField(10);
		add(tfInput);

		tfInput.addActionListener(this);
		addWindowListener(this);
		
		lblOutput = new Label("The Accumulated Sum is:");
		add(lblOutput);
		
		setTitle("AWT Accumlator");
		setSize(350, 120);
		setVisible(true);
	}

	 public static void main(String[] args) {
		 new AWTAccumulator();
     }
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		numberIn = Integer.parseInt(tfInput.getText());
		sum += numberIn;
		tfInput.setText("");
		tfOutput.setText(sum+"");
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}