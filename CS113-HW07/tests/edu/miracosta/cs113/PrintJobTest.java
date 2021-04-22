/**
 * @author Julian Beaulieu
 * @version 3.0
 * @see  "JUnit Test class for PrintJob class"
 */

package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrintJobTest {

    private static PrintJob printJob;

    @Before
    public void setUp() throws Exception {
        printJob = new PrintJob();
    }

    private void setUpAfter(boolean callThisMethod) throws Exception {
      if(callThisMethod)
      {
        printJob = new PrintJob(1, 1, 1, 1, 1);
      }
      else
      {
        printJob = new PrintJob();
      }
    }

    private void setUpSpecial(int totalPages, int pagesLeft, int currentDurationOfPrintJob, int startTimeOfPrintJob, int printJobNumber) throws Exception {
        printJob = new PrintJob(totalPages, pagesLeft, currentDurationOfPrintJob, startTimeOfPrintJob, printJobNumber);
    }


    //actual Tests


    @Test
    public void setGetTotalPagesTest() throws InvalidValueException {
      printJob.setTotalPages(30);
      assertEquals("Tests if setter works", 30, printJob.getTotalPages());
    }

    @Test
    public void setGetPagesLeftTest() throws InvalidValueException  {
      printJob.setPagesLeft(20);
      assertEquals("Tests if setter works", 20, printJob.getPagesLeft());
    }

    @Test
    public void setGetCurrentDurationOfPrintJobTest() throws InvalidValueException  {
      printJob.setCurrentDurationOfPrintJob(20);
      assertEquals("Tests if setter works", 20, printJob.getCurrentDurationOfPrintJob());
    }

    @Test
    public void setGetStartTimeOfPrintJobTest() throws InvalidValueException  {
      printJob.setStartTimeOfPrintJob(50);
      assertEquals("Tests if setter works", 50, printJob.getStartTimeOfPrintJob());
    }

    @Test
    public void setGetPrintJobNumberTest() throws InvalidValueException  {
      printJob.setPrintJobNumber(50);
      assertEquals("Tests if setter works", 50, printJob.getPrintJobNumber());
    }

    /**
     * test for Exception throwing
     */

    @Test
    public void setGetTotalPagesExceptionTest(){
        boolean minusOne = false;

        try {
            printJob.setTotalPages(-1);
        } catch (InvalidValueException e) {
          minusOne = true;
        }

        assertTrue("Expected outcome is that exception is thrown", minusOne);
    }

    @Test
    public void setGetPagesLeftExceptionTest(){
        boolean minusOne = false;

        try {
            printJob.setPagesLeft(-1);
        } catch (InvalidValueException e) {
          minusOne = true;
        }

        assertTrue("Expected outcome is that exception is thrown", minusOne);
    }

    @Test
    public void setGetCurrentDurationOfPrintJobExceptionTest(){
        boolean minusOne = false;

        try {
            printJob.setCurrentDurationOfPrintJob(-1);
        } catch (InvalidValueException e) {
          minusOne = true;
        }

        assertTrue("Expected outcome is that exception is thrown", minusOne);
    }

    @Test
    public void setGetStartTimeOfPrintJobExceptionTest(){
        boolean minusOne = false;

        try {
            printJob.setStartTimeOfPrintJob(-1);
        } catch (InvalidValueException e) {
          minusOne = true;
        }

        assertTrue("Expected outcome is that exception is thrown", minusOne);
    }

    @Test
    public void setGetPrintJobNumberExceptionTest(){
        boolean minusOne = false;

        try {
            printJob.setPrintJobNumber(-1);
        } catch (InvalidValueException e) {
          minusOne = true;
        }

        assertTrue("Expected outcome is that exception is thrown", minusOne);
    }

    /**
     * Other tests
     */

    @Test
    public void oneMinutHasPassedTest() throws Exception  {
      setUpSpecial(5, 6, 7, 8, 9);
      assertEquals("Tests if pagesLeft was decremented by 10", -4, printJob.oneMinutHasPassed());
      assertEquals("Tests if currentDurationOfPrintJob was incremented", 8, printJob.getCurrentDurationOfPrintJob());
    }

    @Test
    public void whatTimeWasTheJobCompletedTest() throws Exception  {
      setUpSpecial(5, 6, 7, 8, 9);
      assertEquals("Tests if currentDurationOfPrintJob and startTimeOfPrintJob were added should be 15",
              (printJob.getStartTimeOfPrintJob() + printJob.getCurrentDurationOfPrintJob()), printJob.whatTimeWasTheJobCompleted());
    }

    @Test
    public void equalsAndCloneTestTrue() throws Exception  {
      setUpAfter(true);
      PrintJob temp = printJob.clone();
      assertTrue("Tests if equals and clone work", printJob.equals(temp));
    }

    @Test
    public void equalsAndCloneTestFalse() throws Exception  {
      setUpAfter(true);
      PrintJob temp = printJob.clone();
      setUpAfter(false);

      assertFalse("Tests if equals and clone work", printJob.equals(temp));
    }

    @Test
    public void toStringTest() throws Exception  {
      setUpSpecial(5, 6, 7, 8, 9);
      assertEquals("Tests if toString works",
              "PrintJob9{totalPages = 5, pagesLeft = 6, currentDurationOfPrintJob = 7, startTimeOfPrintJob = 8, startTimeAlreadySet = false}",
                        printJob.toString());
    }
}
