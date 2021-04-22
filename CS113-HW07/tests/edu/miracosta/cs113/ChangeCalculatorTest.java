package edu.miracosta.cs113;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ChangeCalculatorTest {

    /**
     * This prints out the change which was calculated
     * @param change
     */
    private void displayChange(ArrayList<String> change)
    {
        //loops through array and prints out the possibilities
        for(String element : change)
        {
            System.out.println(element);
        }
    }

    //tests for no change or a null
    @Test
    public void testForNoChange() {
        assertEquals("This should return 0, because 0 change has 0 combinations", 0, ChangeCalculator.changeCalculator(0).size());
    }

    @Test
    public void testForNegative() {
        assertEquals("This should return 0, you can't have negative change", 0, ChangeCalculator.changeCalculator(-2).size());
    }

    /**
     * These next methods will test from 25 to 50 and then 75 cents
     **/

    @Test
    public void test25Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(25);

        displayChange(change);

        assertEquals("This should return 13", 13, change.size());
    }

    @Test
    public void test30Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(30);

        displayChange(change);

        assertEquals("This should return 18", 18, change.size());
    }

    @Test
    public void test35Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(35);

        displayChange(change);

        assertEquals("This should return 24", 24, change.size());
    }

    @Test
    public void test40Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(40);

        displayChange(change);

        assertEquals("This should return 31", 31, change.size());
    }

    @Test
    public void test45ents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(45);

        displayChange(change);

        assertEquals("This should return 39", 39, change.size());
    }

    @Test
    public void test50Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(50);

        displayChange(change);

        assertEquals("This should return 49", 49, change.size());
    }

    @Test
    public void test75Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(75);

        displayChange(change);

        assertEquals("This should return 121", 121, change.size());
    }

    /**
     * These next methods will test from 20 to 30cents
     **/

    @Test
    public void test20Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(20);

        displayChange(change);

        assertEquals("This should return 9", 9, change.size());
    }

    @Test
    public void test21Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(21);

        displayChange(change);

        assertEquals("This should return 9", 9, change.size());
    }

    @Test
    public void test22Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(22);

        displayChange(change);

        assertEquals("This should return 9", 9, change.size());
    }

    @Test
    public void test23Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(23);

        displayChange(change);

        assertEquals("This should return 9", 9, change.size());
    }

    @Test
    public void test24Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(24);

        displayChange(change);

        assertEquals("This should return 9", 9, change.size());
    }

    @Test
    public void test26Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(26);

        displayChange(change);

        assertEquals("This should return 13", 13, change.size());
    }
    @Test
    public void test27Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(27);

        displayChange(change);

        assertEquals("This should return 13", 13, change.size());
    }

    @Test
    public void test28Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(28);

        displayChange(change);

        assertEquals("This should return 13", 13, change.size());
    }

    @Test
    public void test29Cents()
    {
        ArrayList<String> change = ChangeCalculator.changeCalculator(29);

        displayChange(change);

        assertEquals("This should return 13", 13, change.size());
    }

    /**
     * This next method will test extreme case of 100 cents
     **/

     @Test
     public void test100Cents()
     {
        ArrayList<String> change = ChangeCalculator.changeCalculator(100);

        displayChange(change);

        assertEquals("This should return 242", 242, change.size());
     }
}
