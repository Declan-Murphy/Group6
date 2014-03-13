package org.dt340a.group6.sprint1.entity;

import java.util.ArrayList;
import java.util.List;

public class AllMasterTableRows {
	
	private List<Object> failureClasses;
	private List<Object> causes;
	private List<Object> countryOperators;
	private List<Object> equipment;

	public AllMasterTableRows() {

	}

	public List<Object> getFailureClasses() {
		return failureClasses;
	}

	public void setFailureClasses(List<Object> failureClasses) {
		this.failureClasses = failureClasses;
	}

	public List<Object> getCauses() {
		return causes;
	}

	public void setCauses(List<Object> causes) {
		this.causes = causes;
	}

	public List<Object> getCountryOperators() {
		return countryOperators;
	}

	public void setCountryOperators(List<Object> countryOperators) {
		this.countryOperators = countryOperators;
	}

	public List<Object> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<Object> equipment) {
		this.equipment = equipment;
	}

}
