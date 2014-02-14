package entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@NamedQueries( {
//	@NamedQuery(name = "Subscriber.findAll", query = "select o from Subscriber o"),
//	@NamedQuery(name = "Subscriber.findByUsername", query = "select o from Subscriber o where o.username=:username"),
})

@Entity
public class UserEquipment {
	
	@Id
	private int ueTypeTacID;
	
	
	private String marketingName;
	private String manufacturerName;
	private String phoneModel;
	private String vendorName;
	private String ueType;
	private String phoneOS;
	private String inputMode;
	private String accessCapability;
	
	
	public UserEquipment(){
		
	}

	public UserEquipment(int ueTypeTacID, String marketingName, String manufacturerName, String phoneModel, String vendorName, String ueType, String phoneOS, String inputMode, String accessCapability) {
		super();
		this.marketingName = marketingName;
		this.manufacturerName = manufacturerName;
		this.phoneModel = phoneModel;
		this.vendorName = vendorName;
		this.ueType = ueType;
		this.phoneOS = phoneOS;
		this.inputMode = inputMode;
		this.accessCapability = accessCapability;
	}
	
	public int getUeTypeTacID() {
		return ueTypeTacID;
	}

	public void setUeTypeTacID(int ueTypeTacID) {
		this.ueTypeTacID = ueTypeTacID;
	}

	public String getMarketingName() {
		return marketingName;
	}

	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getUeType() {
		return ueType;
	}

	public void setUeType(String ueType) {
		this.ueType = ueType;
	}

	public String getPhoneOS() {
		return phoneOS;
	}

	public void setPhoneOS(String phoneOS) {
		this.phoneOS = phoneOS;
	}

	public String getInputMode() {
		return inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	public String getAccessCapability() {
		return accessCapability;
	}

	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

}
