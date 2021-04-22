/**
 * @author Julian Beaulieu
 * @version 3.0
 * @see  "JUnit Test class for Printer class"
 */


package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static org.junit.Assert.*;

public class PrinterTest {

    private static Printer printer;

    //did not let me use the worker class
    private static PrintJob[] doYourJob(int numOfPrintJobs) throws Exception
    {
        Random randomGenerator = new Random();
        PrintJob[] printJobs = new PrintJob[numOfPrintJobs];

        try
        {
            for (int i = 0; i < numOfPrintJobs; i++)
            {
                printJobs[i] = new PrintJob(randomGenerator.nextInt(50) + 1, 0, 0, i + 1);
            }
        }
        catch(InvalidValueException e)
        {
            System.out.println("Error");
        }

        return printJobs;
    }

    @Before
    public void setUp() throws Exception {
      printer = new Printer(1);
    }

    public void setUpAfter(boolean hiNery) throws Exception {
      if(hiNery)
      {
        PrintJob[] tempArray = doYourJob(10);
        for(int i = 0; i < 10; i++)
        {
          printer.addPrintJob(tempArray[i]);
        }
      }
      else
      {
        printer.addPrintJob(new PrintJob(1, 1, 1, 1, 1));
      }
    }

    @Test
    public void setGetPrintJobNumberTest() {
      printer.setPrintJobNumber(500);
      assertEquals("Should return 500", 500, printer.getPrintJobNumber());
    }

    @Test
    public void setGetPrinterQueueTest() throws Exception{
      PrintJob[] tempArray = doYourJob(10);
      Queue<PrintJob> temp = new LinkedList<PrintJob>(Arrays.asList(tempArray)); //I'll be honest the arrays.asList thing was intellij, no clue what this is
      printer.setPrinterQueue(temp);

      assertTrue("should return the same queue", temp.equals(printer.getPrinterQueue()));
    }

    @Test
    public void setGetPrinterNumberTest() {
      printer.setPrinterNumber(1000);
      assertEquals("should return 1000", 1000, printer.getPrinterNumber());
    }

    @Test
    public void hasAnotherPrintJobTest() throws Exception {
      assertFalse("Should return false, since queue empty", printer.hasAnotherPrintJob());
      setUpAfter(false);
      assertTrue("Should return true, since queue has one PrintJob", printer.hasAnotherPrintJob());
    }

    @Test
    public void addPrintJobTest() throws Exception{
      printer.addPrintJob(new PrintJob(1, 1, 1, 1, 1));
      assertTrue("Should return true, since queue has one PrintJob", printer.hasAnotherPrintJob());
    }

    @Test
    public void printTestReturnPrintJob() throws Exception{
      PrintJob temp = new PrintJob(1, 1, 1, 0, 1);
      temp.setStartTimeOfPrintJob(1);
      temp.oneMinutHasPassed();

      setUpAfter(false);

      assertTrue("Should return temp, since printing the printJob is not completed", temp.equals(printer.print(1)));
    }

    @Test
    public void printTestReturnNull() throws Exception {
      printer.addPrintJob(new PrintJob(20, 20, 1, 1, 1));
      assertEquals("Should return null, since printing the printJob is not completed", null, printer.print(2));
    }

    @Test
    public void equalsAndCloneTestTrue() throws Exception {
      setUpAfter(true);
      Printer temp = printer.clone();
      assertTrue("Tests if equals and clone work", printer.equals(temp));
    }

    @Test
    public void equalsAndCloneTestFalse() throws Exception {
      setUpAfter(true);
      Printer temp = printer.clone();
      setUpAfter(false);

      assertFalse("Tests if equals and clone work", printer.equals(temp));
    }

    @Test
    public void toStringTest() throws Exception {
      setUpAfter(false);
      assertEquals("this should be the same",
      "Printer{printJobNumber=1, printerQueue=[PrintJob1{totalPages = 1, " +
      "pagesLeft = 1, currentDurationOfPrintJob = 1, startTimeOfPrintJob = 1, " +
      "startTimeAlreadySet = false}], printerNumber=1}", printer.toString());
    }
}
