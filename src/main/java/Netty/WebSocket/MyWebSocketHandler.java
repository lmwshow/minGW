package Netty.WebSocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * <br>
 * 标题: 处理客户端WebSocket请求的核心业务处理类<br>
 * 描述: 接收/处理/响应 客户端websocket请求的核心业务处理类<br>
 *
 * @Auther: minGW
 * @Date: 2018/5/29 22:03
 * @Description:
 */
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;
    private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";

    /**
     * 服务端处理客户端websocket请求的核心方法
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest) {
            // 处理客户端向服务端发起http握手请求的业务
            FullHttpRequest request = (FullHttpRequest) msg;
            this.handHttpRequest(ctx, request);
        } else if (msg instanceof WebSocketFrame) {
            // 处理websocket连接的业务
            WebSocketFrame frame = (WebSocketFrame) msg;
            this.handWebSocketFrame(ctx, frame);
        }
    }

    /**
     * 处理客户端与服务端之前的websocket业务
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof CloseWebSocketFrame){
            // 如果是关闭websocket的指令
            handshaker.close(ctx.channel(),(CloseWebSocketFrame)frame.retain());
        }
        if (frame instanceof PingWebSocketFrame){
            // 如果是ping消息
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        if (!(frame instanceof TextWebSocketFrame)){
            // 如果不是文本消息，则抛出异常
            System.out.println("目前暂不支持二进制消息");
            throw  new RuntimeException("【"+this.getClass().getName()+"】不支持二进制消息");
        }

        // 获取客户端向服务端发送的文本消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("服务端收到客户端的消息=====>>>" + request);

        // 将客户端发给服务端的消息返回给客户端
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "====>>>" + request);

        // 群发，服务端向每个连接上来的客户端群发消息
        NettyConfig.group.writeAndFlush(tws);
    }


    /**
     * 处理客户端向服务端发起http握手请求的业务
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (!request.getDecoderResult().isSuccess() || !("websocket").equals(request.headers().get("Upgrade"))) {
            // 不是websocket握手请求时
            this.sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
        handshaker = wsFactory.newHandshaker(request);

        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), request);
        }
    }

    /**
     * 服务端向客户端响应消息
     */
    private void sendHttpResponse(ChannelHandlerContext ctc, FullHttpMessage request, DefaultFullHttpResponse response) {
        if (response.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
        }

        // 服务端向客户端发送数据
        ChannelFuture future = ctc.channel().writeAndFlush(response);
        if (response.getStatus().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 工程出现异常时调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 客户端与服务端创建连接时调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.add(ctx.channel());
        System.out.println("客户端与服务端连接开启...");
    }

    /**
     * 客户端与服务端断开连接时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettyConfig.group.remove(ctx.channel());
        System.out.println("客户端与服务端连接关闭...");
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


}
