/**
 * @Author Alec Holt
 * @git alecholt
 * @filename PathListTest.java
 * @description JUnit test class for PathList Object
 */

package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PathListTest
{
    //TESTER INSTANCE VARIABLES
    PathList pathlist;


    //TESTER HELPER METHODS
    public void testId(int pos, int expected)
    {
        int id = pathlist.getNodeId(pos - 1);
        assertEquals(id, expected);
    }

    public void testXPos(int pos, int expected)
    {
        int xPos = pathlist.getNodeXPos(pos - 1);
        assertEquals(xPos, expected);
    }

    public void testYPos(int pos, int expected)
    {
        int yPos = pathlist.getNodeYPos(pos - 1);
        assertEquals(yPos, expected);
    }

    //SETUP
    @Before
    public void setup()
    {
        pathlist = new PathList();
    }


    //METHOD TESTS
    //TEST FOR ID
    @Test
    public void IDTest1()
    {
        testId(1, 1);
    }

    @Test
    public void IDTest2()
    {
        testId(23, 23);
    }

    @Test
    public void IDTestOutOfBounds()
    {
        testId(200, -1);
    }

    @Test
    public void IDTestOutOfBounds2()
    {
        testId(-200, -1);
    }

    //TEST FOR XPOS
    @Test
    public void XPosTest1()
    {
        testXPos(1, 257);
    }

    @Test
    public void XPosTest2()
    {
        testXPos(50, 180);
    }

    @Test
    public void XPosTest3()
    {
        testXPos(112, 182);
    }

    @Test
    public void XPosTestOutOfBounds()
    {
        testXPos(113, -1);
    }

    @Test
    public void XPosTestOutOfBounds2()
    {
        testXPos(-1, -1);
    }

    //TEST FOR YPOS
    @Test
    public void YPosTest1()
    {
        testYPos(1, 426);
    }

    @Test
    public void YPosTest2()
    {
        testYPos(50, 241);
    }

    @Test
    public void YPosTest3()
    {
        testYPos(112, 215);
    }

    @Test
    public void YPosTestOutOfBounds()
    {
        testYPos(113, -1);
    }

    @Test
    public void YPosTestOutOfBounds2()
    {
        testYPos(-1, -1);
    }
}
