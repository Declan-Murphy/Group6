package org.dt340a.group6.sprint1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;

@NamedQueries({
})

@Entity
@IdClass(CountryOperatorPrimaryKey.class)
public class CountryOperator {

	@Id
	// @GeneratedValue(strategy=GenerationType.AUTO)
	private double mCC;
	@Id
	private double mNC;
	private String country;
	private String operator;

	public CountryOperator() {
		super();
	}

	private CountryOperator(Builder builder) {
		this.mCC = builder.mCC;
		this.mNC = builder.mNC;
		this.country = builder.country;
		this.operator = builder.operator;
	}

	public static class Builder {
		private double mCC;
		private double mNC;
		private String country;
		private String operator;

		public Builder() {
			super();
		}

		public Builder mCC(double mCC) {
			this.mCC = mCC;
			return this;
		}

		public Builder mNC(double mNC) {
			this.mNC = mNC;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder operator(String operator) {
			this.operator = operator;
			return this;
		}

		public CountryOperator build() {
			return new CountryOperator(this);
		}

	}
	
	public boolean isMCCEqual(int mCC) {
		return this.mCC == mCC;
	}
	
	public boolean isMNCEqual(int mNC) {
		return this.mNC == mNC;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
