package com.im.net.client.handler;

import com.im.net.client.util.ClientUtil;
import com.im.net.server.handler.AbstractHandler;
import com.im.net.server.pojo.User;
import com.im.net.server.protobuf.MessageOuterClass;
import io.netty.channel.Channel;
import javafx.application.Platform;
import org.springframework.stereotype.Component;

@Component(value = "RecieveMsg2000")
public class ResLoginHandler extends AbstractHandler {

    public void handle(Channel channel, MessageOuterClass.Message message) {
        final MessageOuterClass.ResLogin resLogin = message.getReslogin();
        if (resLogin.getStatus().equals("fail")){
            Platform.runLater(new Runnable() {
                public void run() {
                    ClientUtil.setTipsText("用户名或密码错误");
                    }
            });
        }else {
            Platform.runLater(new Runnable() {
                public void run() {
                   ClientUtil.closePrimaryStage();
                   User user = new User();
                   user.setUserid(resLogin.getUserid());
                   user.setUsername(resLogin.getUsername());
                   user.setSex(resLogin.getSex());
                   ClientUtil.openChatView(user);
                   ClientUtil.reqfriends(user.getUserid());
                }
            });
        }
    }

}
