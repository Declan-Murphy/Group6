package org.dt340a.group6.sprint1.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dt340a.group6.sprint1.entity.*;
import org.dt340a.group6.sprint1.fileImport.*;

public class PersistenceUtil implements Serializable {

	private static final long serialVersionUID = -2227022978224595895L;
	private FailureClassReader failureClassReader;
	private CauseReader causeReader;
	private CountryOperatorReader countryOperatorReader;
	private EquipmentReader equipmentReader;
	private CallFailureReader callFailureReader;
	private AllMasterTableRows allMasterTableRows;

	public static String filePath;// ="test.xls";

	protected static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("sprint1");

	public PersistenceUtil() {
		super();
		failureClassReader = new FailureClassReader();
		causeReader = new CauseReader();
		countryOperatorReader = new CountryOperatorReader();
		equipmentReader = new EquipmentReader();
		callFailureReader = new CallFailureReader();
		allMasterTableRows = new AllMasterTableRows();
	}

	public static void persistAll(String fileName) {
		PersistenceUtil.filePath = fileName;
		PersistenceUtil persistenceUtil = new PersistenceUtil();
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		persistenceUtil.persistFailureClasses(entityManager);

		persistenceUtil.persistEventCauses(entityManager);

		persistenceUtil.persistCountryOperators(entityManager);

		persistenceUtil.persistEquipment(entityManager);

		persistenceUtil.persistCallFailures(entityManager);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void persistFailureClasses(EntityManager entityManager) {
		// System.out.println("FailureClasses start");
		allMasterTableRows.setFailureClasses(failureClassReader
				.getAllFailureClassRows());
		for (Object row : allMasterTableRows.getFailureClasses()) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
		// System.out.println("FailureClasses end");
	}

	public void persistEventCauses(EntityManager entityManager) {
		// System.out.println("EventCauses start");
		allMasterTableRows.setCauses(causeReader.getAllEventCauseRows());
		for (Object row : allMasterTableRows.getCauses()) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
		// System.out.println("EventCauses end");
	}

	public void persistCountryOperators(EntityManager entityManager) {
		// System.out.println("CountryOperators start");
		allMasterTableRows.setCountryOperators(countryOperatorReader
				.getAllCountryOperatorRows());
		for (Object row : allMasterTableRows.getCountryOperators()) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
		// System.out.println("CountryOperators end");
	}

	public void persistEquipment(EntityManager entityManager) {
		// System.out.println("Equipment start");
		allMasterTableRows.setEquipment(equipmentReader.getAllEquipmentRows());
		for (Object row : allMasterTableRows.getEquipment()) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
		// System.out.println("Equipment end");
	}

	public void persistCallFailures(EntityManager entityManager) {
		// System.out.println("CallFailures start");
		for (Object row : callFailureReader
				.getAllCallFailureRows(allMasterTableRows)) {
			// entityManager.persist(row);
			entityManager.merge(row);
		}
		// System.out.println("CallFailures end");
	}

	public static void persistAll(List<Object> entityList) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Object entity : entityList) {
			em.persist(entity);
		}
		em.getTransaction().commit();
		em.close();
	}

	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

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

	/* ******************************************************************
	 * Methods to manage the database structure
	 * *****************************************************************
	 */
	public static void dropTablesIfExist() {
		System.out.println("Drop tables started");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery(
				"drop tables if exists CallFailure, Cause, CountryOperator, Equipment, FailureClass;")
				.executeUpdate();
		em.getTransaction().commit();
		em.close();
		System.out.println("Drop tables completes");
	}

	/* ******************************************************************
	 * Methods relating to the entities SQL queries
	 * *****************************************************************
	 */
	public static List<Cause> findAllCauses() {
		EntityManager em = emf.createEntityManager();
		List<Cause> causes = (List<Cause>) em.createNamedQuery("Cause.findAll")
				.getResultList();
		em.close();

		return causes;
	}

	public static List<Cause> findCausesByEventId(double eventId) {
		EntityManager em = emf.createEntityManager();
		List<Cause> causes = (List<Cause>) em
				.createNamedQuery("Cause.findByEvent_id")
				.setParameter("eventId", eventId).getResultList();
		em.close();

		if (causes.size() == 0)
			return null;
		else
			return causes;
	}

	// TODO why is this called findCausecode???
	public static List<CallFailure> findCauseCode_EventIDByIMSI(String IMSI) {

		EntityManager em = emf.createEntityManager();
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery("CallFailure.findByIMSI")
				.setParameter("IMSI", IMSI).getResultList();
		em.close();

		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}

	public static List<CallFailure> findCallByIMSIBetweenDate(String IMSI,
			Date startDateTime, Date endDateTime) {

		EntityManager em = emf.createEntityManager();
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery("CallFailure.findImsiBetween")
				.setParameter("IMSI", IMSI)
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		em.close();

		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}

	public static List<CallFailure> findAllCallFailure() {

		EntityManager em = emf.createEntityManager();
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery("CallFailure.findAll").getResultList();
		em.close();

		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}

	public static List<CallFailure> findCallFailureByTAC(int tac) {

		EntityManager em = emf.createEntityManager();
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery("CallFailure.findByTAC")
				.setParameter("TAC", tac).getResultList();
		em.close();

		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}

	public static List<Cause> findCauseCode(double cause, double event) {
		// eventId and o.causeCode =:causeCode
		EntityManager em = emf.createEntityManager();
		List<Cause> causeList = (List<Cause>) em.createNamedQuery("Cause.find")
				.setParameter("eventId", event)
				.setParameter("causeCode", cause).getResultList();
		em.close();

		if (causeList.size() == 0)
			return null;
		else
			return causeList;
	}

	public static List<CallFailure> findCallFailureByTACInTime(int tac,
			Date startDateTime, Date endDateTime) {

		EntityManager em = emf.createEntityManager();
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery("CallFailure.findByTACInTime")
				.setParameter("TAC", tac)
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		em.close();

		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}

	// public static List<CallFailure> findCallByIMSIBetweenDate(String IMSI,
	// Date startDateTime, Date endDateTime){
	//
	// EntityManager em = emf.createEntityManager();
	// List<CallFailure> callFailures = (List<CallFailure>)
	// em.createNamedQuery("CallFailure.findImsiBetween").setParameter("IMSI",
	// IMSI).setParameter("startDateTime",
	// startDateTime).setParameter("endDateTime", endDateTime).getResultList();
	// em.close();
	//
	// if (callFailures.size() == 0)
	// return null;
	// else
	// return callFailures;
	// }

	public static List<Equipment> findEquipmentByEquipment_tAC(int eTAC) {
		EntityManager em = emf.createEntityManager();
		List<Equipment> equipment = (List<Equipment>) em
				.createNamedQuery("Equipment.findByTac")
				.setParameter("Tac", eTAC).getResultList();
		em.close();

		if (equipment.size() == 0)
			return null;
		else
			return equipment;
	}

	public static List<Equipment> findEquipmentByModel(String model) {
		EntityManager em = emf.createEntityManager();
		List<Equipment> equipment = (List<Equipment>) em
				.createNamedQuery("Equipment.findByModel")
				.setParameter("MODEL", model).getResultList();
		em.close();

		if (equipment.size() == 0)
			return null;
		else
			return equipment;
	}

	public static List<CallFailure> findDate(Date startDate) {
		EntityManager em = emf.createEntityManager();
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery("CallFailure.findDate")
				.setParameter("startDate", startDate).getResultList();
		em.close();

		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}

	public static List<List> findCountBetweenTimesTotalDuration(
			Date startDateTime, Date endDateTime) {
		EntityManager em = emf.createEntityManager();
		List<List> us09List = (List<List>) em
				.createNamedQuery(
						"CallFailure.findCountBetweenTimesTotalDuration")
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		em.close();

		if (us09List.size() == 0)
			return null;
		else
			return us09List;
	}

	public static List<CallFailure> countCauseCode(int tAC, double cause,
			double event) {
		// eventId and o.causeCode =:causeCode
		EntityManager em = emf.createEntityManager();
		List<CallFailure> causeList = (List<CallFailure>) em
				.createNamedQuery("CallFailure.countByEventAndCause")
				.setParameter("TAC", tAC).setParameter("EVENT", event)
				.setParameter("CAUSE", cause).getResultList();
		em.close();

		if (causeList.size() == 0)
			return null;
		else
			return causeList;
	}

	public static List<CallFailure> groupCallFailureByTAC(int tac) {
		EntityManager em = emf.createEntityManager();
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery("CallFailure.groupByTAC")
				.setParameter("TAC", tac).getResultList();
		em.close();
		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}

	public static List<CallFailure> findAllBetween(Date startDateTime,
			Date endDateTime) {
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery("CallFailure.findAllBetween")
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		em.close();
		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}

	public static List<CallFailure> findCountOfOccurancesForGivenIMSI(
			String imsi) {
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<CallFailure> us12List = (List<CallFailure>) em
				.createNamedQuery(
						"CallFailure.findCountOfOccurancesForGivenIMSI")
				.setParameter("iMSI", imsi).getResultList();

		em.close();
		if (us12List.size() == 0)
			return null;
		else
			return us12List;
	}

	public static List<CallFailure> findAllIMSIsWithCallFailureGivenTime(
			Date startDateTime, Date endDateTime) {
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery(
						"CallFailure.findAllIMSIsWithCallFailureGivenTime")
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		em.close();
		System.out.print(callFailures.get(0).getBaseDataID());
		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}

	public static List<CallFailure> findAll() {
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<CallFailure> callFailures = (List<CallFailure>) em
				.createNamedQuery("CallFailure.findAll").getResultList();
		em.close();
		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
	}
	
	public static List<User> findAllUsers(){
		EntityManager em = emf.createEntityManager();
		List<User> users = (List<User>) em.createNamedQuery("User.findAll").getResultList();
		em.close();		
		return users;		
	}
	
	public static User findUserByUsername(String username){
		
		EntityManager em = emf.createEntityManager();
		List<User> users = (List<User>) em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
		em.close();
		
		if (users.size() == 0)
			return null;
		else 
			return users.get(0);
	}
}
