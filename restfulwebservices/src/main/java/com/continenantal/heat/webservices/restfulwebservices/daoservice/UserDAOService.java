package com.continenantal.heat.webservices.restfulwebservices.daoservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.continenantal.heat.webservices.restfulwebservices.beans.User;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<User>();
	private static int count=  3;
	static {
		users.add(new User(1,"Adam",new Date()));
		users.add(new User(2,"Eve",new Date()));
		users.add(new User(3,"Glory",new Date()));
	}
	
	public List<User> users(){
		return users;
	}
	
	public User getUser(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(++count);
		users.add(user);		
	}
	
	public User deleteUserById(int id)
	{
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		
		return null;
	
	}
}
