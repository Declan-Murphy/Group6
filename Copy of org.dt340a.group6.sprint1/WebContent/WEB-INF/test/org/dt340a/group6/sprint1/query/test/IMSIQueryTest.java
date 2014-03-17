package org.dt340a.group6.sprint1.query.test;

import static org.junit.Assert.*;

import java.awt.List;

import org.junit.Test;

import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Equipment;
import org.dt340a.group6.sprint1.query.IMSIQuery;

public class IMSIQueryTest {
	
	@Test
	public final void testTypeTrueViewInfoForIMSI() {
		IMSIQuery imsiQuery = new IMSIQuery();
		assertTrue("Show first Callfailure for given IMSI, 310560000000012 be type "
				+ "CallFailure", imsiQuery.viewInfoForIMSI("310560000000012").get(0).getClass().equals(CallFailure.class));
	}
	
	@Test
	public final void testTypeFalseViewInfoForIMSI() {
		IMSIQuery imsiQuery = new IMSIQuery();
		assertFalse("Show first Callfailure for given IMSI, 310560000000012 be type "
				+ "CallFailure", imsiQuery.viewInfoForIMSI("310560000000012").get(0).getClass().equals(Equipment.class));
	}
	
	@Test
	public final void testTrueViewInfoForIMSI() {
		IMSIQuery imsiQuery = new IMSIQuery();
		String result = imsiQuery.viewInfoForIMSI("310560000000012").get(0).getiMSI();
		assertTrue("Find first Callfailure .getiMSI for given IMSI, 310560000000012 should "
				+ "return 310560000000012", "310560000000012".equals(result));
	}
	
	@Test
	public final void testFalseViewInfoForIMSI() {
		IMSIQuery imsiQuery = new IMSIQuery();
		String result = imsiQuery.viewInfoForIMSI("310560000000012").get(0).getiMSI();
		assertFalse("Find first Callfailure .getiMSI for given IMSI, 310560000000012 should "
				+ "return 310560000000012, not 4125", "4125".equals(result));
	}

}
