/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @desription JUnit tests for morse code tree
 */
package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import java.util.StringTokenizer;

import static org.junit.Assert.*;

public class MorseCodeTreeTest
{
  private static MorseCodeTree<String> theTree = null;
  private static final String[] MORSEALPHABET = {"*-", "-***", "-*-*", "-**", "*", "**-*", "--*", "****", "**",
                                                 "*---", "-*-", "*-**", "--", "-*", "---", "*--*", "--*-", "*-*",
                                                 "***", "-", "**-", "***-", "*--", "-**-", "-*--", "--**"};
  private static final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
                                          "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

  private static final String JULIANBEAULIEU = "JULIANBEAULIEU";
  private static final String NERYCHAPETONLAMAS = "NERYCHAPETONLAMAS";
  private static final String HELLOWORLD = "HELLOWORLD";
  private static final String RICKROLL = "NEVERGONNAGIVEYOUUPDVERGONNALETYOUDOWNNEVERGONNAROLLAROUNDANDDESERTYOU";

  private static final String JULIANBEAULIEUMORSE = "*--- **- *-** ** *- -* -*** * *- **- *-** ** * **-";
  private static final String NERYCHAPETONLAMASMORSE = "-* * *-* -*-- -*-* **** *- *--* * - --- -* *-** *- -- *- ***";
  private static final String HELLOWORLDMORSE = "**** * *-** *-** --- *-- --- *-* *-** -**";
  private static final String RICKROLLMORSE = "-* * ***- * *-* --* --- -* -* *- --* ** ***- * -*-- --- **- **- *--* -*" +
                                                    "* ***- * *-* --* --- -* -* *- *-** * - -*-- --- **- -** --- *-- -* -* * " +
                                                        "***- * *-* --* --- -* -* *- *-* --- *-** *-** *- *-* --- **- -* -** *- -* " +
                                                              "-** -** * *** * *-* - -*-- --- **-";

  private static StringTokenizer julianTokenizer = null;
  private static StringTokenizer neryTokenizer = null;
  private static StringTokenizer halloWorldTokenizer = null;
  private static StringTokenizer rickRollTokenizer = null;

  @Before
  public void setUp()
  {
    theTree = new MorseCodeTree<String>();

    julianTokenizer = new StringTokenizer(JULIANBEAULIEUMORSE, " ");
    neryTokenizer = new StringTokenizer(NERYCHAPETONLAMASMORSE, " ");
    halloWorldTokenizer = new StringTokenizer(HELLOWORLDMORSE, " ");
    rickRollTokenizer = new StringTokenizer(RICKROLLMORSE, " ");
  }

  //Error Testing

  @Test
  public void testForUnsupportedLetter()
  {
    assertTrue("This should return Error", theTree.translate("A").equals("Error"));
  }

  @Test
  public void testForUnsupportedNumber()
  {
    assertTrue("This should return Error", theTree.translate("1").equals("Error"));
  }

  @Test
  public void testForUnsupportedCharacter()
  {
    assertTrue("This should return Error", theTree.translate("#").equals("Error"));
  }

  @Test
  public void testForNoFile()
  {
    String errorMessage = "An Error has been encountered while trying to open the file." +
                                                                    "Please make sure you entered the correct file name";

    assertTrue("This should return: \n" + errorMessage, theTree.translateMorseCodeFromFile("A").equals(errorMessage));
  }

  @Test
  public void testForInvalidCharacterInFile()
  {
    String errorMessage = "There has been a problem translating the morse code.\n" +
                                                                    "Please make sure code is legal and try again.";;

    assertTrue("This should return: \n" + errorMessage, theTree.translateMorseCodeFromFile("errorMorse.txt").equals(errorMessage));
  }

  //Testing the alphabet

  @Test
  public void testAlphabet()
  {
    for(int counter = 0; counter < 26; counter++)
    {
      assertTrue("The error occurred at letter #" + (counter + 1) + " Which is letter: " + ALPHABET[counter] + " and morse: " + MORSEALPHABET[counter],
                                                                        ALPHABET[counter].equals(theTree.translate(MORSEALPHABET[counter])));
    }
  }

  @Test
  public void testNameOfProgrammer()
  {
    StringBuilder string = new StringBuilder();

    while(julianTokenizer.hasMoreTokens())
    {
      string.append(theTree.translate(julianTokenizer.nextToken()));
    }

    assertTrue("This should translate morse code to " + JULIANBEAULIEU, JULIANBEAULIEU.equals(string.toString()));
  }

  @Test
  public void testNameOfProfessor()
  {
    StringBuilder string = new StringBuilder();

    while(neryTokenizer.hasMoreTokens())
    {
      string.append(theTree.translate(neryTokenizer.nextToken()));
    }

    assertTrue("This should translate morse code to " + NERYCHAPETONLAMAS, NERYCHAPETONLAMAS.equals(string.toString()));
  }

  @Test
  public void testHelloWorld()
  {
    StringBuilder stringB = new StringBuilder();

    while(halloWorldTokenizer.hasMoreTokens())
    {
      stringB.append(theTree.translate(halloWorldTokenizer.nextToken()));
    }

    assertTrue("This should translate morse code to " + HELLOWORLD, HELLOWORLD.equals(stringB.toString()));
  }

  @Test
  public void testRickRoll()
  {
    StringBuilder stringB = new StringBuilder();

    while(rickRollTokenizer.hasMoreTokens())
    {
      stringB.append(theTree.translate(rickRollTokenizer.nextToken()));
    }

    assertTrue("This should translate morse code to " + RICKROLL, RICKROLL.equals(stringB.toString()));
  }
}
