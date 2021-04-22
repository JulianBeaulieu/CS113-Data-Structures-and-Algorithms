/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @filename BinaryTree
 * @HW 11
 * @Professor Nery Chapeton-Lamas
 */
package edu.miracosta.cs113;

public interface SearchTree<E>
{
  public boolean add(E item);
  public boolean contains(E item);
  public E find(E target);
  public E delete(E target);
  public boolean remove(E target);
}
