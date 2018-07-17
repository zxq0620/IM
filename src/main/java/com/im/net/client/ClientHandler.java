package com.im.net.client;

import com.im.net.client.util.ClientUtil;
import com.im.net.server.handler.AbstractHandler;
import com.im.net.server.protobuf.MessageOuterClass;
import com.im.net.server.util.GetApplicationContext;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.context.ApplicationContext;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ClientUtil.channel = ctx.channel();
        MessageOuterClass.ReqFriends.Builder builder = MessageOuterClass.ReqFriends.newBuilder();
        builder.setUserid(12345);
        MessageOuterClass.Message message = MessageOuterClass.Message.newBuilder()
                .setMsgtype(1001)
                .setReqfriends(builder)
                .build();
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        MessageOuterClass.Message message = (MessageOuterClass.Message) msg;
        if (message.getMsgtype()!=0){
            ApplicationContext context = GetApplicationContext.getInstance();
            String handlertype = "RecieveMsg"+((MessageOuterClass.Message) msg).getMsgtype();
            AbstractHandler handler = (AbstractHandler) context.getBean(handlertype);
            //handler.handle(ctx.channel(),message);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {                    //4
        cause.printStackTrace();
        ctx.close();
    }
}
