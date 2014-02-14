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


@Entity
public class EventCause {	
	//every entity requires an id, and we can make it auto generated
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int eventCauseID;
	
	
	private String eventID;
	private String causeCode;
	private String description;
	
	public EventCause(){
		
	}
	
	public EventCause(String eventID, String causeCode, String description){
		this.eventID = eventID;
		this.causeCode = causeCode;
		this.description = description;
	}
	
	
	public int getEventCauseID() {
		return eventCauseID;
	}

	public void setEventCauseID(int eventCauseID) {
		this.eventCauseID = eventCauseID;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
