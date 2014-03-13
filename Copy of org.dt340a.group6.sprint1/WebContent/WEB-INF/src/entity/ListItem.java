package entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries( {
	@NamedQuery(name = "ListItem.findAll", query = "select o from ListItem o"),
	//@NamedQuery(name = "ListItem.findByUserId", query = "select o from ListItem o where o.user_id=:userId"),
})
@Entity
public class ListItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private User user;
	
	private String itemContent;
	
	public ListItem(){
		
	}
	
	public ListItem( User s, String i ){
		super();
		this.user = s;
		this.itemContent = i;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

}
