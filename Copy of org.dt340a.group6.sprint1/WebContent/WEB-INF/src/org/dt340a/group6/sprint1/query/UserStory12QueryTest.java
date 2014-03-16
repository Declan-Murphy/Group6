package org.dt340a.group6.sprint1.query;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.Call;

import org.junit.Test;
import org.mockito.Mockito;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Cause;
import org.dt340a.group6.sprint1.query.*;

public class UserStory12QueryTest {

	@Test
	public final void testCorrectReturnTypeFindAllIMSIsWithCallFailureGivenTime() {
		
	}
	
	@Test
	public final void testIncorrectReturnFindAll() {
		UserStory12Query usq = new UserStory12Query();		
		assertNotSame(usq.findAll().get(0).getClass(), Cause.class );
	}

	@Test
	public final void testCorrectFindAll() {
		UserStory12Query usq = new UserStory12Query();		
		assertSame(usq.findAll().get(0).getClass(), CallFailure.class );
	}

	@Test
	public final void testFindAllBetween() {
		
	}

	@Test
	public final void testFindCountOfOccurancesForGivenIMSI() {
		
	}

}
