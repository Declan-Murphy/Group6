package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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

import org.hibernate.mapping.Collection;

@NamedQueries( {
//	@NamedQuery(name = "Subscriber.findAll", query = "select o from Subscriber o"),
//	@NamedQuery(name = "Subscriber.findByUsername", query = "select o from Subscriber o where o.username=:username"),
})

@Entity
public class FailureClass {
	
	//every entity requires an id, and we can make it auto generated
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="failureClassID")
	private int failureClassID;
	
	private String description;
	
	@OneToMany(mappedBy = "failureClass" )
	private List<CallFailure> callFailures ;
	
	public List<CallFailure> getCallFailure() {
		return callFailures;
	}

	public void setCallFailure(List<CallFailure> callFailure) {
		this.callFailures = callFailure;
	}
	public void addCallFailure(CallFailure callFailure) {
		this.callFailures.add(callFailure);
	}
	public FailureClass(){
		
	}
	
	public FailureClass(int failureClassID, String description) {
		super();
		this.description = description;
		callFailures = new ArrayList<CallFailure>();
	}
	
	
	public int getFailureClassID() {
		return failureClassID;
	}

	public void setFailureClassID(int failureClassID) {
		this.failureClassID = failureClassID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
