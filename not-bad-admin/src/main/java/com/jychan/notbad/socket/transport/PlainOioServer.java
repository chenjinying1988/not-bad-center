package com.jychan.notbad.socket.transport;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by chenjinying on 2017/6/11.
 * mail: 415683089@qq.com
 */
public class PlainOioServer {

    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);//1.绑定服务器到指定的端口
        try {
            for (;;) {
                final Socket clientScoket = socket.accept();//2.接受一个连接
                System.out.println("接受连接，from: " + clientScoket);
                new Thread(new Runnable() {//3.创建一个新的线程来处理连接
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientScoket.getOutputStream();
                            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));//4.将消息发送到连接的客户端
                            out.flush();//5.一旦消息被写入和刷新时就关闭连接
                            out.write("Haha!\r\n".getBytes(Charset.forName("UTF-8")));//4.将消息发送到连接的客户端
                            clientScoket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            try {
                                clientScoket.close();
                            } catch (IOException e1) {
                                System.out.println("error!!," + e1.getMessage());
                            }
                        }
                    }
                }).start();//6.启动线程
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        PlainOioServer pOioServer = new PlainOioServer();
        pOioServer.serve(9999);
    }
}
