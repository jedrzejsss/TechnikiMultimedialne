/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klawiatura;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jedrzej
 */
public class OdleglosciMiedzyKlawiszamiTest {
    
    public OdleglosciMiedzyKlawiszamiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dajRoznice method, of class OdleglosciMiedzyKlawiszami.
     */
    @Test
    public void testDajRoznice() {
        System.out.println("dajRoznice");
        String a = "";
        String b = "";
        OdleglosciMiedzyKlawiszami instance = new OdleglosciMiedzyKlawiszami();
        int expResult = 0;
        int result = instance.dajRoznice(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
