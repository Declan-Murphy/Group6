package org.dt340a.group6.sprint1.testingJUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.dt340a.group6.sprint1.fileImport.test.*;
import org.dt340a.group6.sprint1.query.test.*;
import org.dt340a.group6.sprint1.validation.*;
import org.dt340a.group6.sprint1.validation.test.PrimitiveCheckTest;

@RunWith(Suite.class)
@SuiteClasses({ CallFailureReaderTest.class, CauseReaderTest.class,
		CountryOperatorReaderTest.class, EquipmentReaderTest.class,
		FailureClassReaderTest.class, FileReaderTest.class,
		PrimitiveCheckTest.class, IMSIQueryTest.class })
public class Sprint0_TestSuite {

}
