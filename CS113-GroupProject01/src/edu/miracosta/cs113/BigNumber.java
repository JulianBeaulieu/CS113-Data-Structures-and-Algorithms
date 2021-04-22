package edu.miracosta.cs113;

public interface BigNumber {

    /*TODO:
     * - make sure to document interface methods below!!!
     * - these are specific to Sprint 2, so for Sprint 1 you can simply have the methods throw an UnsupportedOperationException
     * - do not forget to consult your UML for each sprint so you know ALL methods to build (interface
     * does not contain constructors and toString methods! don't forget!!)
     */

    /**
     * add an int value to the BigInteger value
     * @param n
     *             int value to be added to BigInteger
     */
    public void add(int n);

    /**
     * to convert a String value to a BigInteger value and adds it to the BigInteger value
     * @param n
     *             String value of a BigInteger value to be added to BigInteger
     */
    public void add(String n);

    /**
     * subtract an int value to the BigInteger value
     * @param n
     *             int value to be subtracted from BigInteger
     */
    public void subtract(int n);

    /**
     * to convert a String value to a BigInteger value and subtracts it to the BigInteger value
     * @param n
     *             String value of a BigInteger value to be subtracted from BigInteger
     */
    public void subtract(String n);
}
