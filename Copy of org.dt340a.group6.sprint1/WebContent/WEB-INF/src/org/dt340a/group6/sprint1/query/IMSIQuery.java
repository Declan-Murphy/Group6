package org.dt340a.group6.sprint1.query;

import java.util.List;

import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.entity.CallFailure;

/**
 * @author dr206
 *
 */
public class IMSIQuery {

	
	/**
	 * 
	 */
	public IMSIQuery() {
//		printData(viewInfoForIMSI("240210000000003"));
	}
	
	/**
	 * @param callFailures
	 */
	public void printData(List<CallFailure> callFailures) {
		int count=0;
		System.out.println(callFailures.size());
		for(CallFailure fail : callFailures){
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
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		new IMSIQuery();
	}
	
}
