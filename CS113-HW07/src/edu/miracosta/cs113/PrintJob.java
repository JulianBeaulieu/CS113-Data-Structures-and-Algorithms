/**
 * @author Julian Beaulieu
 * @version 3.0
 * @see  "This class is used to save a print job.
 *        A print job keeps track of the total pages, pages printed, when print job was started and what number this printjob is"
 */

package edu.miracosta.cs113;

public class PrintJob
{
  //creating variables
  private int totalPages;
  private int pagesLeft;
  private int currentDurationOfPrintJob;
  private int startTimeOfPrintJob;
  private int printJobNumber;
  private boolean startTimeAlreadySet;

  /**
   * default constructor
   */
  public PrintJob()
  {
    //sets the values
    this.totalPages = 1;
    this.pagesLeft = 0;
    this.currentDurationOfPrintJob = 0;
    this.startTimeOfPrintJob = 0;
    this.printJobNumber = 1;
    this.startTimeAlreadySet = false;
  }


  /**this constructor is used by worker class
   * @param totalPages total amount of pages of this print job
   * @param currentDurationOfPrintJob current time this printjob is being printed
   * @param startTimeOfPrintJob saves the time in minutes when printjob was started
   * @param printJobNumber saves the number of the printjob.
   * @throws InvalidValueException if user tries to use numbers which are not valid
   */
  public PrintJob(int totalPages, int currentDurationOfPrintJob, int startTimeOfPrintJob, int printJobNumber) throws InvalidValueException
  {
    //checks if values entered into constructor are lega
    checkIfNotAllowed(totalPages);
    checkIfNotAllowedButZeroIsOkay(currentDurationOfPrintJob);
    checkIfNotAllowedButZeroIsOkay(startTimeOfPrintJob);
    checkIfNotAllowedZeroNotOkayButBiggerThanFiftyIs(printJobNumber);

    //sets the values
    this.totalPages = totalPages;
    this.pagesLeft = totalPages;
    this.currentDurationOfPrintJob = currentDurationOfPrintJob;
    this.startTimeOfPrintJob = startTimeOfPrintJob;
    this.printJobNumber = printJobNumber;
    this.startTimeAlreadySet = false;
  }

  /**
   * @param totalPages total amount of pages of this print job
   * @param pagesLeft this stores number of pages still needed to print
   * @param currentDurationOfPrintJob current time this printjob is being printed
   * @param startTimeOfPrintJob saves the time in minutes when printjob was started
   * @param printJobNumber saves the number of the printjob.
   * @throws InvalidValueException if user tries to use numbers which are not valid
   */
  public PrintJob(int totalPages, int pagesLeft, int currentDurationOfPrintJob, int startTimeOfPrintJob, int printJobNumber) throws InvalidValueException
  {
    //checks if values entered into constructor are lega
    checkIfNotAllowed(totalPages);
    checkIfNotAllowedButZeroIsOkay(pagesLeft);
    checkIfNotAllowedButZeroIsOkay(currentDurationOfPrintJob);
    checkIfNotAllowedButZeroIsOkay(startTimeOfPrintJob);
    checkIfNotAllowedZeroNotOkayButBiggerThanFiftyIs(printJobNumber);

    //sets the values
    this.totalPages = totalPages;
    this.pagesLeft = pagesLeft;
    this.currentDurationOfPrintJob = currentDurationOfPrintJob;
    this.startTimeOfPrintJob = startTimeOfPrintJob;
    this.printJobNumber = printJobNumber;
    this.startTimeAlreadySet = false;
  }

