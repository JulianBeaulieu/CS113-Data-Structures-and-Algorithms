package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorModelTest {

    private static CalculatorModel cM ;

    @Before
    public void runBefore()
    {
        cM = new CalculatorModel();
    }

    //tests for error checking

    @Test
    public void evaluateNotLegalX()
    {
        assertEquals("Should return Error", "Error", cM.evaluate("x"));
    }

    @Test
    public void evaluateNotLegalExponentSign()
    {
        assertEquals("Should return Error", "Error", cM.evaluate("^"));
    }

    @Test
    public void evaluateNotLegalDividingByZero()
    {
        assertEquals("Should return Error", "Error", cM.evaluate("7 / 0"));
    }

    @Test
    public void evaluateTestEmptyString() {
        assertEquals("Should return an Empty String", "", cM.evaluate(""));
    }

    @Test
    public void evaluateTestZero() {
        assertEquals("Should return 0", "0", cM.evaluate("0"));
    }

    @Test
    public void evaluateTestJustNumber() {
        assertEquals("Should return 6", "6", cM.evaluate("6"));
    }

    //simple math tests

    @Test
    public void evaluateTestSimpleAdditionWithWhiteSpaces() {
        assertEquals("Should return 4", "4", cM.evaluate("2 + 2"));
    }

    @Test
    public void evaluateTestSimpleSubtractionWithWhiteSpaces() {
        assertEquals("Should return 4", "4", cM.evaluate("6 - 2"));
    }

    @Test
    public void evaluateTestSimpleMultiplicationWithWhiteSpaces() {
        assertEquals("Should return 4", "12", cM.evaluate("6 * 2"));
    }

    @Test
    public void evaluateTestSimpleDivisionWithWhiteSpaces() {
        assertEquals("Should return 4", "3", cM.evaluate("6 / 2"));
    }

    //order of operation tests with out parentheses

    @Test
    public void evaluateTestOrderOfOperationWithOutParentheses1(){
        assertEquals("Should return 7", "7", cM.evaluate("1 + 2 * 3"));
    }

    @Test
    public void evaluateTestOrderOfOperationWithOutParentheses2(){
        assertEquals("Should return -3", "-3", cM.evaluate("4 - 14 / 2"));
    }

    @Test
    public void evaluateTestOrderOfOperationWithOutParentheses3(){
        assertEquals("Should return 16", "16", cM.evaluate("16 * 3 + 4 - 18 * 2"));
    }
    @Test
    public void evaluateTestOrderOfOperationWithOutParentheses4(){
        assertEquals("Should return 104", "104", cM.evaluate("20 / 2 * 2 * 5 + 4 "));
    }


    //order of operation tests with parentheses

    @Test
    public void evaluateTestOrderOfOperationWithParentheses1(){
        assertEquals("Should return 9", "9", cM.evaluate("( 1 + 2 ) * 3"));
    }

    @Test
    public void evaluateTestOrderOfOperationWithParentheses2(){
        assertEquals("Should return -5", "-5", cM.evaluate("( 4 - 14 ) / 2"));
    }

    @Test
    public void evaluateTestOrderOfOperationWithParentheses3(){
        assertEquals("Should return 188", "188", cM.evaluate("( ( 16 * ( 3 + 4 ) ) - 18 ) * 2"));
    }
    @Test
    public void evaluateTestOrderOfOperationWithParentheses4(){
        assertEquals("Should return 180", "180", cM.evaluate("( 20 / 2 ) * ( 2 * ( 5 + 4 ) )"));
    }


    //#############################################
    //derivative tests
    //#############################################







    @Test
    public void deriveTestEmptyString() {
        assertEquals("Should return error", "Error", cM.derive(""));
        assertEquals("Should return error", "Error", cM.derive(" "));
    }

    @Test
    public void deriveTestInvalidString() {
        assertEquals("Should return error", "Error", cM.derive("/"));
        assertEquals("Should return error", "Error", cM.derive("*"));
    }

    @Test
    public void deriveTestZero() {
        assertEquals("Should return 0", "0", cM.derive("0"));
    }

    @Test
    public void deriveTestJustNumber() {
        assertEquals("Should return 0", "0", cM.derive("6"));
    }

    @Test
    public void deriveSimpleAdditionTestJustNumber() {
        assertEquals("Should return 0", "0", cM.derive("6 + 6"));
    }

    @Test
    public void deriveTestSimpleTermWithWhiteSpaces() {
        assertEquals("Should return 4x", "4x", cM.derive("2 x ^ 2"));
    }

    @Test
    public void deriveTestSimpleTermWithOutWhiteSpaces() {
        assertEquals("Should return 4x", "4x", cM.derive("2x^2"));
    }

    @Test
    public void deriveTestSimpleTermWithWhiteSpacesAndNegativeExponent() {
        assertEquals("Should return 4x", "-4x^-3", cM.derive("2 x ^ - 2"));
    }

    @Test
    public void deriveTestSimpleTermWithOutWhiteSpacesAndNegativeExponent() {
        assertEquals("Should return 4x", "-4x^-3", cM.derive("2x^-2"));
    }

    @Test
    public void deriveTestComplexTermWithWhiteSpacesAndNegativeExponent() {
        assertEquals("Should return 4x", "12x+3-4x^-3", cM.derive("2 x ^ - 2 + 6 x ^ 2 - 3 x + 3"));
    }

    @Test
    public void deriveTestComplexTermWithOutWhiteSpacesAndNegativeExponent() {
        assertEquals("Should return 4x", "12x+3-4x^-3", cM.derive("2x^-2+6x^2-3x+3"));
    }

    @Test
    public void deriveTestComplexTermWithWhiteSpacesAndNegativeExponentAndNegativeCoefficient() {
        assertEquals("Should return 4x", "12x+3+4x^-3", cM.derive("- 2 x ^ - 2 + 6 x ^ 2 - 3 x + 3"));
    }

    @Test
    public void deriveTestComplexTermWithOutWhiteSpacesAndNegativeExponentAndNegativeCoefficient() {
        assertEquals("Should return 4x", "12x+3+4x^-3", cM.derive("-2x^-2+6x^2-3x+3"));
    }
}