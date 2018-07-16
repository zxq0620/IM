package com.im.net.server.dao;

import com.im.net.server.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	
	public int getMaxId();

	public User findById(Integer id);
	
	public User findByName(String nickName);
	
	public void addUser(User user);
	
	public void delUser(Integer id);
	
	public void updateUser(User user);
}
