package com.im.net.server.handler;

import com.im.net.server.util.GetApplicationContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class MessageDecode extends ByteToMessageDecoder {

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int msgType = byteBuf.readInt();
        String msgClass = "RecieveMsg"+msgType;
        ApplicationContext context = GetApplicationContext.getInstance();
        if(context.containsBean(msgClass)) {
            //AbstractMessage message = (AbstractMessage) context.getBean(msgClass);
            //message.readBody(byteBuf);
            //list.add(message);
        }else{
            list.add(null);
        }
    }
}
