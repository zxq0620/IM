package com.im.net.server.Message;

import com.im.net.server.dao.FriendDao;
import com.im.net.server.pojo.User;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "RecieveMsg1001")
public class ReqFriendsMessage extends AbstractMessage{

    private int user_id;

    private List<User> friends;

    @Autowired
    FriendDao friendDao;

    public void handler(Channel channel) {
        if (friends.isEmpty()){
            channel.writeAndFlush(Unpooled.copyInt(2001));
            channel.writeAndFlush(Unpooled.copyInt("null".length()));
        }
    }

    public void writeBody(ByteBuf buf) {

    }

    public void readBody(ByteBuf buf) {
        this.user_id = buf.readInt();
        this.friends = friendDao.getFriends(this.user_id);
    }
}
