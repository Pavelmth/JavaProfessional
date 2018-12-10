package ru.pavel.javaprofessional;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test1()
    {
        Transform transform = new Transform();
        int[] a = {7, 8, 10, 12, 16, 4, 5, 2};
        int[] b = {5, 2};

        Assert.assertArrayEquals(b, transform.transform(a));
    }

    @Test
    public void test2() {
        Check check = new Check();
        int[] a = {7, 8, 10, 12, 16, 4, 5, 2};

        Assert.assertEquals(true, check.check(a));
    }
}
