package com.im.net.client.handler;

import com.im.net.client.util.ClientUtil;
import com.im.net.server.handler.AbstractHandler;
import com.im.net.server.protobuf.MessageOuterClass;
import io.netty.channel.Channel;
import javafx.application.Platform;
import org.springframework.stereotype.Component;

@Component(value = "RecieveMsg2001")
public class ResFriendHandler extends AbstractHandler {

    public void handle(Channel channel, MessageOuterClass.Message message) {
        final MessageOuterClass.ResFriends friend = message.getResfriends();
        Platform.runLater(new Runnable() {
            public void run() {
                System.out.println(friend.getUsername());
                ClientUtil.addFriendList(friend.getUsername());
            }
        });
    }

}
