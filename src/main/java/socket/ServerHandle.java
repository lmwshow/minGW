package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Auther: minGW
 * @Date: 2018/8/1 21:23
 * @Description:
 */
public class ServerHandle implements Runnable{

    public Socket socket;

    public ServerHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            String express;
            while (true)
            {
                //这里就说明了，四次挥手，服务端是通过read感知关闭请求的
                if ((express = in.readLine()) == null) break;

                System.out.println("服务器收到信息："+ express);

                out.println(express);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            //一些必要的清理工作
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if(out != null){
                out.close();
                out = null;
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }


        }

    }
}
