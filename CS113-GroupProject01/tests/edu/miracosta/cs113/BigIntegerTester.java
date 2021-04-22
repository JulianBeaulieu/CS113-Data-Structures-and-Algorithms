package edu.miracosta.cs113;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class BigIntegerTester {


    /* SPRINT 1 TESTS */
    /**
     * Test helper for the toString method, ensuring all other tests will work
     * @param initial The initial value which the toString should return exactly
     */
    private void toStringTest(String initial) {
        BigInteger bigInt = new BigInteger(initial);
        assertEquals(initial, bigInt.toString());
    }

    /**
     * Basic test for the toString method, ensures that the bigInt can be read out as it was entered
     * @result Should always be the same as what was entered
     */
    @Test
    public void basicToStringTest(){
        toStringTest("123,456");
    }

    /**
     * Basic test for the toString method, ensures that the bigInt can be read out as it was entered
     * @result Should always be the same as what was entered
     */
    @Test
    public void basicNegativeToStringTest(){
        toStringTest("-123,456");
    }

    /**
     * Bigger test for the toString method, ensures that the bigInt can be read out as it was entered
     * @result Should always be the same as what we entered
     */
    @Test
    public void bigToStringTest(){
        toStringTest("46,376,937,677,490,009,712,648,124,896,970,078,050,417,018,260,538");
    }

    /**
     * Negative test for the toString method, ensures that the bigInt can be read out as it was entered
     * @result Should always be the same as what we entered
     */
    @Test
    public void bigNegativeToStringTest(){
        toStringTest("-46,376,937,677,490,009,712,648,124,896,970,078,050,417,018,260,538");
    }









    /* SPRINT 2 TESTS*/
    //remove @Ignore's below when ready to test Sprint 2!


    /**
     * A private method to make testing the addition method simpler, assumes a correct toString method,
     * so make sure your toString passes the given JUnit first.
     * @param initial The initial value for our instantiation of the bigInteger
     * @param add The number we wish to add to the bigInteger
     * @param expected The number we expect after the addition
     */
    private void additionTest(String initial, String add, String expected){
        BigInteger bigInt = new BigInteger(initial);
        bigInt.add(add);
        String stringAnswer = bigInt.toString();
        assertEquals(stringAnswer, expected);
    }





    /**
     * The simple addition test to make sure the BigInteger class is capable of carrying the tens place after an
     * addition
     * @result Should be fourteen as we carry the ten after adding
     */
    @Test
    public void additionCarryTest() {
        additionTest("5","9","14");
    }

    /**
     * Demonstrate that the bigInteger can operate when the initial is longer than the adder
     * @result The most common mistake would be returning 10 as it adds only until the adder is done and overwrites
     */
    @Test
    public void additionCarryTestMedium() {
        additionTest("9,999","1","10,000");
    }

    /**
     * Demonstrate that the bigInteger can operate when the adder is longer than the initial
     * @result The most common mistake would be returning 10 as it adds only until the initial is done
     */
    @Test
    public void additionCarryTestMediumSwap() {
        additionTest("1","9,999","10,000");
    }

    /**
     * Test that our carry works with a single digit and double digit number that is not simply testing recursive carry
     * @result Should represent we can carry when the tens place is already a 1 and changes it to a two, common
     *  error would be a result of 11 when the carry does not add to the pre-existing one
     */
    @Test
    public void additionCarryAndAddTest() {
        additionTest("7","14","21");
    }

    /**
     * Shows the carry works for numbers larger than int and long
     * @result Should show that we can carry any n distance
     */
    @Test
    public void additionCarryTestBig() {
        additionTest("9,999,999,999,999,999,999,999,999,999,999,999,999,999",
                "1",
                "10,000,000,000,000,000,000,000,000,000,000,000,000,000");
    }

    /**
     * A good test of the bigInteger's actual capacity to add large numbers
     * @result Shows that the bigInteger class's addition works for positive numbers
     */@Test
    public void additionCarryTestVeryBig() {
        additionTest("37,107,287,533,902,102,798,797,998,220,837,590,246,510,135,740,250",
                "46,376,937,677,490,009,712,648,124,896,970,078,050,417,018,260,538",
                "83,484,225,211,392,112,511,446,123,117,807,668,296,927,154,000,788");
    }

    /**
     * A simple test for negative numbers adding to a 0
     * @result Shows the case of 0 when the initial is negative and adder is negative
     */
    @Test public void additionNegativeTest(){
        additionTest("-1","1","0");
    }

    /**
     * A simple test for negative numbers adding to 0
     * @result Shows the case of 0 when the adder is negative and initial is positive
     */
    @Test public void additionNegativeTestSwap(){
        additionTest("1","-1","0");
    }

    /**
     * Simple check for adding two negatives
     * @result A common error may be returning -0 when it should return -2
     */
    @Test public void additionNegativeTestOnlyNegatives()
    {
        additionTest("-1","-1","-2");
    }









    /**
     * Makes testing subtraction easier just as the addition did, as with addition, this assumes your toString works
     * so make sure that JUnit works first.
     * @param initial The initial value of our BigInteger
     * @param subtract The number to subtract
     * @param expected The expected value
     */
    private void subtractTest(String initial, String subtract, String expected) {
        BigInteger bigInt = new BigInteger(initial);
        bigInt.subtract(subtract);
        String stringAnswer = bigInt.toString();
        assertEquals(stringAnswer, expected);
    }




    /**
     * Simple subtraction test to make sure the leading 0 is terminated
     * @result A common error may be 01 or accidental addition
     */
    @Test
    public void subtractionSimpleTest(){
        subtractTest("53", "52", "1");
    }

    /**
     * A simple check of subtraction and borrowing across multiple digits
     * @result A common error may be 3999 or 00001 or a reversed operation like 1997, there are many incorrect results
     * that could occur
     */
    @Test
    public void subtractionCarryTest(){
        subtractTest("2,000", "1,999", "1");
    }

    /**
     * Check subtraction when the initial value is larger than the subtractor
     * @result A common error may be -1 as only 0-1 was done and then the process terminates
     */
    @Test
    public void subtractionCarryTestSwap(){
        subtractTest("2,000", "1", "1,999");
    }

    /**
     * Check that we can render a negative number with a very simple subtraction
     * @result A common error may be 2 or 8
     */
    @Test
    public void subtractionNegativeTestPositives(){
        subtractTest("3", "5", "-2");
    }

    /**
     * Check negative subtraction when the intiial value is 1 digit and the subtractor is 2
     * @result A common error may be 84 in the case of reversed addition or something else
     */
    @Test
    public void subtractionNegativeTestPositives2(){
        subtractTest("4", "44", "-40");
    }

    /**
     * Check negative subtraction for a 2 digit and three digit where multiple borrows must happen
     * @result Depending on implementation this test can go ary in many different ways.
     */
    @Test
    public void subtractionNegativeTestPositives3(){
        subtractTest("44", "436", "-392");
    }

    /**
     * Show that subtracting a negative is the same as a positive
     * @result Common error would be actually subtracting and getting -392
     */
    @Test
    public void subtractionNegativeTestNegative(){
        subtractTest("44", "-436", "480");
    }

    /**
     * Check subtracting a negative from a positive works, this is really the same as adding in the negative world
     * @result A common mistake may be following the same logic as adding, so -44+436=-392
     */
    @Test
    public void subtractionNegativeTestNegativeSwap(){
        subtractTest("-44", "436", "-480");
    }

    /**
     * Check that subtracting a negative from a negative works
     * @result Should show that it is the same as adding the second value common error would be -480
     */
    @Test
    public void subtractionNegativeTestBothNegatives(){
        subtractTest("-436", "-44", "-392");
    }


    /**
     * Show that the subtraction works for large numbers
     * @result Demonstrates the bigInt works for huge numbers
     */
    @Test
    public void subtractionHugeTest(){
        subtractTest("46,376,937,677,490,009,712,648,124,896,970,078,050,417,018,260,538",
                "37,107,287,533,902,102,798,797,998,220,837,590,246,510,135,740,250",
                "9,269,650,143,587,906,913,850,126,676,132,487,803,906,882,520,288");
    }

}