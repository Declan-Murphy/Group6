package org.dt340a.group6.sprint1.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Cause;
import org.dt340a.group6.sprint1.entity.Equipment;
import org.dt340a.group6.sprint1.entity.User;
import org.junit.Test;

public class PersistenceUtilTest {

	@Test//TODO
	public final void testPersistAllString() {

	}

	@Test//TODO
	public final void testPersistFailureClasses() {

	}

	@Test//TODO
	public final void testPersistEventCauses() {

	}

	@Test//TODO
	public final void testPersistCountryOperators() {

	}

	@Test//TODO
	public final void testPersistEquipment() {

	}

	@Test//TODO
	public final void testPersistCallFailures() {

	}

	@Test//TODO
	public final void testPersistAllListOfObject() {

	}

	@Test//TODO
	public final void testPersist() {

	}

	@Test//TODO
	public final void testRemove() {

	}

	@Test//TODO
	public final void testMerge() {

	}

	@Test//TODO
	public final void testCreateEM() {
//		assertEquals(PersistenceUtil.createEM().getClass(), EntityManager.class);
	}

	@Test
	public final void testFindCauseCode_EventIDByIMSI() {
		assertEquals(PersistenceUtil.findCauseCode_EventIDByIMSI("191911000516761").get(0).getClass(), CallFailure.class);
		assertNotEquals(PersistenceUtil.findCauseCode_EventIDByIMSI("191911000516761").get(0).getClass(), Cause.class);
		assertEquals(PersistenceUtil.findCauseCode_EventIDByIMSI("aaaa"), null);
		assertNotEquals(PersistenceUtil.findCauseCode_EventIDByIMSI("aaaa"), CallFailure.class);
	}

	@Test
	public final void testFindCallByIMSIBetweenDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy'T'hh:mm");
		try {
			Date startDateTime = sdf.parse("01/01/2013T00:00");
			Date endDateTime = sdf.parse("01/01/2014T00:00");
			assertEquals(PersistenceUtil.findCallByIMSIBetweenDate("191911000516761", startDateTime, endDateTime).get(0).getClass(), CallFailure.class);
			assertNotEquals(PersistenceUtil.findCallByIMSIBetweenDate("191911000516761", startDateTime, endDateTime).get(0).getClass(), Equipment.class);
			startDateTime = sdf.parse("01/01/4013T00:00");
			endDateTime = sdf.parse("01/01/4014T00:00");
			assertEquals(PersistenceUtil.findCallByIMSIBetweenDate("191911000516761", startDateTime, endDateTime), null);
			assertNotEquals(PersistenceUtil.findCallByIMSIBetweenDate("191911000516761", startDateTime, endDateTime), "null");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test//TODO
	public final void testFindCallFailureByTACInTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy'T'hh:mm");
		try {
			Date startDateTime = sdf.parse("01/01/2013T00:00");
			Date endDateTime = sdf.parse("01/01/2014T00:00");
//			assertEquals(PersistenceUtil.findCallFailureByTACInTime(101700, startDateTime, endDateTime).getClass(), List.class);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void testFindEquipmentByModel() {
		assertEquals(PersistenceUtil.findEquipmentByModel("Wireless CPU Q2687").get(0).gettAC(), 101700);
		assertNotEquals(PersistenceUtil.findEquipmentByModel("Wireless CPU Q2687").get(0).gettAC(), "tac");
		assertEquals(PersistenceUtil.findEquipmentByModel("Wireless CPU Q2687").get(0).getClass(), Equipment.class);
		assertNotEquals(PersistenceUtil.findEquipmentByModel("Wireless CPU Q2687").get(0).getClass(), Cause.class);
	}

	@Test//TODO
	public final void testFindCountBetweenTimesTotalDuration() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy'T'hh:mm");
		try {
			Date startDateTime = sdf.parse("01/01/2013T00:00");
			Date endDateTime = sdf.parse("01/01/2014T00:00");
//			assertEquals(PersistenceUtil.findCountBetweenTimesTotalDuration(startDateTime, endDateTime).get(0).get(0).getClass(), List.class);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test//TODO
	public final void testCountCauseCode() {
//		assertEquals(PersistenceUtil.countCauseCode(tAC, cause, event).get(0).getClass(), CallFailure.class);
	}

	@Test//TODO
	public final void testGroupCallFailureByTAC() {
		//can't find a TAC it works for...
//		assertEquals(PersistenceUtil.groupCallFailureByTAC(101700).getClass(), List.class);
//		assertEquals(PersistenceUtil.groupCallFailureByTAC(100700), null);

	}

	@Test 
	public final void testFindAllCallFailuresBetween(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy'T'hh:mm");
		try {
			Date startDateTime = sdf.parse("01/01/2013T00:00");
			Date endDateTime = sdf.parse("01/01/2014T00:00");
			assertSame(PersistenceUtil.findAllCallFailuresBetween(startDateTime, endDateTime).get(0).getClass(), CallFailure.class);
			assertNotSame(PersistenceUtil.findAllCallFailuresBetween(startDateTime, endDateTime).get(0).getClass(), Cause.class);
			startDateTime = sdf.parse("01/01/4014T00:00");
			endDateTime = sdf.parse("01/01/4015T00:00");
			assertSame(PersistenceUtil.findAllCallFailuresBetween(startDateTime, endDateTime), null);
			assertNotSame(PersistenceUtil.findAllCallFailuresBetween(startDateTime, endDateTime), "null");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void testFindAllCallFailures() {
		assertSame(PersistenceUtil.findAllCallFailures().get(0).getClass(), CallFailure.class);
		assertNotSame(PersistenceUtil.findAllCallFailures().get(0).getClass(), Cause.class);
	}

	@Test
	public final void testFindAllUsers() {
		assertSame(PersistenceUtil.findAllUsers().get(0).getClass(), User.class);
		assertNotSame(PersistenceUtil.findAllUsers().get(0).getClass(), CallFailure.class);
	}

	@Test
	public final void testFindUserByUsername() {
		assertSame(PersistenceUtil.findUserByUsername("admin").getClass(), User.class);
		assertNotSame(PersistenceUtil.findUserByUsername("admin").getClass(), Cause.class);
		assertEquals(PersistenceUtil.findUserByUsername("admin").getUsername(), "admin");
		assertNotEquals(PersistenceUtil.findUserByUsername("admin").getUsername(), "admin!");
		assertEquals(PersistenceUtil.findUserByUsername("PandaBear"), null);
		assertNotEquals(PersistenceUtil.findUserByUsername("PandaBear"), "null");
	}

}