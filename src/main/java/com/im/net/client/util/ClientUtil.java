package com.im.net.client.util;

import com.im.net.client.view.ChatView;
import com.im.net.server.pojo.User;
import com.im.net.server.protobuf.MessageOuterClass;
import io.netty.channel.Channel;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class ClientUtil {

    public static Channel channel;
    public static Stage primarystage;
    public static Label tips;
    public static ListView list;

    public static void send(MessageOuterClass.Message message){
        channel.writeAndFlush(message);
    }

    public static void login(int userid, String password) {
        MessageOuterClass.ReqLogin.Builder reqLogin = MessageOuterClass.ReqLogin.newBuilder();
        reqLogin.setUserid(userid)
                .setPassword(password);
        MessageOuterClass.Message message = MessageOuterClass.Message.newBuilder()
                .setMsgtype(1000)
                .setReqlogin(reqLogin)
                .build();
        send(message);
    }

    public static void reqfriends(int userid){
        MessageOuterClass.ReqFriends.Builder builder = MessageOuterClass.ReqFriends.newBuilder();
        builder.setUserid(userid);
        MessageOuterClass.Message message = MessageOuterClass.Message.newBuilder()
                .setMsgtype(1001)
                .setReqfriends(builder)
                .build();
        send(message);
    }

    public static void closePrimaryStage(){
        primarystage.close();
    }

    public static void openChatView(User user){
        new ChatView(user).show();
    }

    public static void setTipsText(String text){
        tips.setText(text);
    }

    public static void addFriendList(String friendname){
        list.getItems().add(friendname);
    }
}
