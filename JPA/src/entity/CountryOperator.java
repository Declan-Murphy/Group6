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
public class CountryOperator {
	
	//every entity requires an id, and we can make it auto generated
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int mcc;
	@Id
	private int mnc;
	private String phoneOperator;
	private String country;
	
	public CountryOperator(){
		
	}
	
	public CountryOperator(int mcc, int mnc, String phoneOperator, String country) {
		super();
		this.mnc = mnc;
		this.phoneOperator = phoneOperator;
		this.country = country;
	}

	public int getMcc() {
		return mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	public int getMnc() {
		return mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}

	public String getPhoneOperator() {
		return phoneOperator;
	}

	public void setPhoneOperator(String phoneOperator) {
		this.phoneOperator = phoneOperator;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
