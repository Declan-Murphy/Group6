package org.dt340a.group6.sprint1.query.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.rpc.Call;

import org.junit.Test;
import org.mockito.Mockito;
import org.dt340a.group6.sprint1.entity.CallFailure;
import org.dt340a.group6.sprint1.entity.Cause;
import org.dt340a.group6.sprint1.query.UserStory12Query;
import org.dt340a.group6.sprint1.query.test.*;

public class UserStory12QueryTest {
	
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy'T'hh:mm");
		try {
			Date startDate = sdf.parse("01/01/2000T00:00");
			Date endDate = sdf.parse("01/01/2016T00:00");
			UserStory12Query usq = new UserStory12Query();
			assertSame(usq.findAllBetween(startDate, endDate).get(0).getClass(), CallFailure.class);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
