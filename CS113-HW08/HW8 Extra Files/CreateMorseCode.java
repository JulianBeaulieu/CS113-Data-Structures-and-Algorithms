package edu.miracosta.cs113;

public class CreateMorseCode
{
  public static Morse[] array()
  {
    Morse[] morseCode = new Morse[26];

    morseCode[0] = new Morse("E", "*");
    morseCode[1] = new Morse("T", "-");
    morseCode[2] = new Morse("C", "-*-*");
    morseCode[3] = new Morse("D", "-**");
    morseCode[4] = new Morse("A", "*-");
    morseCode[5] = new Morse("F", "**-*");
    morseCode[6] = new Morse("G", "--*");
    morseCode[7] = new Morse("H", "****");
    morseCode[8] = new Morse("I", "**");
    morseCode[9] = new Morse("J", "*---");
    morseCode[10] = new Morse("K", "-*-");
    morseCode[11] = new Morse("L", "*-**");
    morseCode[12] = new Morse("M", "--");
    morseCode[13] = new Morse("N", "-*");
    morseCode[14] = new Morse("O", "---");
    morseCode[15] = new Morse("P", "*--*");
    morseCode[16] = new Morse("Q", "--*-");
    morseCode[17] = new Morse("R", "*-*");
    morseCode[18] = new Morse("S", "***");
    morseCode[19] = new Morse("B", "-***");
    morseCode[20] = new Morse("U", "**-");
    morseCode[21] = new Morse("V", "***-");
    morseCode[22] = new Morse("W", "*--");
    morseCode[23] = new Morse("X", "-**-");
    morseCode[24] = new Morse("Y", "-*--");
    morseCode[25] = new Morse("Z", "--**");

    return morseCode;
  }
}
