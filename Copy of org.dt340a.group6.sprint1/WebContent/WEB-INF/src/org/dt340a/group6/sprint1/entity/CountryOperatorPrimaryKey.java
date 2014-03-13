package org.dt340a.group6.sprint1.entity;

import java.io.Serializable;

public class CountryOperatorPrimaryKey implements Serializable {

	private static final long serialVersionUID = -3868029487198207391L;
	private double mCC;
	private double mNC;

	public CountryOperatorPrimaryKey() {
		super();
	}

	public CountryOperatorPrimaryKey(double mCC, double mNC) {
		this.mCC = mCC;
		this.mNC = mNC;
	}

	public double getmCC() {
		return mCC;
	}

	public void setmCC(double mCC) {
		this.mCC = mCC;
	}

	public double getmNC() {
		return mNC;
	}

	public void setmNC(double mNC) {
		this.mNC = mNC;
	}

}
