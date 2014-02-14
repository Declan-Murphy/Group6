package main;

import java.util.List;

import persistence.PersistenceUtil;
import entity.UserEquipment;

public class UserEquipmentConfig {
	
	public static void main(String[] args){
		UserEquipmentConfig config = new UserEquipmentConfig();
	}

	public UserEquipmentConfig(){
//		createUserEquipment(ueTypeTacID, marketingName, manufacturerName, phoneModel, vendorName, ueType, phoneOS, inputMode, accessCapability);
	}

	public void viewUserEquipment(){
		List<UserEquipment> userEquipment = PersistenceUtil.findAllUserEquipment();
		for(UserEquipment currentUserEquipment : userEquipment){
			System.out.println("UserEquipment "+currentUserEquipment.getUeTypeTacID()+ " exists.");
		}
	}
	
	public void createUserEquipment(int ueTypeTacID, String marketingName, String manufacturerName, String phoneModel, String vendorName, String ueType, String phoneOS, String inputMode, String accessCapability){
		viewUserEquipment();
		UserEquipment userEquipment = new UserEquipment(ueTypeTacID, marketingName, manufacturerName, phoneModel, vendorName, ueType, phoneOS, inputMode, accessCapability);
		PersistenceUtil.persist(userEquipment);
		System.out.println("UserEquipment record created");
		viewUserEquipment();
	}

}
