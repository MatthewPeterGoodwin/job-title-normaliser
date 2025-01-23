package com.mpg.normaliser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NormaliserTest {

    @Test
    public void testNormalise() {
        Normaliser normaliser = new Normaliser();

        assertEquals("Software engineer", normaliser.normalise("Java engineer"));
        assertEquals("Software engineer", normaliser.normalise("C# engineer"));
        assertEquals("Accountant", normaliser.normalise("Accountant"));
        assertEquals("Accountant", normaliser.normalise("Chief Accountant"));
    }

}
