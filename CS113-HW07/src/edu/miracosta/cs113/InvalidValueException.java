/**
* @author Julian Beaulieu
* @version 1.0
*/
package edu.miracosta.cs113;

public class InvalidValueException extends Exception
{
  public InvalidValueException()
  {
    super("Invalid Value!");
  }

  public InvalidValueException(String message)
  {
    super(message);
  }
}
