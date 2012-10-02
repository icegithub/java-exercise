import java.awt.*;
import java.awt.event.*;

    public class AWTCounter extends Frame implements ActionListener, WindowListener {
        /**
		 * 
		 */
		private Label lblCount;
        private TextField tfCount;
        private Button btnCount;
        private int count = 0;

        public AWTCounter() {
            setLayout(new FlowLayout());
            lblCount = new Label("Counter");
            add(lblCount);
            
            tfCount = new TextField("0", 9);
            tfCount.setEditable(false);
            add(tfCount);
            
            btnCount = new Button("Count");
            add(btnCount);
            
            btnCount.addActionListener(this);
            
            addWindowListener(this);
            
            setTitle("AWT Counter");
            setSize(250,100);
            setVisible(true);            
        }

        public static void main(String[] args) {
        	
        	AWTCounter app = new AWTCounter();
        }
        
		@Override
		public void actionPerformed(ActionEvent evt) {
			// TODO Auto-generated method stub
			count++;
			tfCount.setText(count+"");
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
		
		@Override
		public void windowOpened(WindowEvent e) { }
		@Override
		public void windowClosed(WindowEvent e) { }
		@Override
		public void windowIconified(WindowEvent e) { }
		@Override
		public void windowDeiconified(WindowEvent e) { }
		@Override
		public void windowActivated(WindowEvent e) { }
		@Override
		public void windowDeactivated(WindowEvent e) { }
		
    }