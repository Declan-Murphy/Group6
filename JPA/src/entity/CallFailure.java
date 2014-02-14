package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@NamedQueries( {
//	@NamedQuery(name = "Subscriber.findAll", query = "select o from Subscriber o"),
//	@NamedQuery(name = "Subscriber.findByUsername", query = "select o from Subscriber o where o.username=:username"),
})

@Entity
public class CallFailure {

	//every entity requires an id, and we can make it auto generated
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int baseDataID;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="failureClassID", insertable=false, updatable=false)
	private FailureClass failureClass;
	
	public FailureClass getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(FailureClass failureClass) {
		this.failureClass = failureClass;
	}

	private Date dateTime;
	private int eventCauseID;
	//private int failureClassID;	
	private int ueTypeTacID;
	private int mcc;
	private int mnc;
	private int cellID;
	private int duration;
	private String neVersion;
	private String imsi;
	private String hier3ID;
	private String hier32ID;
	private String hier321ID;
	
	public CallFailure(){
		
	}
	
	public CallFailure(Date dateTime, int eventCauseID, FailureClass failureClass, int ueTypeTacID, int mcc,int mnc, int cellID, int duration, String neVersion, String imsi, String hier3ID, String hier32ID, String hier321ID) {
		super();
		this.dateTime = dateTime;
		this.eventCauseID = eventCauseID;
		//this.failureClassID = failureClassID;
		this.ueTypeTacID = ueTypeTacID;
		this.mcc = mcc;
		this.mnc = mcc;
		this.cellID = cellID;
		this.duration = duration;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3ID = hier3ID;
		this.hier32ID = hier32ID;
		this.hier321ID = hier321ID;
		
		this.failureClass = failureClass;
	}

	public int getBaseDataID() {
		return baseDataID;
	}

	public void setBaseDataID(int baseDataID) {
		this.baseDataID = baseDataID;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getEventCauseID() {
		return eventCauseID;
	}

	public void setEventCauseID(int eventCauseID) {
		this.eventCauseID = eventCauseID;
	}

//	public int getFailureClassID() {
//		return failureClassID;
//	}
//
//	public void setFailureClassID(int failureClassID) {
//		this.failureClassID = failureClassID;
//	}

	public int getUeTypeTacID() {
		return ueTypeTacID;
	}

	public void setUeTypeTacID(int ueTypeTacID) {
		this.ueTypeTacID = ueTypeTacID;
	}

	public int getMcc() {
		return mcc;
	}
	public int getMnc() {
		return mnc;
	}
	public void setMcc(int mcc) {
		this.mcc = mcc;
	}
	public void setMnc(int mnc) {
		this.mnc = mnc;
	}
	public int getCellID() {
		return cellID;
	}

	public void setCellID(int cellID) {
		this.cellID = cellID;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getHier3ID() {
		return hier3ID;
	}

	public void setHier3ID(String hier3id) {
		hier3ID = hier3id;
	}

	public String getHier32ID() {
		return hier32ID;
	}

	public void setHier32ID(String hier32id) {
		hier32ID = hier32id;
	}

	public String getHier321ID() {
		return hier321ID;
	}

	public void setHier321ID(String hier321id) {
		hier321ID = hier321id;
	}
}


