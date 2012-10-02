import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class ImageProducer extends Thread {
    private ImageObserver imageObserver;
    private Socket socket;
    private String host;
    private int port;
    private String content_str = "--length:";
    private String request = "CLIENT";

    public ImageProducer(String host, int port, JFrame jframe) {
        this.host = host;
        this.port = port;
        this.imageObserver = (ImageObserver) jframe;
    }

    @Override
    public void run() {  
        PrintWriter printWriter = null;
        try {
            
            socket = new Socket(host, port);
           
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            InputStream input = socket.getInputStream();
            BufferedInputStream bufferedInput = new BufferedInputStream(input);
            byte header[] = new byte[1024];
            output.writeBytes("CLIENT");
            Image img;
            while (true) {
                // 璇昏嚜瀹氫箟鐨刣ata澶撮儴锛屾壘鍒癨n\n缁撳熬
                int i = 0;
                while((header[i] = (byte)bufferedInput.read()) != -1) {
                        if(header[i] == '\n' && header[i-1] == '\n')
                                break;
                        i++;
                }
                // 杞寲涓哄皬鍐欏瓧姣�
                String header_str = new String(header).toLowerCase();

                // 鍦╯tart鍜宔nd涔嬮棿涓簂ength锛屼篃灏辨槸鍥剧墖鐨勫ぇ灏�
                int start = header_str.indexOf(content_str) + content_str.length();
                int end = header_str.indexOf("\n", start);
                header_str = header_str.substring(start, end);

                // 杞寲涓篿nt鍨�
                 int size = Integer.parseInt(header_str);
                if (size < 0) break;

                // 灏唋ength闀垮害鐨勫浘鐗囨斁鍒癷mageBytes涓�
                // 閫氳繃bufferedInput.read鏂规硶
                byte imageBytes[] = new byte[size];
                int newSize, totalSize = 0;
                while ((newSize = bufferedInput.read(imageBytes,totalSize,size- totalSize)) != -1){
                	 totalSize += newSize;
                	 if (totalSize == size) break;
                	 Thread.sleep(10);
                }
         //       bufferedInput.read(imageBytes, 0, size);
                

                // 鍒涘缓鍥惧儚锛�鍙傛暟灏辨槸鍒氬垰浼犲叆鐨刬mageBytes
                img = Toolkit.getDefaultToolkit().createImage(imageBytes); 

                // 閫氱煡鍥剧墖瑙傚療鑰呮洿鏂板浘鍍�
                if (imageObserver !=null) imageObserver.setImage(img);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ImageProducer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProducer.class.getName()).log(Level.SEVERE, null, ex);
        } 
  }
}