package edu.miracosta.cs113;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Translate
{
    public static void translateTextToMorseFile(String inputFilename, String outputFilename)
    {
        MorseCodeTree<String> theTree = new MorseCodeTree<String>();

        try
        {
            Scanner fileIn = new Scanner(new FileInputStream(inputFilename));
            PrintWriter fileOut = new PrintWriter(outputFilename);

            while(fileIn.hasNextLine())
            {

                String nextLine = fileIn.nextLine();
                //Line
                StringTokenizer sT = new StringTokenizer(nextLine, " ");
                StringBuilder sB = new StringBuilder();

                //Line
                while(sT.hasMoreTokens())
                {
                    //Word
                    char[] charArr = sT.nextToken().toUpperCase().toCharArray();

                    //Letter in word
                    for(char c : charArr)
                    {
                        sB.append(theTree.encode("" + c) + " ");
                    }

                    sB.append(" ");
                }

                fileOut.println(sB.toString());
            }

            fileIn.close();
            fileOut.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
