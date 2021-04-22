package edu.miracosta.cs113;

public interface SearchTree<E>
{
  public boolean add(E item);
  public boolean contains(E item);
  public E find(E target);
  public E delete(E target);
  public boolean remove(E target);
}
