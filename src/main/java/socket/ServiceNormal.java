package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther: minGW
 * @Date: 2018/8/1 21:14
 * @Description:
 */
public final class ServiceNormal {

    private static int DEFAULT_PORT = 12345;

    private static ServerSocket serverSocket = null;

    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int defaultPort) throws IOException {

        if (serverSocket != null)
            return;

        try {
            serverSocket = new ServerSocket(defaultPort);

            System.out.println("服务端已启动，端口号：" + defaultPort);

            while (true)
            {
                Socket socket = serverSocket.accept();

                new Thread(new ServerHandle(socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if (serverSocket != null)
            {
                System.out.println("服务器已关闭");
                serverSocket.close();
                serverSocket = null;
            }

        }

    }

}
