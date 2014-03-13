package org.dt340a.group6.sprint1.query;

public class UserStory12Object {
	
	String imsi;
	int count;
	
	public UserStory12Object(String imsi, int count) {
		this.imsi = imsi;
		this.count = count;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
