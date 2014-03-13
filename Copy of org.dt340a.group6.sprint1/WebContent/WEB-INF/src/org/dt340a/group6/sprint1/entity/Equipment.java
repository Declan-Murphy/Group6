package org.dt340a.group6.sprint1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	 @NamedQuery(name = "Equipment.findByTac", query = "select o from Equipment o where o.tAC=:Tac"),
	 @NamedQuery(name = "Equipment.findByModel", query = "select o from Equipment o where o.model=:MODEL"),
})

@Entity
public class Equipment {

	@Id
	private int tAC;
	private String marketingName;
	private String manufacturer;
	private String accessCapability;
	private String model;
	private String vendorName;
	private String equipmentType;
	private String operatingSystem;
	private String inputMode;

	public Equipment() {
		super();
	}

	private Equipment(Builder builder) {
		this.tAC = builder.tAC;
		this.marketingName = builder.marketingName;
		this.manufacturer = builder.manufacturer;
		this.accessCapability = builder.accessCapability;
		this.model = builder.model;
		this.vendorName = builder.vendorName;
		this.equipmentType = builder.equipmentType;
		this.operatingSystem = builder.operatingSystem;
		this.inputMode = builder.inputMode;
	}

	public static class Builder {
		private int tAC;
		private String marketingName;
		private String manufacturer;
		private String accessCapability;
		private String model;
		private String vendorName;
		private String equipmentType;
		private String operatingSystem;
		private String inputMode;

		public Builder() {
			super();
		}

		public Builder tAC(int tAC) {
			this.tAC = tAC;
			return this;
		}

		public Builder marketingName(String marketingName) {
			this.marketingName = marketingName;
			return this;
		}

		public Builder manufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
			return this;
		}

		public Builder accessCapability(String accessCapability) {
			this.accessCapability = accessCapability;
			return this;
		}

		public Builder model(String model) {
			this.model = model;
			return this;
		}

		public Builder vendorName(String vendorName) {
			this.vendorName = vendorName;
			return this;
		}

		public Builder equipmentType(String equipmentType) {
			this.equipmentType = equipmentType;
			return this;
		}

		public Builder operatingSystem(String operatingSystem) {
			this.operatingSystem = operatingSystem;
			return this;
		}

		public Builder inputMode(String inputMode) {
			this.inputMode = inputMode;
			return this;
		}

		public Equipment build() {
			return new Equipment(this);
		}

	}
	
	public boolean isTACEqual(int tAC) {
		return this.tAC == tAC;
	}

	public int gettAC() {
		return tAC;
	}

	public void settAC(int tAC) {
		this.tAC = tAC;
	}

	public String getMarketingName() {
		return marketingName;
	}

	public void setMarketingName(String marketingName) {
		this.marketingName = marketingName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAccessCapability() {
		return accessCapability;
	}

	public void setAccessCapability(String accessCapability) {
		this.accessCapability = accessCapability;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getInputMode() {
		return inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}
}
