package com.im.net.server.util;

import com.im.net.server.pojo.User;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class ServerUtil {

    public static AttributeKey<Session> session_key = AttributeKey.valueOf("session");

    public static boolean addChannelSession(Channel channel, Session session) {
        Attribute<Session> sessionAttr = channel.attr(session_key);
        return sessionAttr.compareAndSet(null, session);
    }

    public static void regiterUser(Channel channel, User user){
        Attribute<Session> sessionAttr = channel.attr(session_key);
        Session session = sessionAttr.get();
        session.setUser(user);
    }

}
