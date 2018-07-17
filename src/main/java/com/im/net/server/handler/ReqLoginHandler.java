package com.im.net.server.handler;

import com.im.net.server.dao.UserDao;
import com.im.net.server.pojo.User;
import com.im.net.server.protobuf.MessageOuterClass;
import com.im.net.server.util.ServerUtil;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//请求登陆
@Component(value = "RecieveMsg1000")
public class ReqLoginHandler extends AbstractHandler {

    @Autowired
    private UserDao userDao;

    public void handle(Channel channel, MessageOuterClass.Message message) {
        MessageOuterClass.ReqLogin reqlogin = message.getReqlogin();
        MessageOuterClass.ResLogin.Builder resLogin = MessageOuterClass.ResLogin.newBuilder();
        User user = userDao.findById(reqlogin.getUserid());
        if (reqlogin.getUserid()==0){
            resLogin.setStatus("fail");
        }
        if (user != null && user.getPassword().equals(reqlogin.getPassword())) {
            resLogin.setStatus("success");
            resLogin.setUsername(user.getUsername());
            resLogin.setSex(user.getSex());
            resLogin.setUserid(user.getUserid());
            ServerUtil.regiterUser(channel,user);
        }else {
            resLogin.setStatus("fail");
        }
        MessageOuterClass.Message respon = MessageOuterClass.Message.newBuilder()
                .setMsgtype(2000)
                .setReslogin(resLogin)
                .build();
        channel.writeAndFlush(respon);
    }
}
