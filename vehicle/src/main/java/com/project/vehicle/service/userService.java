package com.project.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vehicle.dao.userDao;
import com.project.vehicle.model.User;

@Service
public class userService {
@Autowired
userDao userObj;
	public int insertUserData(User user) {
		
		
		return userObj.insertUser(user);
	}
	
	public int getLoginCount(String name,String pass) {
		
		return userObj.getUserLogin(name, pass);
	}
public User getUser(String name) {
	
return userObj.getusers(name);
}

public int updateLogin(String name) {
	
	return userObj.updateUserLogin(name);
}
public int updateLogout(String name) {
	
	return userObj.updateUserLogout(name);
}
public int getUserCount(String name) {
	
	return userObj.getUserCountByName(name);
}

public int updatePassword(int id,String password) {
	
	return userObj.updatePassword(id, password);
}

public int getUserid(String name) {
	
	
	return userObj.getUserid(name);
}

public int getSuperUser(String name,String password) {

	return userObj.getSuperUser(name, password);
}

public int getEmailCount(String email) {
	

return userObj.getEmailid(email);

}
}
