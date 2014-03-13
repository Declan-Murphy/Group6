package org.dt340a.group6.sprint1.entity;

import java.io.Serializable;

public class CausePrimaryKey implements Serializable {

	private static final long serialVersionUID = -5831298652373238759L;
	private double eventId;
	private double causeCode;

	public CausePrimaryKey() {
		super();
	}

	public CausePrimaryKey(double eventId, double causeCode) {
		this.eventId = eventId;
		this.causeCode = causeCode;
	}

	public double getEventID() {
		return eventId;
	}

	public void setEventID(double eventID) {
		this.eventId = eventID;
	}

	public double getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(double causeCode) {
		this.causeCode = causeCode;
	}

}
