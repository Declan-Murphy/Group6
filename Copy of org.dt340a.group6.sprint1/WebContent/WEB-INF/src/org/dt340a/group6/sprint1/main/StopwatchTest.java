package org.dt340a.group6.sprint1.main;

import static org.junit.Assert.*;

import org.junit.Test;

public class StopwatchTest {

	@Test
	public final void testStopwatchAndElapsedTime() {
		Stopwatch sw = new Stopwatch();
		assertTrue(sw.elapsedTime()>0);
	}

}
