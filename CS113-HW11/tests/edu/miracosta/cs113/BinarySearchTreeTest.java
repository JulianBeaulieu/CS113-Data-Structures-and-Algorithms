/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @filename BinarySearchTreeTest
 * @HW 11
 * @Professor Nery Chapeton-Lamas
 */
package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest
{
  private static BinarySearchTree<Integer> tree = null;

  private static final String[] ABC = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

  private static final String CORRECTABCINSERTION = "A\n" + " null\n" + " B\n" + "  null\n" + "  C\n" +
                                                    "   null\n" + "   D\n" + "    null\n" + "    E\n" + "     null\n" +
                                                    "     F\n" + "      null\n" + "      G\n" + "       null\n" + "       H\n" +
                                                    "        null\n" + "        I\n" + "         null\n" + "         J\n" +
                                                    "          null\n" + "          K\n" + "           null\n" + "           null\n";

  private static final String[] HELLOFROMTHEOTHERSIDE = {"Hello", "From", "The", "Other", "Side", "I", "Must", "Have", "Called", "A", "Thousand", "Times",
                                                          "To", "Tell", "You", "I", "am", "Sorry", "For", "Everything", "That", "I", "Have", "Done",
                                                          "But", "when", "I", "call", "you", "never", "seem", "to", "be", "home"};

  private static final String HELLOFROMTHEOTHERSIDEBINARYTREE = "Hello\n" + " From\n" + "  Called\n" + "   A\n" + "    null\n" + "    But\n" + "     null\n" +
                                                                "     null\n" + "   For\n" + "    Everything\n" + "     Done\n" + "      null\n" + "      null\n" +
                                                                "     null\n" + "    null\n" + "  Have\n" + "   null\n" + "   null\n" + " The\n" + "  Other\n" +
                                                                "   I\n" + "    null\n" + "    Must\n" + "     null\n" + "     Nery\n" + "      null\n" + "      null\n" +
                                                                "   Side\n" + "    null\n" + "    Tell\n" + "     Sorry\n" + "      null\n" + "      null\n" + "     That\n" +
                                                                "      null\n" + "      null\n" + "  Thousand\n" + "   null\n" + "   Times\n" + "    null\n" + "    To\n" +
                                                                "     null\n" + "     You\n" + "      null\n" + "      am\n" + "       null\n" + "       when\n" + "        call\n" +
                                                                "         be\n" + "          null\n" + "          null\n" + "         never\n" + "          home\n" + "           null\n" +
                                                                "           null\n" + "          seem\n" + "           null\n" + "           to\n" + "            null\n" + "            null\n" +
                                                                "        you\n" + "         null\n" + "         null\n";

  @Before
  public void setUp()
  {
    tree = new BinarySearchTree<>();
  }

  /**
   * Fills up an array with Strings from a array of Strings
   * @param stringArr A String array containing Strings
   */
  public void loadUpTree(String[] stringArr)
  {
    for(String str : stringArr)
    {
      tree.add(str);
    }
  }

  @Test
  public void simpleAddTest()
  {
    loadUpTree(ABC);
    tree.add("K");
    assertEquals(CORRECTABCINSERTION, tree.toString());
  }

  @Test
  public void complexAddTest()
  {
    loadUpTree(HELLOFROMTHEOTHERSIDE);
    tree.add("Nery");
    assertEquals(HELLOFROMTHEOTHERSIDEBINARYTREE, tree.toString());
  }

  @Test
  public void containsTestTrue()
  {
    loadUpTree(ABC);
    assertTrue(tree.contains("F"));
  }

  @Test
  public void containsTestFail()
  {
    loadUpTree(ABC);
    assertFalse(tree.contains("Z"));
  }

  @Test
  public void findTestTrue()
  {
    loadUpTree(ABC);
    assertEquals("F", tree.find("F"));
  }

  @Test
  public void findTestFail()
  {
    loadUpTree(ABC);
    assertEquals(null, tree.find("Z"));
  }

  @Test
  public void removeTestTrue()
  {
    loadUpTree(ABC);
    assertTrue(tree.remove("F"));
    assertFalse(tree.contains("F"));
  }

  @Test
  public void removeTestFalse()
  {
    loadUpTree(ABC);
    assertFalse(tree.remove("Z"));
  }

  @Test
  public void deleteTestTrue()
  {
    loadUpTree(ABC);
    assertEquals("F", tree.delete("F"));
    assertFalse(tree.contains("F"));
  }

  @Test
  public void deleteTestFalse()
  {
    loadUpTree(ABC);
    assertEquals(null, tree.delete("Z"));
  }
}