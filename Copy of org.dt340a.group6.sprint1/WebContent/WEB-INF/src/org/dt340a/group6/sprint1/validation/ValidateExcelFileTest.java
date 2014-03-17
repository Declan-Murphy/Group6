package org.dt340a.group6.sprint1.validation;

import static org.junit.Assert.*;

import org.dt340a.group6.sprint1.fileImport.FileReader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ValidateExcelFileTest {

    FileReader goodFileReader;
    FileReader badFileReader;

    @Before
    public void setUp() throws Exception {
        goodFileReader = new FileReader("testA.xls");
        badFileReader = new FileReader("badData.xls");
    }
    
    @Test
    public void isBaseDataValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isBaseDataValid();
        assertTrue(check);
    }
    @Test
    public void isBaseDataValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isBaseDataValid();
        assertFalse(check);
    }
    
    @Test
    public void isEventCauseValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isEventCauseValid();
        assertTrue(check);
    }
    @Test
    public void isEventCauseValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isEventCauseValid();
        assertFalse(check);
    }
    
    @Test
    public void isFailureClassValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isFailureClassValid();
        assertTrue(check);
    }
    @Test
    public void isFailureClassValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isFailureClassValid();
        assertFalse(check);
    }
       
    @Test
    public void isUETableValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isUETableValid();
        assertTrue(check);
    }
    @Test
    public void isUETableValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isUETableValid();
        assertFalse(check);
    }
    
    @Test
    public void isMCCMNCValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isMCCMNCValid();
        assertTrue(check);
    }
    @Test
    public void isMCCMNCValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isMCCMNCValid();
        assertFalse(check);
    }
    
    @Test
    public void isXLSValidTrue() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isXLSValid();
        assertTrue(check);
    }
    @Test
    public void isXLSValidFalse() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isXLSValid();
        assertFalse(check);
    }

}
