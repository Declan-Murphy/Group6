package main;

import java.util.List;

import persistence.PersistenceUtil;
import entity.ListItem;
import entity.User;


public class UserConfig {

	public UserConfig(){	
	}
	public void viewUser(){
		List<User> users = PersistenceUtil.findAllUsers();
		for(User u:users){
			System.out.println("User "+u.getUsername()+ " exists.");
		}
	}
	public void viewListItems(){
		List<ListItem> items = PersistenceUtil.findAllItems();
		for(ListItem i:items){
			System.out.println("ListItem "+i.getItemContent()+ " exists.");
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
	public void createListItem(String username, String itemContent){
		ListItem item = new ListItem( PersistenceUtil.findUserByUsername(username) , itemContent);
		PersistenceUtil.persist(item);
		System.out.println("ListItem added");
	}
	public void removeListItem(String username, String itemContent){
		User user = PersistenceUtil.findUserByUsername(username);	
		ListItem item = PersistenceUtil.findListItem(user, itemContent);
		PersistenceUtil.remove(item);
		System.out.println("ListItem removed");
	}			

}
