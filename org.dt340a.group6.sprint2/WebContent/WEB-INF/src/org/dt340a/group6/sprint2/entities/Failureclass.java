package org.dt340a.group6.sprint2.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the failureclass database table.
 * 
 */
@Entity
@NamedQuery(name="Failureclass.findAll", query="SELECT f FROM Failureclass f")
public class Failureclass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int failureClass;

	private String description;

	//bi-directional many-to-one association to Callfailure
	@OneToMany(mappedBy="failureclass")
	private List<Callfailure> callfailures;

	public Failureclass() {
	}

	public int getFailureClass() {
		return this.failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Callfailure> getCallfailures() {
		return this.callfailures;
	}

	public void setCallfailures(List<Callfailure> callfailures) {
		this.callfailures = callfailures;
	}

	public Callfailure addCallfailure(Callfailure callfailure) {
		getCallfailures().add(callfailure);
		callfailure.setFailureclass(this);

		return callfailure;
	}

	public Callfailure removeCallfailure(Callfailure callfailure) {
		getCallfailures().remove(callfailure);
		callfailure.setFailureclass(null);

		return callfailure;
	}

}