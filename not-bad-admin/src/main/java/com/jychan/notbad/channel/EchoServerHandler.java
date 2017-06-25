package com.jychan.notbad.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by chenjinying on 2017/6/3.
 * mail: 415683089@qq.com
 */
@ChannelHandler.Sharable // 1.可以在 channel 里共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //这个方法会在每次client有输入的时候调用
        ByteBuf buffer = (ByteBuf) msg;
        System.out.println("服务器收到：" + buffer.toString(CharsetUtil.UTF_8));
        ctx.write(buffer); // 将接收到的消息返回发送者，注意这里还没冲刷数据
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("读取完成 channelReadComplete...");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // 冲刷所有待审消息到远程节点，关闭通道后，操作完成
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close(); // 关闭通道
    }
}
