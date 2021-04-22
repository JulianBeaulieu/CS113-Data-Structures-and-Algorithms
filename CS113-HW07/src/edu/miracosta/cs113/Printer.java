/**
 * @author Julian Beaulieu
 * @verson 4.0
 * @see Handels the print jobs
 */
package edu.miracosta.cs113;

import java.util.LinkedList;
import java.util.Queue;

public class Printer
{
  private int printJobNumber;
  private Queue<PrintJob> printerQueue;
  private int printerNumber;

  /**
   * default constructor
   */
  public Printer()
  {
    this.printJobNumber = 0;
    this.printerQueue = new LinkedList<PrintJob>();
    this.printerNumber = 0;
  }

  /**
   * @param numberOfThisPrinter which tells this printer which number it is
   */
  public Printer(int numberOfThisPrinter)
  {
    this.printJobNumber = 0;
    this.printerQueue = new LinkedList<PrintJob>();
    this.printerNumber = numberOfThisPrinter;
  }

  /**
  * Copy constructor
  *
  * @see String#Printer(Printer other)
  *
  * @param other which is of the Printer class
  *
  */
  public Printer(Printer other)
  {
    if(other == null)
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }
    this.printJobNumber = other.printJobNumber;
    this.printerQueue = other.printerQueue;
    this.printerNumber = other.printerNumber;
  }

  /*###################################################
   *################# Mutators ########################
   *###################################################
   */

  /**
   * @param printJobNumber
   */
  public void setPrintJobNumber(int printJobNumber)
  {
    this.printJobNumber = printJobNumber;
  }

  /**
   * @param printerQueue
   */
  public void setPrinterQueue(Queue<PrintJob> printerQueue)
  {
    this.printerQueue = printerQueue;
  }

  /**
   * @param printerNumber
   */
  public void setPrinterNumber(int printerNumber)
  {
    this.printerNumber = printerNumber;
  }

  /*###################################################
   *################# Accessors #######################
   *###################################################
   */

  /**
   * @return the current number of printJobs
   */
  public int getPrintJobNumber()
  {
    return printJobNumber;
  }

  /**
   * @return the refference to the printerQueue that holds the PrintJobs
   */
  public Queue<PrintJob> getPrinterQueue()
  {
    return printerQueue;
  }

  /**
   * @return gets the number of this printer
   */
  public int getPrinterNumber()
  {
    return printerNumber;
  }

    /*###################################################
   *############### Other needed Methods ##############
   *###################################################
   */

  /**
   * @return true if there is another printjob
   */
  public boolean hasAnotherPrintJob()
  {
    if((0 == printJobNumber) || (printerQueue.peek() == null))
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  /**
   * @param newPrintJob
   * @return the passed in PrintJob object
   */
  public PrintJob addPrintJob(PrintJob newPrintJob)
  {
    printerQueue.offer(newPrintJob);
    printJobNumber++;
    return newPrintJob;
  }

  /**
   * @param currentTime
   * @return a PrintJob object if that PrintJob is completed
   */
  public PrintJob print(int currentTime)
  {
    //in case the other printers have more printjobs
    if(!hasAnotherPrintJob())
    {
        return null;
    }

    //saves a reference to the current PrintJob in the printerQueue
    PrintJob temp = printerQueue.element();

    //setStartTimeOfPrintJob throws and exception if the currentTime is not legal
    try
    {
      //will only let the start time be set once, thats why no if
      temp.setStartTimeOfPrintJob(currentTime);
    }
    catch(InvalidValueException e)
    {
      return null; //something went wrong
    }

    //if the PrintJob at the front of the Queue is done it will return it else it returns null
    if(temp.oneMinutHasPassed() <= 0)
    {
      return printerQueue.poll();
    }
    else
    {
        return null;
    }
  }

  /*###################################################
   *################# Default Methods #################
   *###################################################
   */

  /**
   * @param o other object which will be compared
   * @return true or false depending if this object equals the other one
   */
  public boolean equals(Object o)
  {
    if (this == o)
    {
        return true;
    }

    if (o == null || getClass() != o.getClass())
    {
        return false;
    }

    Printer printer = (Printer) o;

    return printJobNumber == printer.printJobNumber &&
            this.printerNumber == printer.printerNumber &&
            this.printerQueue.equals(printer.getPrinterQueue());
  }

  /**
   * @return string representation of this object
   */
  @Override
  public String toString()
  {
    return "Printer{" +
            "printJobNumber=" + printJobNumber +
            ", printerQueue=" + printerQueue +
            ", printerNumber=" + printerNumber +
            '}';
  }

  /**
   * @return this object
   */
  public Printer clone()
  {
    return new Printer(this);
  }
}
