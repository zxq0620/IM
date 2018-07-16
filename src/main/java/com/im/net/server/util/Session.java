package com.im.net.server.util;

import io.netty.channel.Channel;
import com.im.net.server.pojo.User;


public class Session {

    Channel channel;
    User user;
    int udpport;

    public Session(Channel channel) {
        this.channel = channel;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUdpport() {
        return udpport;
    }

    public Channel getChannel() {
        return channel;
    }

    public User getUser() {
        return user;
    }
}
