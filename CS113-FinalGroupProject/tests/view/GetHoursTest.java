/**
 * @Author Julian Beaulieu
 * @git julianbeaulieu
 * @filename BlackBoxTest.java
 * @description JUnit test class for TextFileGenerator Object
 * @info Since the object gets the current opening hours from the library api, there is no way to really test if it returns the correct data
 *        Since the hours change every semester and daily
 */
package view;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class GetHoursTest
{
  @Test
  public void getOceansideHours()
  {
    assertTrue(view.GetHours.getMiraCostaOpeningHours() != "");
  }

  @Test
  public void getSanElijoHours()
  {
    assertTrue(view.GetHours.getSanElijoOpeningHours() != "");
  }

  @Test
  public void getCLCHours()
  {
    assertTrue(view.GetHours.getCLCOpeningHours() != "");
  }
}