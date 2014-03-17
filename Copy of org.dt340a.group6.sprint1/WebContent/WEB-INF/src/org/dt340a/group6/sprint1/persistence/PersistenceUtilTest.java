package org.dt340a.group6.sprint1.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Cause;
import org.dt340a.group6.sprint1.entity.User;
import org.junit.Test;

public class PersistenceUtilTest {

	@Test
	public final void testPersistAllString() {
		
	}

	@Test
	public final void testPersistFailureClasses() {
		
	}

	@Test
	public final void testPersistEventCauses() {
		
	}

	@Test
	public final void testPersistCountryOperators() {
		
	}

	@Test
	public final void testPersistEquipment() {
		
	}

	@Test
	public final void testPersistCallFailures() {
		
	}

	@Test
	public final void testPersistAllListOfObject() {
		
	}

	@Test
	public final void testPersist() {
		
	}

	@Test
	public final void testRemove() {
		
	}

	@Test
	public final void testMerge() {
		
	}

	@Test
	public final void testCreateEM() {
		
	}

	@Test
	public final void testDropTablesIfExist() {
		
	}

	@Test
	public final void testFindAllCauses() {
		
	}

	@Test
	public final void testFindCausesByEventId() {
		
	}

	@Test
	public final void testFindCauseCode_EventIDByIMSI() {
		
	}

	@Test
	public final void testFindCallByIMSIBetweenDate() {
		
	}

	@Test
	public final void testFindAllCallFailure() {
		
	}

	@Test
	public final void testFindCallFailureByTAC() {
		
	}

	@Test
	public final void testFindCauseCode() {
		
	}

	@Test
	public final void testFindCallFailureByTACInTime() {
		
	}

	@Test
	public final void testFindEquipmentByEquipment_tAC() {
		
	}

	@Test
	public final void testFindEquipmentByModel() {
		
	}

	@Test
	public final void testFindDate() {
		
	}

	@Test
	public final void testFindCountBetweenTimesTotalDuration() {
		
	}

	@Test
	public final void testCountCauseCode() {
		
	}

//	@Test
//	public final void testGroupCallFailureByTAC() {
//		assertSame(PersistenceUtil.groupCallFailureByTAC(101700).get(0).getClass(), CallFailure.class);
//		assertNotSame(PersistenceUtil.groupCallFailureByTAC(101700).get(0).getClass(), Equipment.class);
//		assertSame(PersistenceUtil.groupCallFailureByTAC(101700).getClass(), List.class);
//		assertNotSame(PersistenceUtil.groupCallFailureByTAC(101700).getClass(), CallFailure.class);
//	}

	@Test
	public final void testFindAll() {
		assertSame(PersistenceUtil.findAll().get(0).getClass(), CallFailure.class);
		assertNotSame(PersistenceUtil.findAll().get(0).getClass(), Cause.class);
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
	}

}
