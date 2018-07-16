package com.im.net.server.Message;

import io.netty.channel.Channel;

public abstract class AbstractMessage extends ByteBufBean{

    abstract public void handler(Channel channel);

}
