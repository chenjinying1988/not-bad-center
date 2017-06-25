package com.jychan.notbad.socket.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 使用 netty 实现阻塞IO例子
 *
 * Created by chenjinying on 2017/6/15.
 * mail: 415683089@qq.com
 */
public class NettyOioServer {

    public void server(int port) throws Exception {
        ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hello你好!\r\n", Charset.forName("UTF-8")));
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); //1.创建一个ServerBootstrap
            b.group(group) // 2.使用 OioEventLoopGroup 允许阻塞模式（OId-IO）
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() { //3.指定ChannelInitializer 将给每个接受的连接调用
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //4.添加的ChannelHandler拦截事件，并允许他们作出反应
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    // 5.写信息到客户端，并添加ChannelFutureListener，一旦消息写入就关闭
                                    ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture f = b.bind().sync(); // 6.绑定服务器来接受连接
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync(); // 7.释放所有资源
        }
    }

    public static void main(String[] args) throws Exception {
        NettyOioServer nettyOioServer = new NettyOioServer();
        nettyOioServer.server(6789);

    }
}
