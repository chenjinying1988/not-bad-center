package com.jychan.notbad.channel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 长连接服务
 *
 * Created by chenjinying on 2017/6/3.
 * mail: 415683089@qq.com
 */
@Service
public class KeepAliveServer {


    @Value("${netty.server.host}")
    private String host;
    @Value("${netty.server.port}")
    private int port;

    Channel channel;
    EchoServer server;

    public KeepAliveServer() {
        System.out.println(">>>>>>>>>>>>> port = " + port);
        if (port == 0) port = 9999;
        server = new EchoServer(port);
        try {
            server.start();
        } catch (Exception e) {
            System.out.println(">>>> 异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

}
