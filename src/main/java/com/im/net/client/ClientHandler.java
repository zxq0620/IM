package com.im.net.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.UnsupportedEncodingException;

public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copyInt(1001));
        ctx.writeAndFlush(Unpooled.copyInt(12345));
        //ctx.writeAndFlush(Unpooled.copyInt("12345".length()));
        //ctx.writeAndFlush(Unpooled.copiedBuffer("12345".getBytes()));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {

        in.readInt();
        byte[] content = new byte[in.readInt()];
        in.readBytes(content);
        try {
            System.out.println(new String(content,"UTF-8"));
        } catch (UnsupportedEncodingException e) {

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {                    //4
        cause.printStackTrace();
        ctx.close();
    }
}
