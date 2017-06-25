package com.jychan.notbad.socket.transport;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * 使用 netty 实现非阻塞IO例子
 *
 * Created by chenjinying on 2017/6/18.
 * mail: 415683089@qq.com
 */
public class NettyNioServer {

    public void server(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi, netty nio...\r\n", Charset.forName("UTF-8"))
        );
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();//1.创建一个ServerBootstrap
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {//3.指定ChannelInitializer，将给每个接受的连接使用
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //4.添加的ChannelInboundHandlerAdapter() 接收事件并进行处理
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    //5.写信息到客户端，并添加 ChannelFutureListener 当一旦消息写入就关闭连接
                                    TimeUnit.SECONDS.sleep(5);
                                    ctx.writeAndFlush(buf.duplicate())
                                            .addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture f = b.bind().sync(); //6.绑定服务器来接受连接
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync(); //7.释放所有资源
        }
    }

    public static void main(String[] args) throws Exception {
        NettyNioServer server = new NettyNioServer();
        server.server(9001);
    }

}
