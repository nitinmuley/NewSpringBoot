package com.nitin.webservices.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
//it talks to DB
@Component
public class UserDaoService {

	
	private static List<User> users= new ArrayList<>();
	private static List<User> posts= new ArrayList<>();
	private static int userCount=3;
	
	static {
		users.add(new User(1,"nitin", new Date()));
		users.add(new User(2,"Ram", new Date()));
		users.add(new User(3,"KD", new Date()));
	}
	
	public List<User> finAll(){
		return users;
	}
	
	public User save(User user) {
		
		if(user.getId()==null) {
			user.setId(userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for (User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
	 return null;
	}
	
	
	public User deleteUserById(int id) {
       Iterator<User> iterator = users.iterator();
       while (iterator.hasNext()) {
    	   User user=iterator.next();
    	   if(user.getId()==id) {
    		   iterator.remove();
    		   return user;
    	   }
       }
	 return null;
	}
	
}
