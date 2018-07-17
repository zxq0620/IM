package com.im.net.server.handler;

import com.im.net.server.protobuf.MessageOuterClass;
import com.im.net.server.util.GetApplicationContext;
import com.im.net.server.util.ServerUtil;
import com.im.net.server.util.Session;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.context.ApplicationContext;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx){
        if (!ServerUtil.addChannelSession(ctx.channel(), new Session(ctx.channel()))) {
            ctx.channel().close();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        MessageOuterClass.Message message = (MessageOuterClass.Message) msg;
        System.out.println(message.getMsgtype());
        if (message.getMsgtype()!=0){
            ApplicationContext context = GetApplicationContext.getInstance();
            String handlertype = "RecieveMsg"+((MessageOuterClass.Message) msg).getMsgtype();
            AbstractHandler handler = (AbstractHandler) context.getBean(handlertype);
            handler.handle(ctx.channel(),message);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
