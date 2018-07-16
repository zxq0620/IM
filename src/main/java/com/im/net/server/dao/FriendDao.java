package com.im.net.server.dao;

import com.im.net.server.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendDao {

    public List<User> getFriends(int userid);
}
