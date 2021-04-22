package edu.miracosta.cs113;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest
{
    @Test
    public void goWorkTest()
    {
        assertEquals("This should return an array of PrintJobs with the size 100", 100, Worker.goWork().length);
    }

    @Test
    public void goWorkFiftyTest()
    {
        assertEquals("This should return an array of PrintJobs with the size 50", 50, Worker.goWork(50).length);
    }

    @Test
    public void goWorkTwentyThreeTest()
    {
        assertEquals("This should return an array of PrintJobs with the size 23", 23, Worker.goWork(23).length);
    }
}