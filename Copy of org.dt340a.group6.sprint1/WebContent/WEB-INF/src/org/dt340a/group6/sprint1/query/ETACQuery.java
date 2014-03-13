package org.dt340a.group6.sprint1.query;

import java.util.List;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Equipment;

/**
 * @author dr206
 *
 */
public class ETACQuery {

	
	/**
	 * 
	 */
	public ETACQuery() {
		//printData(viewInfoForETAC(21060800));
		printData(viewInfoForETAC("Test IMEI"));
	}
	
	/**
	 * @param callFailures
	 */
	public void printData(List<Equipment> equipment) {
		int count=0;
		System.out.println(equipment.size());
		
		for(Equipment equip : equipment){
			System.out.println("EQUIPMENT QUERY");
			System.out.println("Count is "+count);
//			System.out.println("The tac is: "+equip.gettAC()+ " exists.");
//			System.out.println("The Model is: "+equip.getModel()+ " exists.");
			
			System.out.println("Model: " + equip.getModel() + "\nTAC: " + equip.gettAC());
			List<CallFailure> callFailures = new IMSIQuery().viewInfoForTAC(equip.gettAC());
			if(callFailures != null){
				System.out.println("Number of Call Failures for that model: " + callFailures.size());
			}
			else{
				System.out.println("There are no Call failures because that TAC returned null");
			}
			
			
			
//			System.out.println("The event Id is: "+fail. " exists.");
//			System.out.println("The cause code is: "+(int)fail.getCause().getCauseCode()+ " exists.");
			System.out.println();
			count++;
		}
	}
	
	/**
	 * @param IMSI
	 * @return
	 */
	public List<Equipment> viewInfoForETAC(int ETAC){
		return PersistenceUtil.findEquipmentByEquipment_tAC(ETAC);
	}
	
	public List<Equipment> viewInfoForETAC(String model){
		return PersistenceUtil.findEquipmentByModel(model);
	}	

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("RUNNING QUERY");
		new ETACQuery();
	}
	
}
