package Netty.WebSocket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * <br>
 * 标题: 程序入口<br>
 * 描述: 启动应用<br>
 * @Auther: minGW
 * @Date: 2018/5/29 22:12
 * @Description:
 * Netty 的服务器端的 acceptor 阶段, 没有使用到多线程, 因此上面的 主从多线程模型 在 Netty 的服务器端是不存在的.
 * 服务器端的 ServerSocketChannel 只绑定到了 bossGroup 中的一个线程,
 * 因此在调用 Java NIO 的 Selector.select 处理客户端的连接请求时, 实际上是在一个线程中的,
 * 所以对只有一个服务的应用来说, bossGroup 设置多个线程是没有什么作用的, 反而还会造成资源浪费.
 *
 *
 * the creator of Netty says multiple boss threads are useful if we share NioEventLoopGroup between different server bootstraps,
 * but I don't see the reason for it.
 */
public class AppStart {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new MyWebSocketChannelHandler());
            System.out.println("服务端开启等待客户端连接...");

            Channel channel = serverBootstrap.bind(8888).sync().channel();
            channel.closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 优雅的退出程序
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}