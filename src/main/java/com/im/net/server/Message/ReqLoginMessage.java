package com.im.net.server.Message;

import com.im.net.server.dao.UserDao;
import com.im.net.server.pojo.User;
import com.im.net.server.util.ServerUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//请求登陆
@Component(value = "RecieveMsg1000")
public class ReqLoginMessage extends AbstractMessage{

    private int userid;
    private String password;

    @Autowired
    private UserDao userDao;


    public void writeBody(ByteBuf buf) {

    }

    public void readBody(ByteBuf byteBuf) {
       this.userid = byteBuf.readInt();
       this.password = readUTF8(byteBuf);
    }

    public void handler(Channel channel) {
        User user = userDao.findById(this.userid);
        if (user != null && user.getPassword().equals(password)) {
            channel.writeAndFlush(Unpooled.copyInt(2000));
            channel.writeAndFlush(Unpooled.copyInt("success".length()));
            channel.writeAndFlush(Unpooled.copiedBuffer("success".getBytes()));
            ServerUtil.regiterUser(channel,user);
        }else {
            channel.writeAndFlush(Unpooled.copyInt(2000));
            channel.writeAndFlush(Unpooled.copyInt("failed".length()));
            channel.writeAndFlush(Unpooled.copiedBuffer("failed".getBytes()));
        }

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
