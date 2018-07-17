package com.im.net.server.handler;

import com.im.net.server.dao.FriendDao;
import com.im.net.server.pojo.User;
import com.im.net.server.protobuf.MessageOuterClass;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "RecieveMsg1001")
public class ReqFriendsHandler extends AbstractHandler {

    private List<User> friends;

    @Autowired
    FriendDao friendDao;

    public void handle(Channel channel, MessageOuterClass.Message message) {
        MessageOuterClass.ReqFriends reqFriends = message.getReqfriends();
        MessageOuterClass.ResFriends.Builder resFriends = MessageOuterClass.ResFriends.newBuilder();
        MessageOuterClass.Message.Builder respon = MessageOuterClass.Message.newBuilder();
        if (reqFriends.getUserid()==0){
            return;
        }
        friends = friendDao.getFriends(reqFriends.getUserid());
        if (!friends.isEmpty()){
            for (int i = 0 ; i < friends.size() ; i++){
                resFriends.setUserid(friends.get(i).getUserid())
                        .setUsername(friends.get(i).getUsername())
                        .setSex(friends.get(i).getSex());
                        //.setImg(friends.get(i).getImg());
                respon.setMsgtype(2001)
                        .setResfriends(resFriends);
                channel.writeAndFlush(respon.build());
                System.out.println(friends.get(i).getUsername());
            }
        }
    }
}
