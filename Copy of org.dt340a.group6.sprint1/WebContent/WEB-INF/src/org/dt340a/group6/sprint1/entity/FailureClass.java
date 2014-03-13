package org.dt340a.group6.sprint1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;

@NamedQueries({
})

@Entity
public class FailureClass {

	// every entity requires an id, and we can make it auto generated

	// @GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	// @Column(name="failureClassID")
	private int failureClass;

	private String description;

	// @OneToMany(mappedBy = "failureClass" )
	// private List<CallFailure> callFailures ;

	public FailureClass() {
		super();
	}

	public FailureClass(int failureClassID, String description) {
		super();
		this.failureClass = failureClassID;
		this.description = description;
		// callFailures = new ArrayList<CallFailure>();
	}

	private FailureClass(Builder builder) {
		this.failureClass = builder.failureClass;
		this.description = builder.description;
	}

	public static class Builder {
		private int failureClass;
		private String description;

		public Builder() {
			super();
		}

		public Builder failureClass(int failureClass) {
			this.failureClass = failureClass;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public FailureClass build() {
			return new FailureClass(this);
		}
		
	}
	
	public boolean isFailureClassEqual(int failureClass) {
		return this.failureClass == failureClass;
	}

	public int getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(int failureClass) {
		this.failureClass = failureClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
