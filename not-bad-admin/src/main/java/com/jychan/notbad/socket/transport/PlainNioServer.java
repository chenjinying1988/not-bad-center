package com.jychan.notbad.socket.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by chenjinying on 2017/6/11.
 * mail: 415683089@qq.com
 */
public class PlainNioServer {

    public void serve(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket ss = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        ss.bind(address);//1.绑定服务器到指定端口
        Selector selector = Selector.open();//2.打开selector处理channel
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);//3.注册ServerSocket到ServerSocket, 并指定这是专门接受连接
        final ByteBuffer msg = ByteBuffer.wrap("Hi!!!\t\n".getBytes("UTF-8"));
        for (;;) {
            try {
                selector.select();//4.等待新的事件来处理。这将阻塞，直到一个事件传入
            } catch (IOException e) {
                e.printStackTrace();
                // handle exception
                break;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();//5.从收到的所有事件中，获取SelectionKey实例
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove(); //for what ?
                try {
                    if (key.isAcceptable()) { //6.检查该事件是一个新连接，准备好接受
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        //7.接受客户端，并用 selector 进行注册
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                        System.out.println("接受连接 from client:" + client);
                    }
                    if (key.isWritable()) {//8.检查socket 是否准备好写数据
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            //9.将数据写入到所连接的客户端。如果网络饱和，连接是可写的，那么这个循环将写入数据，直到该缓冲区是空的
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        client.close(); //10.关闭连接
                    }
                } catch (IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (IOException ignored) {}
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PlainNioServer server = new PlainNioServer();
        server.serve(7777);
    }
}
