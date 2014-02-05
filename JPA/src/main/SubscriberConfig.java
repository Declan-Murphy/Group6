package main;

import importFromFile.ImportExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import persistence.PersistenceUtil;
import entity.Subscriber;


public class SubscriberConfig {
	
	public static void main(String[] args){
		//SubscriberConfig config = new SubscriberConfig();
		try {
			ImportExcel.excel();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public SubscriberConfig(){
		createSubscriber("Jane", "pword2");
	}


	public void viewSubscriber(){
		List<Subscriber> subscribers = PersistenceUtil.findAllSubscribers();
		for(Subscriber s:subscribers){
			System.out.println("Subscriber "+s.getUsername()+ " exists.");
		}
	}
	
	public void createSubscriber(String name, String password){
		Subscriber subscriber = new Subscriber(name, password);
		PersistenceUtil.persist(subscriber);
		System.out.println("Subscriber registered");
	}
			
}
