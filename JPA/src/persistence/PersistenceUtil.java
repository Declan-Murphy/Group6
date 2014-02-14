package persistence;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import entity.UserEquipment;
import entity.EventCause;

import entity.FailureClass;
import entity.CountryOperator;
import entity.CallFailure;

public class PersistenceUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("dt340a");
	
	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void persistAll(List<Object> entityList) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();	
		for(Object entity: entityList){
			em.persist(entity);
		}
		em.getTransaction().commit();
		em.close();
	}
//	public static void fastPersistAll() {
//		//TODO Take this from FileUploadServlet ^^^
//		ImportExcel file = new ImportExcel("test.xls");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();	
//		for(int j=0; j<=30;j++){
//			for(int i = 1; i<=  file.getSheetLength(0); i++ ){
//				//PersistenceUtil.persist(new IMSI(getBDIMSI(i)));
//				em.persist(new IMSI(file.getBDIMSI(i)));
//			}
//		}	
//		em.getTransaction().commit();
//		em.close();
//		System.out.println("Fast persist Finished" );
//	}
	
	public static void remove(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Object merge(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();		
		em.close();
		return entity;
	}

	public static EntityManager createEM() {
		return emf.createEntityManager();
	}
	
	
//	public static List<Country> findAllCountries(){
//		EntityManager em = emf.createEntityManager();
//		List<Country> countries = (List<Country>) em.createNamedQuery("Country.findAll").getResultList();
//		em.close();
//		
//		return countries;
//	}
//
//	public static Country findCountryByCountryName(String countryName){
//		
//		EntityManager em = emf.createEntityManager();
//		List<Country> countries = (List<Country>) em.createNamedQuery("Country.findByCountryName").setParameter("countryName", countryName).getResultList();
//		em.close();
//		
//		if (countries.size() == 0)
//			return null;
//		else 
//			return countries.get(0);
//	}

	public static List<UserEquipment> findAllUserEquipment(){
		EntityManager em = emf.createEntityManager();
		List<UserEquipment> userEquipment = (List<UserEquipment>) em.createNamedQuery("UserEquipment.findAll").getResultList();
		em.close();
		
		return userEquipment;
	}

	public static UserEquipment findUserEquipmentByUEType(String ueType){
		
		EntityManager em = emf.createEntityManager();
		List<UserEquipment> userEquipment = (List<UserEquipment>) em.createNamedQuery("UserEquipment.findByUEType").setParameter("ueType", ueType).getResultList();
		em.close();
		
		if (userEquipment.size() == 0)
			return null;
		else 
			return userEquipment.get(0);
	}

	public static List<EventCause> findAllEventCauses(){
		EntityManager em = emf.createEntityManager();
		List<EventCause> eventCauses = (List<EventCause>) em.createNamedQuery("EventCause.findAll").getResultList();
		em.close();
		
		return eventCauses;
	}

	public static EventCause findEventCausesByCauseCode(String causeCode){
		
		EntityManager em = emf.createEntityManager();
		List<EventCause> eventCauses = (List<EventCause>) em.createNamedQuery("EventCause.findByCauseCode").setParameter("causeCode", causeCode).getResultList();
		em.close();
		
		if (eventCauses.size() == 0)
			return null;
		else 
			return eventCauses.get(0);
	}

//	public static List<IMSI> findAllIMSIs(){
//		EntityManager em = emf.createEntityManager();
//		List<IMSI> imsis = (List<IMSI>) em.createNamedQuery("IMSI.findAll").getResultList();
//		em.close();
//		
//		return imsis;
//	}
//
//	public static IMSI findIMSIByIMSINumber(String imsiNumber){
//		
//		EntityManager em = emf.createEntityManager();
//		List<IMSI> imsis = (List<IMSI>) em.createNamedQuery("IMSI.findByCauseCode").setParameter("imsiNumber", imsiNumber).getResultList();
//		em.close();
//		
//		if (imsis.size() == 0)
//			return null;
//		else 
//			return imsis.get(0);
//	}

	public static List<FailureClass> findAllFailureClasses(){
		EntityManager em = emf.createEntityManager();
		List<FailureClass> failureClasses = (List<FailureClass>) em.createNamedQuery("FailureClass.findAll").getResultList();
		em.close();
		
		return failureClasses;
	}

	public static FailureClass findFailureClassesByDescription(String description){
		
		EntityManager em = emf.createEntityManager();
		List<FailureClass> failureClasses = (List<FailureClass>) em.createNamedQuery("FailureClass.findByDescription").setParameter("imsiNumber", description).getResultList();
		em.close();
		
		if (failureClasses.size() == 0)
			return null;
		else 
			return failureClasses.get(0);
	}

	public static List<CountryOperator> findAllCountryOperator(){
		EntityManager em = emf.createEntityManager();
		List<CountryOperator> mccMncs = (List<CountryOperator>) em.createNamedQuery("MccMnc.findAll").getResultList();
		em.close();
		
		return mccMncs;
	}

	public static CountryOperator findMccMncByPhoneOperator(String phoneOperator){
		
		EntityManager em = emf.createEntityManager();
		List<CountryOperator> mccMncs = (List<CountryOperator>) em.createNamedQuery("MccMnc.findByPhoneOperator").setParameter("phoneOperator", phoneOperator).getResultList();
		em.close();
		
		if (mccMncs.size() == 0)
			return null;
		else 
			return mccMncs.get(0);
	}
	
	public static List<CallFailure> findAllCallFailures(){
		EntityManager em = emf.createEntityManager();
		List<CallFailure> callFailures = (List<CallFailure>) em.createNamedQuery("CallFailure.findAll").getResultList();
		em.close();
		
		return callFailures;
	}

	public static CallFailure findCallFailureByBaseDataID(int baseDataID){
		
		EntityManager em = emf.createEntityManager();
		List<CallFailure> callFailures = (List<CallFailure>) em.createNamedQuery("CallFailure.findBybaseDataID").setParameter("baseDataID", baseDataID).getResultList();
		em.close();
		
		if (callFailures.size() == 0)
			return null;
		else 
			return callFailures.get(0);
	}
}

