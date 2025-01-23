package com.mpg.normaliser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NormaliserTest {

    Normaliser normaliser = new Normaliser();

    @Test
    public void testNormaliseWithExactMatches() {

        assertEquals("Software engineer", normaliser.normalise("Software engineer"));
        assertEquals("Architect", normaliser.normalise("Architect"));
        assertEquals("Quantity surveyor", normaliser.normalise("Quantity surveyor"));
        assertEquals("Accountant", normaliser.normalise("Accountant"));
    }

    @Test
    public void testNormaliseWithSampleCode() {
        assertEquals("Software engineer", normaliser.normalise("Java engineer"));
        assertEquals("Software engineer", normaliser.normalise("C# engineer"));
        assertEquals("Accountant", normaliser.normalise("Chief Accountant"));
        assertEquals("Architect", normaliser.normalise("Building Architect"));
    }

    @Test
    public void testNormaliseWithCaseInsensitivity() {
        assertEquals("Software engineer", normaliser.normalise("SOFTWARE ENGINEER"));
        assertEquals("Architect", normaliser.normalise("architect"));
        assertEquals("Quantity surveyor", normaliser.normalise("QUANTITY SURVEYOR"));
        assertEquals("Accountant", normaliser.normalise("aCcOuNtAnT"));
    }

    @Test
    public void testNormaliseWithNullAndEmpty() {
        assertEquals("", normaliser.normalise(null));
        assertEquals("", normaliser.normalise(""));
        assertEquals("", normaliser.normalise("   "));
    }


    @Test
    public void testCalculateDistance() {
        assertEquals(0, normaliser.calculateDistance("test", "test"));
        assertEquals(1, normaliser.calculateDistance("test", "tests"));
        assertEquals(3, normaliser.calculateDistance("kitten", "sitting"));
        assertEquals(2, normaliser.calculateDistance("flaw", "lawn"));
        assertEquals(2, normaliser.calculateDistance("distance", "instance"));
    }

}