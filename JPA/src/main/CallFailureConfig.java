//package main;
//
//import java.util.Date;
//import java.util.List;
//
//import persistence.PersistenceUtil;
//import entity.CallFailure;
//
//
//public class CallFailureConfig {
//
//	public static void main(String[] args){
//		CallFailureConfig config = new CallFailureConfig();
//	}
//
//	public CallFailureConfig(){
//		createCallFailure(new Date(), 132, 123, 123, 123,123, 231, 456, "123", "465", "123", "46", "456");
//	}
//
//
//	public void viewSubscriber(){
//		List<CallFailure> callFailures = PersistenceUtil.findAllCallFailures();
//		for(CallFailure cf : callFailures){
//			System.out.println("CallFailure "+cf.getBaseDataID()+ " exists.");
//		}
//	}
//	
//	public void createCallFailure(Date dateTime, int eventCauseID, int failureClassID, int ueTypeTacID, int mcc,int mnc, int cellID, int duration, String neVersion, String imsi, String hier3ID, String hier32ID, String hier321ID){
//		CallFailure callFailure = new CallFailure(dateTime, eventCauseID, failureClassID, ueTypeTacID, mcc,mnc, cellID, duration, neVersion, imsi, hier3ID, hier32ID, hier321ID);
//		PersistenceUtil.persist(callFailure);
//		System.out.println("CallFailure Created");
//	}
//	
//}
