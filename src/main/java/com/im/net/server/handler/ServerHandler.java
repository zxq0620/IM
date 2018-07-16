package com.im.net.server.handler;

import com.im.net.server.Message.AbstractMessage;
import com.im.net.server.util.ServerUtil;
import com.im.net.server.util.Session;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx){
        if (!ServerUtil.addChannelSession(ctx.channel(), new Session(ctx.channel()))) {
            ctx.channel().close();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        AbstractMessage message = (AbstractMessage) msg;
        if (message!=null){
            message.handler(ctx.channel());
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
