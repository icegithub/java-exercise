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
                // 读自定义的data头部，找到\n\n结尾
                int i = 0;
                while((header[i] = (byte)bufferedInput.read()) != -1) {
                        if(header[i] == '\n' && header[i-1] == '\n')
                                break;
                        i++;
                }
                // 转化为小写字母
                String header_str = new String(header).toLowerCase();

                // 在start和end之间为length，也就是图片的大小
                int start = header_str.indexOf(content_str) + content_str.length();
                int end = header_str.indexOf("\n", start);
                header_str = header_str.substring(start, end);

                // 转化为int型
                int size = Integer.parseInt(header_str);
                if (size < 0) break;

                // 将length长度的图片放到imageBytes中
                // 通过bufferedInput.read方法
                byte imageBytes[] = new byte[size];
                int newSize, totalSize = 0;
                while ((newSize = bufferedInput.read(imageBytes,totalSize,size-totalSize)) != -1){
                	 totalSize += newSize;
                	 if (totalSize == size) break;
                	 Thread.sleep(10);
                }
         //       bufferedInput.read(imageBytes, 0, size);
                

                // 创建图像， 参数就是刚刚传入的imageBytes
                img = Toolkit.getDefaultToolkit().createImage(imageBytes); 

                // 通知图片观察者更新图像
                if (imageObserver !=null) imageObserver.setImage(img);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ImageProducer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProducer.class.getName()).log(Level.SEVERE, null, ex);
        } 
  }
}