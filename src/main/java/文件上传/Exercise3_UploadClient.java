package 文件上传;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Auther: minGW
 * @Date: 2018/5/24 17:13
 * @Description:
 */
public class Exercise3_UploadClient {
    
    public static void main(String[] args) throws IOException {
        
        // 1.提示输入要上传的文件路径, 验证路径是否存在以及是否是文件夹 
        File file = getFile();

        // 2.发送文件名到服务端
        Socket socket = new Socket("localhost",12345);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream ps = new PrintStream(socket.getOutputStream());

        ps.println(file.getName());
        // 2.1.发送文件大小到服务端
        ps.println(file.length());

        // 5.接收结果, 如果存在给予提示, 程序直接退出
        String result = br.readLine();
        if ("存在".equals(result)) {
            System.out.println("文件已存在, 请不要重复上传");
            socket.close();
            return;
        }

        long length = Long.parseLong(result);       // 获取服务端文件的大小

        // 6.如果不存在, 定义FileInputStream读取文件, 写出到网络
        FileInputStream fis = new FileInputStream(file);
        fis.skip(length);                           // 服务端完成了多少就跳过多少

        byte[] buffer = new byte[1024];
        int len;
        long finish = 0;
        while ((len = fis.read(buffer)) != -1) {
            ps.write(buffer, 0, len);
            finish += len;
            System.out.println("已完成: " + finish * 100 / file.length() + "%");
        }
        fis.close();
        ps.close();
        socket.close();

        System.out.println("上传完毕");

    }

    public static File getFile() {

        System.out.println("请输入要上传的文件路径:");
        Scanner in = new Scanner(System.in);

        while (true)
        {
            File file = new File(in.nextLine());
            if (!file.exists())
                System.out.println("您输入的路径不存在, 请重新输入:");
            else if (file.isDirectory())
                System.out.println("暂不支持文件夹上传, 请输入一个文件路径:");
            else
                return file;
        }
    }
}
