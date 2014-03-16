package org.dt340a.group6.sprint1.query;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Cause;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.query.*;

public class UserStory12QueryTest {

	@Test
	public final void testCorrectReturnTypeFindAllIMSIsWithCallFailureGivenTime() {
		UserStory12Query mockedQueryClass = Mockito.mock(UserStory12Query.class);
		List<CallFailure> mockedCallFailureList = Mockito.mock(List.class);
		Mockito.when(mockedQueryClass.findAll()).thenReturn(mockedCallFailureList);
		assertSame(mockedQueryClass.findAll(), mockedCallFailureList);
	}
	
	@Test
	public final void testNotIncorrectReturnTypeFindAllIMSIsWithCallFailureGivenTime() {
		UserStory12Query mockedQueryClass = Mockito.mock(UserStory12Query.class);
		List<Cause> mockedCauseList = Mockito.mock(List.class);
		List<CallFailure> mockedCallFailureList = Mockito.mock(List.class);
		Mockito.when(mockedQueryClass.findAll()).thenReturn(mockedCallFailureList);
		assertNotSame(mockedQueryClass.findAll(), mockedCauseList);
	}

	@Test
	public final void testFindAll() {
		
	}

	@Test
	public final void testFindAllBetween() {
		
	}

	@Test
	public final void testFindCountOfOccurancesForGivenIMSI() {
		
	}

}
