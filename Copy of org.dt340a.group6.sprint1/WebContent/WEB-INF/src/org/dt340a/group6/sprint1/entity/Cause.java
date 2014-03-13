package org.dt340a.group6.sprint1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries( {
	@NamedQuery(name = "Cause.findAll", query = "select o from Cause o"),
	@NamedQuery(name = "Cause.findByEvent_id", query = "select o from Cause o where o.eventId=:eventId"),
	@NamedQuery(name = "Cause.find", query = "select o from Cause o where o.eventId=:eventId and o.causeCode =:causeCode"),
})

@Entity
@IdClass(CausePrimaryKey.class)
public class Cause {
	
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private double eventId;
	@Id
	private double causeCode;
	private String description;
	
	public Cause(){
		super();
	}
	
	public Cause(double eventId, double causeCode, String description){
		this.eventId = eventId;
		this.causeCode = causeCode;
		this.description = description;
	}

	private Cause(Builder builder) {
		this.eventId = builder.eventId;
		this.causeCode = builder.causeCode;
		this.description = builder.description;
	}

	public static class Builder {
		private double eventId;
		private double causeCode;
		private String description;

		public Builder() {
			super();
		}

		public Builder eventId(double eventId) {
			this.eventId = eventId;
			return this;
		}

		public Builder causeCode(double causeCode) {
			this.causeCode = causeCode;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Cause build() {
			return new Cause(this);
		}
		
	}
	
	public boolean isEventIdEqual(double eventId) {
		return this.eventId == eventId;
	}
	
	public boolean isCauseCodeEqual(double causeCode) {
		return this.causeCode == causeCode;
	}

	public double getEventId() {
		return eventId;
	}

	public void setEventId(double eventID) {
		this.eventId = eventID;
	}

	public double getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(double causeCode) {
		this.causeCode = causeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
