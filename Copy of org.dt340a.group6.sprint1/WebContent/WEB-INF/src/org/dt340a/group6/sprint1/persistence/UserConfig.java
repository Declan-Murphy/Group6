package org.dt340a.group6.sprint1.persistence;

import java.util.List;

import org.dt340a.group6.sprint1.entity.User;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;


public class UserConfig {

	public UserConfig(){	
	}
	public void viewUser(){
		List<User> users = PersistenceUtil.findAllUsers();
		for(User u:users){
			System.out.println("User "+u.getUsername()+ " exists.");
		}
	}

	public void createUser(String name, String password, String type){
		User user = new User(name, password, type);
		PersistenceUtil.persist(user);
		System.out.println("User registered");
	}
	public void removeUser(String username){
		User user = PersistenceUtil.findUserByUsername(username);	
		PersistenceUtil.remove(user);
		System.out.println("user removed");
	}
}
