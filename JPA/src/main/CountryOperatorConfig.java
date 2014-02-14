package main;

import java.util.List;

import persistence.PersistenceUtil;
import entity.CountryOperator;

public class CountryOperatorConfig {
	
	public static void main(String[] args){
		CountryOperatorConfig config = new CountryOperatorConfig();
	}

	public CountryOperatorConfig(){
		createCountryOperator(798, 456, "vodafone", "Ireland");
	}


	public void viewFailureClass(){
		List<CountryOperator> mccMncs = PersistenceUtil.findAllCountryOperator();
		for(CountryOperator m : mccMncs){
			System.out.println("MccMnc " + m.getPhoneOperator()+ " exists.");
		}
	}
	
	public void createCountryOperator(int mcc, int mnc, String phoneOperator, String country){
		CountryOperator mccMnc = new CountryOperator(mcc, mnc, phoneOperator, country);
		PersistenceUtil.persist(mccMnc);
		System.out.println("MccMnc Created");
	}
	
}
