package com.jychan.notbad.channel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by chenjinying on 2017/6/6.
 * mail: 415683089@qq.com
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(); // 创建 EventLoopGroup
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(group) //创建 ServerBootstrap
                    .channel(NioServerSocketChannel.class) //指定使用 NIO 的传输 Channel
                    .localAddress(new InetSocketAddress(port)) //设置 socket 地址使用所选的端口
                    .childHandler(new ChannelInitializer<SocketChannel>() { //添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            // 绑定的服务器;sync 等待服务器关闭
            ChannelFuture future = bootstrap.bind().sync();

            System.out.println(EchoServer.class.getName() + " started and listen on " + future.channel().localAddress());

            // 关闭 channel 和 块，直到它被关闭
            future.channel().closeFuture().sync();
        } finally {
            // 关机的 EventLoopGroup，释放所有资源。
            group.shutdownGracefully().sync();
        }
    }


}
