/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 4.0
 * @ProblemStatement JUnit tests for the Class HashtableChain
 */
package edu.miracosta.cs113;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class HashtableChainTest
{
  //Object which will be use to test
  private static HashtableChain<Integer, String> hashTable = null;

  //array of strings which will be used to be stored into the hash table
  private static final String[] rickRoll = {"We're", "no", "strangers", "to", "love",
                        "You", "know", "the", "rules", "and", "so", "do", "I",
                        "A", "full", "commitment's", "what", "I'm", "thinking", "of",
                        "You", "wouldn't", "get", "this", "from", "any", "other", "guy",
                        "I", "just", "wanna", "tell", "you", "how", "I'm", "feeling",
                        "Gotta", "make", "you", "understand",
                        "Never", "gonna", "give", "you", "up",
                        "Never", "gonna", "let", "you", "down",
                        "Never", "gonna", "run", "around", "and", "desert", "you",
                        "Never", "gonna", "make", "you", "cry",
                        "Never", "gonna", "say", "goodbye",
                        "Never", "gonna", "tell", "a", "lie", "and", "hurt", "you"};

  //this is what the hasmap is supposed to look like when filling the hashtable with the previous array
  private static final String rickRollInHashTable = "[0] -> null\n" + "[1] -> null\n" +
                                                    "[2] -> up \n" + "[3] -> null\n" +
                                                    "[4] -> wouldn't \n" + "[5] -> You \n" +
                                                    "[6] -> null\n" + "[7] -> null\n" +
                                                    "[8] -> of \n" + "[9] -> love \n" +
                                                    "[10] -> null\n" + "[11] -> null\n" +
                                                    "[12] -> Never \n" + "[13] -> null\n" +
                                                    "[14] -> guy \n" + "[15] -> rules \n" +
                                                    "[16] -> cry \n" + "[17] -> null\n" +
                                                    "[18] -> say -> get \n" + "[19] -> null\n" +
                                                    "[20] -> around -> give \n" + "[21] -> null\n" +
                                                    "[22] -> null\n" + "[23] -> null\n" +
                                                    "[24] -> null\n" + "[25] -> null\n" +
                                                    "[26] -> hurt -> We're \n" + "[27] -> goodbye \n" +
                                                    "[28] -> null\n" + "[29] -> null\n" +
                                                    "[30] -> null\n" + "[31] -> null\n" +
                                                    "[32] -> this \n" + "[33] -> null\n" +
                                                    "[34] -> null\n" + "[35] -> null\n" +
                                                    "[36] -> null\n" + "[37] -> null\n" +
                                                    "[38] -> commitment's \n" + "[39] -> null\n" +
                                                    "[40] -> so \n" + "[41] -> thinking \n" +
                                                    "[42] -> wanna -> full \n" + "[43] -> null\n" +
                                                    "[44] -> null\n" + "[45] -> null\n" +
                                                    "[46] -> null\n" + "[47] -> make \n" +
                                                    "[48] -> null\n" + "[49] -> just -> strangers \n" +
                                                    "[50] -> null\n" + "[51] -> gonna \n" +
                                                    "[52] -> null\n" + "[53] -> you \n" +
                                                    "[54] -> null\n" + "[55] -> null\n" +
                                                    "[56] -> null\n" + "[57] -> null\n" +
                                                    "[58] -> null\n" + "[59] -> null\n" +
                                                    "[60] -> null\n" + "[61] -> null\n" +
                                                    "[62] -> null\n" + "[63] -> tell \n" +
                                                    "[64] -> I'm \n" + "[65] -> A -> the -> know \n" +
                                                    "[66] -> null\n" + "[67] -> null\n" +
                                                    "[68] -> null\n" + "[69] -> null\n" +
                                                    "[70] -> run -> and \n" + "[71] -> to \n" +
                                                    "[72] -> null\n" + "[73] -> Gotta -> I \n" +
                                                    "[74] -> null\n" + "[75] -> null\n" +
                                                    "[76] -> let -> what \n" + "[77] -> null\n" +
                                                    "[78] -> null\n" + "[79] -> null\n" +
                                                    "[80] -> how -> do \n" + "[81] -> down -> other -> from \n" +
                                                    "[82] -> null\n" + "[83] -> null\n" +
                                                    "[84] -> lie \n" + "[85] -> null\n" +
                                                    "[86] -> null\n" + "[87] -> no \n" +
                                                    "[88] -> null\n" + "[89] -> null\n" +
                                                    "[90] -> null\n" + "[91] -> understand -> any \n" +
                                                    "[92] -> feeling \n" + "[93] -> null\n" +
                                                    "[94] -> null\n" + "[95] -> null\n" +
                                                    "[96] -> null\n" + "[97] -> a \n" +
                                                    "[98] -> desert \n" + "[99] -> null\n" +
                                                    "[100] -> null\n";

  private static final String[] RICKROLLINHASHTABLE = {"up", "wouldn't", "You", "of", "love", "Never", "guy", "rules",
                                                       "cry", "say", "get", "around", "give", "hurt", "We're", "goodbye",
                                                       "this", "commitment's", "so", "thinking", "wanna", "full", "make",
                                                       "just", "strangers", "gonna", "you", "tell", "I'm", "A", "the",
                                                       "know", "run", "and", "to", "Gotta", "I", "let", "what", "how",
                                                       "do", "down", "other", "from", "lie", "no", "understand", "any",
                                                       "feeling", "a", "desert"};

  /**
   * also tests rehash method, since it forces the hashtable
   * to rehash due to the size of values which are being saved
   */
  @Before
  public void setup()
  {
    hashTable = new HashtableChain<Integer, String>();

    for(int i = 0; i < 1000; i++)
    {
      hashTable.put(new Integer((int) (Math.random() * 1000)), new String("" + i));
    }
  }

  public void fillFromArray(String[] array)
  {
    hashTable.clear();

    for(int i = 0; i < array.length; i++)
    {
      hashTable.put(array[i].hashCode(), array[i]);
    }
  }

  @Test
  public void getTest()
  {
    fillFromArray(rickRoll);
    assertEquals(hashTable.get(rickRoll[2].hashCode()), "strangers");
  }

  @Test
  public void putTest()
  {
    fillFromArray(rickRoll);
    hashTable.put(10, "Nery");
    assertEquals(hashTable.get(10), "Nery");
  }

  @Test
  public void sizeTestEmpty()
  {
    hashTable.clear();
    assertTrue(hashTable.size() == 0);
  }

  @Test
  public void sizeTestFull()
  {
    //the size should be 51 because the hashtable should not add duplicates
    fillFromArray(rickRoll);
    assertTrue(hashTable.size() == 51);
  }

  @Test
  public void isEmptyTestTrue()
  {
    hashTable = new HashtableChain<Integer, String>();
    assertTrue(hashTable.isEmpty());
  }

  @Test
  public void isEmptyTestFalse()
  {
    assertFalse(hashTable.isEmpty());
  }

  @Test
  public void clearTest()
  {
    hashTable.clear();

    assertTrue(hashTable.isEmpty());
  }

  @Test
  public void containsKeyTestAssertTrue()
  {
    fillFromArray(rickRoll);
    assertTrue(hashTable.containsKey(rickRoll[0].hashCode()));
  }

  @Test
  public void containsKeyTestAssertFalse()
  {
    fillFromArray(rickRoll);
    assertFalse(hashTable.containsKey(1));
  }

  @Test
  public void containsValueTestAssertTrue()
  {
    fillFromArray(rickRoll);
    assertTrue(hashTable.containsValue("commitment's"));
  }

  @Test
  public void containsValueTestAssertFalse()
  {
    fillFromArray(rickRoll);
    assertFalse(hashTable.containsValue("Julian da Programmer"));
  }

  @Test
  public void toStringTest()
  {
    fillFromArray(rickRoll);

    assertEquals(hashTable.toString(), rickRollInHashTable);
  }

  @Test
  public void removeTestOneItemInChain()
  {
    fillFromArray(rickRoll);
    assertTrue(hashTable.containsValue("commitment's")); //makes sure that the value we are about to remove exists

    hashTable.remove("commitment's".hashCode()); // deletes the value
    assertFalse(hashTable.containsValue("commitment's")); //checks if item was really deleted
  }

  @Test
  public void removeTestMultipleItemsInChain()
  {
    fillFromArray(rickRoll);
    assertTrue(hashTable.containsKey("other".hashCode())); //makes sure that the value we are about to remove exists
    hashTable.remove("other".hashCode()); // deletes the value

    assertFalse(hashTable.containsValue("other".hashCode())); //checks if item was really deleted
  }

  @Test
  public void removeValueTestMultipleItemsInChain()
  {
    fillFromArray(rickRoll);
    assertTrue(hashTable.containsValue("other")); //makes sure that the value we are about to remove exists

    hashTable.removeValue("other");
    assertFalse(hashTable.containsValue("other")); //checks if item was really deleted
  }

  @Test
  public void equalsTrueTest()
  {
    fillFromArray(rickRoll);

    HashtableChain<Integer, String> otherHashTable = new HashtableChain<Integer, String>();

    for(int i = 0; i < rickRoll.length; i++)
    {
      hashTable.put(rickRoll[i].hashCode(), rickRoll[i]);
    }

    assertTrue(hashTable.equals(otherHashTable));
  }

  @Test
  public void equalsFalseTest()
  {
    HashtableChain<Integer, String> otherHashTable = new HashtableChain<Integer, String>();

    for(int i = 0; i < rickRoll.length; i++)
    {
      hashTable.put(rickRoll[i].hashCode(), rickRoll[i]);
    }

    assertFalse(hashTable.equals(otherHashTable));
  }

  /*################## Iterator Tests ##########################################*/

  @Test
  public void iteratorHasNextAndNextTest()
  {
    fillFromArray(rickRoll);

    Iterator itr = hashTable.iterator();

    for(int i = 0; itr.hasNext(); i++)
    {
     assertTrue(itr.next().toString().equals(RICKROLLINHASHTABLE[i]));
    }
  }

  @Test
  public void iteratorRemoveTest()
  {
    fillFromArray(rickRoll);

    Iterator itr = hashTable.iterator();

    //this is needed so the iterator moves to the first entry object
    itr.hasNext();

    Object nextItem = itr.next(); //this should return "up" from the hashtable

    itr.remove(); //this should remove "up" from the hashtable

    //since it removed "up" from the hashtable, the contains method should return false
    assertFalse(hashTable.toString().contains(nextItem.toString()));
  }

  @Test(expected = IllegalStateException.class)
  public void iteratorRemoveFailTest()
  {
    fillFromArray(rickRoll);

    Iterator itr = hashTable.iterator();

    itr.remove();
  }
}
