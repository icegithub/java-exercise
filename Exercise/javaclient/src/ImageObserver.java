import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;

 
public class ImageObserver extends JFrame {
        public static final int WINDOW_WIDTH = 640;
        public static final int WINDOW_HEIGHT = 480;
        private JLabel lblURL;
        private JTextField tfURL;
        private JButton btnControl;
	private Image currentImage;

	private ImageProducer imgProducer;

   //     private class CapCanvas extends JPanel 
	public ImageObserver() {
		try {
	    		URL serverURL = new URL("http://192.168.32.139:8989");
                        imgProducer = new ImageProducer(serverURL.getHost(), serverURL.getPort(), this);
                       
		} catch (MalformedURLException ex) { }
                imgProducer.start();
                
                
                /** ******************************************* */
            /*    Container cp = this.getContentPane();
                lblURL = new JLabel("URL:");
                tfURL = new JTextField("");
                btnControl = new JButton("Connect");
                
                setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                cp.add(lblURL);
                cp.add(tfURL);
                cp.add(btnControl);*/
                
                
                
                
                
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit when close button clicked
                setTitle("Capture Client"); // "this" JFrame sets title
                setSize(WINDOW_WIDTH, WINDOW_HEIGHT);  // or pack() the components
                setVisible(true);   // show it
	}

	public void setImage(Image img)
	{
		prepareImage(img,this);
		currentImage = img;
		repaint();
	}

    @Override
	public void paint(Graphics g)
	{
		if (currentImage != null) {
			g.drawImage(currentImage,0,0,this);   
		}
	}
    
    public static void main(String[] args) {
      // Run GUI codes in the Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
            @Override
         public void run() {
            new ImageObserver();  // Let the constructor do the job
         }
      });
   }
}
