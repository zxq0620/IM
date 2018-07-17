package com.im.net.server.handler;

import com.im.net.server.protobuf.MessageOuterClass;
import io.netty.channel.Channel;

public abstract class AbstractHandler{

    abstract public void handle(Channel channel, MessageOuterClass.Message message);

}
