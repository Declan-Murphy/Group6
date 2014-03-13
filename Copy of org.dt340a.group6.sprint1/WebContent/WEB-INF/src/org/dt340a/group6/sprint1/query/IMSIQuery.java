package org.dt340a.group6.sprint1.query;

import java.util.List;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Cause;
import org.dt340a.group6.sprint1.entity.Equipment;

/**
 * @author dr206
 *
 */
public class IMSIQuery {

	
	/**
	 * 
	 */
	public IMSIQuery() {
		//printData(viewInfoForIMSI("240210000000003"));
		printData(viewInfoForAll());
	}

	
	/**
	 * @param callFailures
	 */
	public void printData(List<CallFailure> callFailures) {
		int count=0;
		System.out.println(callFailures.size());
		for(CallFailure fail : callFailures){
			System.out.println("CALL FAILURE QUERY");
			System.out.println("Count is "+count);
			System.out.println("The IMSI is: "+fail.getiMSI()+ " exists.");
			System.out.println("The event Id is: "+(int)fail.getCause().getEventId()+ " exists.");
			System.out.println("The cause code is: "+(int)fail.getCause().getCauseCode()+ " exists.");
			System.out.println();
			count++;
		}
	}
	
	/**
	 * @param IMSI
	 * @return
	 */
	public List<CallFailure> viewInfoForIMSI(String IMSI){
		return PersistenceUtil.findCauseCode_EventIDByIMSI(IMSI);
	}
	public List<CallFailure> viewInfoForAll(){
		return PersistenceUtil.findAllCallFailure();
	}	
	public List<CallFailure> viewInfoForTAC(int tac){
		return PersistenceUtil.findCallFailureByTAC(tac);
	}	
	public List<CallFailure> viewInfoForTACInTime(int tac){
		return PersistenceUtil.findCallFailureByTACInTime(tac);
	}	
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		//new IMSIQuery().viewInfoForTAC(21060800);
		//new IMSIQuery().viewInfoForTACInTime(21060800);
		new IMSIQuery();
//		
//		List<Equipment> causeList = PersistenceUtil.findEquipmentByModel("vea3");
//		causeList.get(0).gettAC();
//		
//		List<CallFailure> causeList2 = PersistenceUtil.findCallFailureByTAC(causeList.get(0).gettAC());
//		
//		causeList2.get(0)
//		List<Cause> causeList3 = PersistenceUtil.findCauseCode(causeList2.get(0).getCause(),4097);
//		
//		
//		System.out.println("Description is : " + causeList.get(0).getDescription() );
	}
	
}