  /**
  * Copy constructor
  *
  * @see String#PrintJob(PrintJob other)
  *
  * @param other which is of the PrintJob class
  *
  */
  public PrintJob(PrintJob other)
  {
    if(other == null)
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }
    this.totalPages = other.totalPages;
    this.pagesLeft = other.pagesLeft;
    this.currentDurationOfPrintJob = other.currentDurationOfPrintJob;
    this.startTimeOfPrintJob = other.startTimeOfPrintJob;
    this.printJobNumber = other.printJobNumber;
    this.startTimeAlreadySet = other.startTimeAlreadySet;
  }

  /*###################################################
   *################# Mutators ########################
   *###################################################
   */

  /**
   * @param totalPages
   */
  public void setTotalPages(int totalPages) throws InvalidValueException
  {
    checkIfNotAllowed(totalPages);
    this.totalPages = totalPages;
  }

  /**
   * @param pagesLeft
   */
  public void setPagesLeft(int pagesLeft) throws InvalidValueException
  {
    checkIfNotAllowedButZeroIsOkay(pagesLeft);
    this.pagesLeft = pagesLeft;
  }

  /**
   * @param currentDurationOfPrintJob
   */
  public void setCurrentDurationOfPrintJob(int currentDurationOfPrintJob) throws InvalidValueException
  {
    checkIfNotAllowedButZeroIsOkay(currentDurationOfPrintJob);
    this.currentDurationOfPrintJob = currentDurationOfPrintJob;
  }

  /**
   * @param startTimeOfPrintJob
   */
  public void setStartTimeOfPrintJob(int startTimeOfPrintJob) throws InvalidValueException
  {
    //you can only set the start time once
    if(!startTimeAlreadySet)
    {
      checkIfNotAllowedButZeroIsOkay(startTimeOfPrintJob);
      this.startTimeOfPrintJob = startTimeOfPrintJob;
      startTimeAlreadySet = true;
    }
  }

  /**
   * @param printJobNumber
   */
  public void setPrintJobNumber(int printJobNumber) throws InvalidValueException
  {
    checkIfNotAllowedZeroNotOkayButBiggerThanFiftyIs(printJobNumber);
    this.printJobNumber = printJobNumber;
  }

  /**
   * @param startTimeAlreadySet
   */
  public void setStartTimeAlreadySet(boolean startTimeAlreadySet)
  {
    this.startTimeAlreadySet = startTimeAlreadySet;
  }

  /*###################################################
   *################# Accessors #######################
   *###################################################
   */

  /**
   * @return number of Pages this print job is big
   */
  public int getTotalPages()
  {
    return this.totalPages;
  }

  /**
   * @return number of pages left to print in printJob
   */
  public int getPagesLeft()
  {
    return this.pagesLeft;
  }

  /**
   * @return current time this printJob has been printing
   */
  public int getCurrentDurationOfPrintJob()
  {
    return this.currentDurationOfPrintJob;
  }

  /**
   * @return the start time of the printJob
   */
  public int getStartTimeOfPrintJob()
  {
    return this.startTimeOfPrintJob;
  }

  /**
   * @return the number of this printJob
   */
  public int getPrintJobNumber()
  {
    return this.printJobNumber;
  }

  /**
   * @return true or false depending if time was set
   */
  public boolean isStartTimeAlreadySet()
  {
    return startTimeAlreadySet;
  }

  /*###################################################
   *############### Other needed Methods ##############
   *###################################################
   */

  /** when method is called, 10 pages were printed, this method makes sure, that it subtracts 10 pages
   * @return will update the printjob and return the number of pages left to print
   */
   public int oneMinutHasPassed()
   {
     currentDurationOfPrintJob++;
     pagesLeft -= 10;
     return pagesLeft;
   }

  /**
   * @return the time the printJob was completed
   */
   public int whatTimeWasTheJobCompleted()
   {
     return startTimeOfPrintJob + currentDurationOfPrintJob;
   }

  /*###################################################
   *################# Helper Methods ##################
   *###################################################
   */


  /**
   * Checks if the parameter is <= 0 or null, if yes will throw exception
   * @param i integer which is passed in
   * @throws InvalidValueException
   */
  private void checkIfNotAllowed(Integer i) throws InvalidValueException
  {
    if((i <= 0) || (i > 50))
    {
        throw new InvalidValueException();
    }
  }

  /**
   * Checks if the parameter is <= 0 && > 50 or null, if yes will throw exception
   * @param i integer which is passed in
   * @throws InvalidValueException
   */
  private void checkIfNotAllowedZeroNotOkayButBiggerThanFiftyIs(Integer i) throws InvalidValueException
  {
    if(i <= 0)
    {
        throw new InvalidValueException();
    }
  }

  /**
   * Checks if the parameter is < 0 or null, if yes will throw exception
   * @param i integer which is passed in
   * @throws InvalidValueException
   */
  private void checkIfNotAllowedButZeroIsOkay(Integer i) throws InvalidValueException
  {
    if(i < 0)
    {
        throw new InvalidValueException();
    }
  }


  /*###################################################
   *################# Default Methods #################
   *###################################################
   */

  /**
   * @param o object which will be compared to
   * @return true or false depending if the objects are equal
   */
  @Override
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

    PrintJob printJob = (PrintJob) o;

    return totalPages == printJob.totalPages &&
            pagesLeft == printJob.pagesLeft &&
            currentDurationOfPrintJob == printJob.currentDurationOfPrintJob &&
            startTimeOfPrintJob == printJob.startTimeOfPrintJob &&
            printJobNumber == printJob.printJobNumber &&
            startTimeAlreadySet == printJob.startTimeAlreadySet;
  }

  /**
   * @return string value of this object
   */
  @Override
  public String toString()
  {
    return "PrintJob"+ printJobNumber +"{" +
            "totalPages = " + totalPages +
            ", pagesLeft = " + pagesLeft +
            ", currentDurationOfPrintJob = " + currentDurationOfPrintJob +
            ", startTimeOfPrintJob = " + startTimeOfPrintJob +
            ", startTimeAlreadySet = " + startTimeAlreadySet +
            "}";
  }

  //clones this object
  public PrintJob clone()
  {
    return new PrintJob(this);
  }
}
