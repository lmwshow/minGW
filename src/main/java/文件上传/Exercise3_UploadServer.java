package 文件上传;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: minGW
 * @Date: 2018/5/24 17:21
 * @Description:
 */
public class Exercise3_UploadServer {

    public static void main(String[] args) throws Exception {
        System.out.println("服务器已启动, 绑定12345端口.");

        ServerSocket serverSocket = new ServerSocket(12345);
        while (true) {
            final Socket socket = serverSocket.accept();
            new Thread(){
                public void run() {
                    try {
                        
                        System.out.println("连接成功");
                        // 3.读取文件名
                        InputStream is = socket.getInputStream();                               // 字节流可以读写各种类型的数据
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));      // 字符流只能读字符
                        PrintStream ps = new PrintStream(socket.getOutputStream());
                        String filename = br.readLine();
                        System.out.println(filename);


                        // 3.1.读取文件大小
                        long length = Long.parseLong(br.readLine());

                        // 4.判断文件是否存在, 将结果发回客户端
                        File dir = new File("upload");
                        dir.mkdir();
                        File file = new File(dir, filename);
                        if (file.exists() && file.length() == length) {     // 文件存在, 大小一致代表已上传过了
                            ps.println("存在");
                            socket.close();
                            return;
                        } else {
                            ps.println(file.length());                      // 不存在是0, 存在则是传了一半的大小
                        }

                        String ip = socket.getInetAddress().getHostAddress();
                        System.out.println(ip + (file.exists() ? "断点续传:" : "开始上传: ") + file.getName());
                        long start = System.currentTimeMillis();

                        // 7.定义FileOutputStream, 从网络读取数据, 存储到本地
                        FileOutputStream fos = new FileOutputStream(file, true);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = is.read(buffer)) != -1)       // 文件可能是任意类型, 所以使用字节流读取
                            fos.write(buffer, 0, len);
                        fos.close();
                        socket.close();

                        long end = System.currentTimeMillis();
                        System.out.println(ip + "上传完毕: " + file.getName() + ", 耗时: " + (end - start) + "毫秒.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
