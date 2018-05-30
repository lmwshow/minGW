package Netty.WebSocket;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 *
 * 标题: Netty 全局配置类<br>
 * 描述: 存储整个工程的全局配置<br>
 *
 * @Auther: minGW
 * @Date: 2018/5/29 22:02
 * @Description:
 */
public class NettyConfig {

    /**
     * 存储每一个客户端接入进来时的 Channel
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}