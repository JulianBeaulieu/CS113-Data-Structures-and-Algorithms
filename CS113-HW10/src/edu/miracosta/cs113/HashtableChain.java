/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 4.0
 * @ProblemStatement Build a fully working Hashtable from the example of the book HashtableChain and implement the SetIteraor
 */
package edu.miracosta.cs113;

import java.util.*;

public class HashtableChain<K, V> implements Map<K, V>
{
  /*########################################################################################################*/
  /*################################# Data Fields ##########################################################*/
  /*########################################################################################################*/
  //The table
  private LinkedList<Map.Entry<K, V>>[] table;

  //capacity
  private static final int CAPACITY = 101;

  //The maximum load factor
  private double LOAD_THRESHOLD = 3.0;

  //number of keys
  private int numKeys;

  /*########################################################################################################*/
  /*################################# Constructors #########################################################*/
  /*########################################################################################################*/

  /**
   * Default Constructor
   */
  public HashtableChain()
  {
    //makes a new array of linked lists
    table = new LinkedList[CAPACITY];

    //sets numKeys to 0
    numKeys = 0;
  }

  /**
   * Copy constructor Constructor
   */
  public HashtableChain(HashtableChain<K, V> other)
  {
    if(other == null || other.getClass() != this.getClass())
    {
      System.out.println("Fatal Error");
      System.exit(0);
    }


    //makes a new array of linked lists
    table = new LinkedList[other.table.length];

    numKeys = other.numKeys;

    for(int i = 0; i < other.table.length; i++)
    {
      if(table[i] != null)
      {
        table[i] = (LinkedList<Map.Entry<K, V>>) other.table[i].clone();
      }
    }
  }

  /*########################################################################################################*/
  /*################################# Methods ##############################################################*/
  /*########################################################################################################*/

  /** Finds either the target key or the first empty slot in the
   *  search chain using linear probing
   *  pre: The table is not full
   * @param key The key of the target object
   * @return The position of the target or the first empty slot if
   *         the target is not in the table
   */
  private int find(Object key)
  {
    //Calculate the starting index.
    return key.hashCode() % table.length;
  }

  /**
   * Returns the value to which the specified key is mapped,
   * or {@code null} if this map contains no mapping for the key.
   * <p>
   * <p>More formally, if this map contains a mapping from a key
   * {@code k} to a value {@code v} such that
   * {@code Objects.equals(key, k)},
   * then this method returns {@code v}; otherwise
   * it returns {@code null}.  (There can be at most one such mapping.)
   * <p>
   * <p>If this map permits null values, then a return value of
   * {@code null} does not <i>necessarily</i> indicate that the map
   * contains no mapping for the key; it's also possible that the map
   * explicitly maps the key to {@code null}.  The {@link #containsKey
   * containsKey} operation may be used to distinguish these two cases.
   *
   * @param key the key whose associated value is to be returned
   * @return the value to which the specified key is mapped, or
   * {@code null} if this map contains no mapping for the key
   * @throws ClassCastException   if the key is of an inappropriate type for
   *                              this map
   *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if the specified key is null and this map
   *                              does not permit null keys
   *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  public V get(Object key)
  {
    int index = key.hashCode() % table.length;

    if(index < 0)
    {
      index += table.length;
    }

    if(table[index] == null)
    {
      return null;
    }

    //search the list at table[index] to find the key.
    for(Map.Entry<K, V> nextItem : table[index])
    {
      if(nextItem.getKey().equals(key))
      {
        return nextItem.getValue();
      }
    }

    //assert: key is not in the table
    return null;
  }

  /**
   * Associates the specified value with the specified key in this map
   * (optional operation).  If the map previously contained a mapping for
   * the key, the old value is replaced by the specified value.  (A map
   * {@code m} is said to contain a mapping for a key {@code k} if and only
   * if {@link #containsKey(Object) m.containsKey(k)} would return
   * {@code true}.)
   *
   * @param key   key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @return the previous value associated with {@code key}, or
   * {@code null} if there was no mapping for {@code key}.
   * (A {@code null} return can also indicate that the map
   * previously associated {@code null} with {@code key},
   * if the implementation supports {@code null} values.)
   * @throws UnsupportedOperationException if the {@code put} operation
   *                                       is not supported by this map
   * @throws ClassCastException            if the class of the specified key or value
   *                                       prevents it from being stored in this map
   * @throws NullPointerException          if the specified key or value is null
   *                                       and this map does not permit null keys or values
   * @throws IllegalArgumentException      if some property of the specified key
   *                                       or value prevents it from being stored in this map
   */
  @Override
  public V put(K key, V value)
  {
    int index = key.hashCode() % table.length;

    if(index < 0)
    {
      index += table.length;
    }

    if(table[index] == null)
    {
      //create a new linked list at table[index].
      table[index] = new LinkedList<Map.Entry<K, V>>();
    }

    //Search the list at table[index] to find the key.
    for(Map.Entry<K, V> nextItem : table[index])
    {
      //If the search is successfull, replace the old value.
      if(nextItem.getKey().equals(key))
      {
        //Replace value for this key.
        V oldVal = nextItem.getValue();
        nextItem.setValue(value);
        return oldVal;
      }
    }

    //assert: key is not in the table, add new item.
    table[index].addFirst(new Entry<K, V>(key, value));
    numKeys++;

    if(numKeys > (LOAD_THRESHOLD * table.length))
    {
      rehash();
    }

    return null;
  }

  /**
   * Returns the number of key-value mappings in this map.  If the
   * map contains more than {@code Integer.MAX_VALUE} elements, returns
   * {@code Integer.MAX_VALUE}.
   *
   * @return the number of key-value mappings in this map
   */
  @Override
  public int size()
  {
    //returns the size of the array of linked lists
    return numKeys;
  }

  /**
   * Returns {@code true} if this map contains no key-value mappings.
   *
   * @return {@code true} if this map contains no key-value mappings
   */
  @Override
  public boolean isEmpty()
  {
    //returns true if num of keys is 0
    return numKeys == 0;
  }

  /**
   * Removes all of the mappings from this map (optional operation).
   * The map will be empty after this call returns.
   *
   * @throws UnsupportedOperationException if the {@code clear} operation
   *                                       is not supported by this map
   */
  @Override
  public void clear()
  {
    //sets num of keys back to 0
    numKeys = 0;

    //sets the reference table to a new array of linked lists
    table = new LinkedList[CAPACITY];
  }

  /**
   * Removes the mapping for a key from this map if it is present
   * (optional operation).   More formally, if this map contains a mapping
   * from key {@code k} to value {@code v} such that
   * {@code Objects.equals(key, k)}, that mapping
   * is removed.  (The map can contain at most one such mapping.)
   * <p>
   * <p>Returns the value to which this map previously associated the key,
   * or {@code null} if the map contained no mapping for the key.
   * <p>
   * <p>If this map permits null values, then a return value of
   * {@code null} does not <i>necessarily</i> indicate that the map
   * contained no mapping for the key; it's also possible that the map
   * explicitly mapped the key to {@code null}.
   * <p>
   * <p>The map will not contain a mapping for the specified key once the
   * call returns.
   *
   * @param key key whose mapping is to be removed from the map
   * @return the previous value associated with {@code key}, or
   * {@code null} if there was no mapping for {@code key}.
   * @throws UnsupportedOperationException if the {@code remove} operation
   *                                       is not supported by this map
   * @throws ClassCastException            if the key is of an inappropriate type for
   *                                       this map
   *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException          if the specified key is null and this
   *                                       map does not permit null keys
   *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public V remove(Object key)
  {
    int index = key.hashCode() % table.length;

    if(table[index] == null || table[index].size() == 0)
    {
      return null;
    }

    //in case the linked list is just one long, removal is instantaneous and fast instead of going into a loop
    if(table[index].size() == 1)
    {
      V oldValue = (V) table[index].getFirst();
      table[index] = null;
      return oldValue;
    }
    else
    {
      //Search the list at table[index] to find the key.
      for(Map.Entry<K, V> nextItem : table[index])
      {
        //If the search is successful, remove the old value.
        if(nextItem.getKey().equals(key))
        {
          // saves the value, then deletes it
          V oldVal = nextItem.getValue();
          table[index].remove(nextItem);
          return oldVal;
        }

        //checks if this was the only object in the linked list, if it was and the linked list was empty it will delete the linked list
        if(table[index].size() == 0)
        {
          table[index] = null;
        }
      }
    }

    return null;
  }

  /**
   * Removes the mapping for a key from this map if it is present
   * (optional operation).   More formally, if this map contains a mapping
   * from key {@code k} to value {@code v} such that
   * {@code Objects.equals(key, k)}, that mapping
   * is removed.  (The map can contain at most one such mapping.)
   * <p>
   * <p>Returns the value to which this map previously associated the key,
   * or {@code null} if the map contained no mapping for the key.
   * <p>
   * <p>If this map permits null values, then a return value of
   * {@code null} does not <i>necessarily</i> indicate that the map
   * contained no mapping for the key; it's also possible that the map
   * explicitly mapped the key to {@code null}.
   * <p>
   * <p>The map will not contain a mapping for the specified key once the
   * call returns.
   *
   * @param key key whose mapping is to be removed from the map
   * @return the previous value associated with {@code key}, or
   * {@code null} if there was no mapping for {@code key}.
   * @throws UnsupportedOperationException if the {@code remove} operation
   *                                       is not supported by this map
   * @throws ClassCastException            if the key is of an inappropriate type for
   *                                       this map
   *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException          if the specified key is null and this
   *                                       map does not permit null keys
   *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  public V removeValue(Object value)
  {
    int index = value.hashCode() % table.length;

    if(table[index] == null || table[index].size() == 0)
    {
      return null;
    }

    //in case the linked list is just one long, removal is instantaneous and fast instead of going into a loop
    if(table[index].size() == 1)
    {
      V oldValue = (V) table[index].getFirst();
      table[index] = null;
      return oldValue;
    }
    else
    {
      //Search the list at table[index] to find the value.
      for(Map.Entry<K, V> nextItem : table[index])
      {
        //If the search is successful, remove the old value.
        if(nextItem.getValue().equals(value))
        {
          // saves the value, then deletes it
          V oldVal = nextItem.getValue();
          table[index].remove(nextItem);
          return oldVal;
        }

        //checks if this was the only object in the linked list, if it was and the linked list was empty it will delete the linked list
        if(table[index].size() == 0)
        {
          table[index] = null;
        }
      }
    }

    return null;
  }

  /** Expand table site when loadFactor exceeds LOAD_THRESHOLD
   * post: The size of the table is double and is an odd integer.
   *       Each nondelete enty from the original table is
   *       reinserted into the expanded table.
   *       The value of numKeys is reset to the number of items
   *       actually inserted; numDeletes is reset to 0.
   */
  private void rehash()
  {
    HashtableChain<K, V> tempHashTable = clone();
    Iterator itr = tempHashTable.iterator();

    //Double capacity of this table
    table = new LinkedList[2 * table.length + 1];

    //Reinsert all item in oldTable into expanded table.
    numKeys = 0;

    for(int i = 0; itr.hasNext(); i++)
    {
      Map.Entry<K, V> temp = (Map.Entry<K, V>) itr.next();

      put(temp.getKey(), temp.getValue());
    }
  }

  /**
   * Returns {@code true} if this map contains a mapping for the specified
   * key.  More formally, returns {@code true} if and only if
   * this map contains a mapping for a key {@code k} such that
   * {@code Objects.equals(key, k)}.  (There can be
   * at most one such mapping.)
   *
   * @param key key whose presence in this map is to be tested
   * @return {@code true} if this map contains a mapping for the specified
   * key
   * @throws ClassCastException   if the key is of an inappropriate type for
   *                              this map
   *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if the specified key is null and this map
   *                              does not permit null keys
   *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public boolean containsKey(Object key)
  {
    //takes key and finds the index
    int index = find(key);

    //if the index is not null it will return true
    if(table[index] != null)
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Returns {@code true} if this map maps one or more keys to the
   * specified value.  More formally, returns {@code true} if and only if
   * this map contains at least one mapping to a value {@code v} such that
   * {@code Objects.equals(value, v)}.  This operation
   * will probably require time linear in the map size for most
   * implementations of the {@code Map} interface.
   *
   * @param value value whose presence in this map is to be tested
   * @return {@code true} if this map maps one or more keys to the
   * specified value
   * @throws ClassCastException   if the value is of an inappropriate type for
   *                              this map
   *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   * @throws NullPointerException if the specified value is null and this
   *                              map does not permit null values
   *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public boolean containsValue(Object value)
  {
    for (int i = 0; i < table.length; i++)
    {
      if(table[i] != null)
      {
        //search the list at table[index] to find the key.
        for(Map.Entry<K, V> nextItem : table[i])
        {
          if(nextItem.getValue() != null && nextItem.getValue().equals(value))
          {
            return true;
          }
        }
      }
    }

    return false;
  }

  /**
   * Returns a {@link Set} view of the keys contained in this map.
   * The set is backed by the map, so changes to the map are
   * reflected in the set, and vice-versa.  If the map is modified
   * while an iteration over the set is in progress (except through
   * the iterator's own {@code remove} operation), the results of
   * the iteration are undefined.  The set supports element removal,
   * which removes the corresponding mapping from the map, via the
   * {@code Iterator.remove}, {@code Set.remove},
   * {@code removeAll}, {@code retainAll}, and {@code clear}
   * operations.  It does not support the {@code add} or {@code addAll}
   * operations.
   *
   * @return a set view of the keys contained in this map
   */
  @Override
  public Set<K> keySet()
  {
    //makes a new set of the size of the hashmap
    Set<K> keySet = new HashSet<K>(size());

    //adds all of the keys to a set
    for (LinkedList<Map.Entry<K,V>> list : table)
    {
      for (Map.Entry<K,V> entry : list)
      {
        if (entry != null)
        {
          keySet.add(entry.getKey());
        }
      }
    }

    //returns that set
    return keySet;
  }

  /**
   * Returns a {@link Set} view of the mappings contained in this map.
   * The set is backed by the map, so changes to the map are
   * reflected in the set, and vice-versa.  If the map is modified
   * while an iteration over the set is in progress (except through
   * the iterator's own {@code remove} operation, or through the
   * {@code setValue} operation on a map entry returned by the
   * iterator) the results of the iteration are undefined.  The set
   * supports element removal, which removes the corresponding
   * mapping from the map, via the {@code Iterator.remove},
   * {@code Set.remove}, {@code removeAll}, {@code retainAll} and
   * {@code clear} operations.  It does not support the
   * {@code add} or {@code addAll} operations.
   *
   * @return a set view of the mappings contained in this map
   */
  @Override
  public Set<Map.Entry<K, V>> entrySet()
  {
    //returns a new entry set
    return new EntrySet();
  }

  /*########################################################################################################*/
  /*################################# Default Methods ######################################################*/
  /*########################################################################################################*/

  /**
   * Returns a String representation of the map.
   */
  public String toString()
  {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < table.length; i++)
    {
      sb.append("[" + i + "] ");

      if(table[i] == null)
      {
        sb.append("-> null");
      }
      else
      {
        //search the list at table[index] to find the key.
        for(Map.Entry<K, V> nextItem : table[i])
        {
          sb.append("-> " + nextItem.toString() + " ");
        }
      }

      sb.append("\n");
    }

    return sb.toString();
  }

  /**
   * Equals method, which compares two hashmaps
   * @param o the other hashmap which will be compared with
   * @return true if both hashmaps are equal, false if they are not
   */
  @Override
  public boolean equals(Object o)
  {
    //checks if it is the same linked list
    if(this == o)
    {
      return true;
    }

    //checks if it is null or not the same class
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }

    //makes typecasts it to a HashtableChain

    HashtableChain<K, V> that = (HashtableChain<K, V>) o;

    //checks if the hastables have the same length and same amount of values
    if(table.length != that.table.length && this.size() != that.size())
    {
      return false;
    }

    //makes two iterators and then loops through the hasmaps and compares the values.
    //if the values are not the same, then it will return false
    Iterator thisIterator = iterator();
    Iterator otherIterator = that.iterator();

    while(thisIterator.hasNext() && otherIterator.hasNext())
    {
      if(!thisIterator.next().equals(otherIterator.next()))
      {
        return false;
      }
    }

    //if it loops through the whole hastable and all values match up, it will return true
    return true;
  }


  /** returns a iterator over the hashmap
   * @return a SetIterator of this hashmap
   */
  public SetIterator iterator()
  {
    return new SetIterator();
  }

  /** Returns a cloned verion of this object
   * @return a copy of this object, not a reference
   */
  @Override
  public HashtableChain<K, V> clone()
  {
    return new HashtableChain<K, V>(this);
  }

  /*########################################################################################################*/
  /*################################# Inner Classes ########################################################*/
  /*########################################################################################################*/

  /**
   * Contains key-value pairs for a hash table
   */
  private static class Entry<K, V> implements Map.Entry<K, V>
  {
    //The key
    private K key;

    //The value
    private V value;

    /** Creates a new Key-value pair
     * @param key The key
     * @param value The value
     */
    public Entry(K key, V value)
    {
      this.key = key;
      this.value = value;
    }

    /** Retrieves the key
     * @return The key
     */
    public K getKey()
    {
      return key;
    }

    /** Retrieves the value.
     * @return The value
     */
    public V getValue()
    {
      return value;
    }

    /** Sets the value
     * @param val The new Value
     * @return The old value
     */
    public V setValue(V val)
    {
      V oldVal = value;
      value = val;
      return oldVal;
    }

    @Override
    public String toString()
    {
      return value.toString();
    }
  }

  /**
   * Inner class to implement the set view
   */
  private class EntrySet extends AbstractSet<Map.Entry<K, V>>
  {
    /**
     * Return the size of the set
     */
    @Override
    public int size()
    {
      return numKeys;
    }

    /**
     * Return an iterator over the set.
     */
    @Override
    public Iterator<Map.Entry<K, V>> iterator()
    {
      return new SetIterator();
    }
  }

  private class SetIterator implements Iterator<Map.Entry<K, V>>
  {
    //current index
    int index = 0;

    //local iterator
    Iterator<Map.Entry<K, V>> localIterator = null;

    /** The last item returned by next() */
    Map.Entry<K,V> lastItemReturned = null;

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * 1st Check if there is a local iterator. if it has a next return true
     * 2nd Loop through the array and check for a not null space
     * 3rd Make a iterator over that linked list and ask if it has a next
     * 4th if that returns null repeat step 2 and 3
     * 4th if there are no next, then return false
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext()
    {
      if(localIterator != null && localIterator.hasNext())
      {
        return true;
      }

      do
      {
        index++;
        if(index >= table.length)
        {
          return false;
        }
      }
      while(table[index] == null);

      localIterator = table[index].iterator();
      return localIterator.hasNext();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Map.Entry<K, V> next()
    {
      if(localIterator.hasNext())
      {
        lastItemReturned = localIterator.next();
        return lastItemReturned;
      }
      else
      {
        return null;
      }

    }

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.
     * <p>
     * The behavior of an iterator is unspecified if the underlying collection
     * is modified while the iteration is in progress in any way other than by
     * calling this method, unless an overriding class has specified a
     * concurrent modification policy.
     * <p>
     * The behavior of an iterator is unspecified if this method is called
     * after a call to the {@link #forEachRemaining forEachRemaining} method.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this iterator
     * @throws IllegalStateException         if the {@code next} method has not
     *                                       yet been called, or the {@code remove} method has already
     *                                       been called after the last call to the {@code next}
     *                                       method
     * @implSpec The default implementation throws an instance of
     * {@link UnsupportedOperationException} and performs no other action.
     */
    @Override
    public void remove()
    {
      if (lastItemReturned == null)
      {
        throw new IllegalStateException();
      }
      else
      {
        localIterator.remove();
        lastItemReturned = null;
      }
    }
  }

  /*########################################################################################################*/
  /*################################# Method Stubs #########################################################*/
  /*########################################################################################################*/

  /**
   * Returns a {@link Collection} view of the values contained in this map.
   * The collection is backed by the map, so changes to the map are
   * reflected in the collection, and vice-versa.  If the map is
   * modified while an iteration over the collection is in progress
   * (except through the iterator's own {@code remove} operation),
   * the results of the iteration are undefined.  The collection
   * supports element removal, which removes the corresponding
   * mapping from the map, via the {@code Iterator.remove},
   * {@code Collection.remove}, {@code removeAll},
   * {@code retainAll} and {@code clear} operations.  It does not
   * support the {@code add} or {@code addAll} operations.
   *
   * @return a collection view of the values contained in this map
   */
  @Override
  public Collection<V> values()
  {
    return null;
  }

  /**
   * Copies all of the mappings from the specified map to this map
   * (optional operation).  The effect of this call is equivalent to that
   * of calling {@link #put(Object, Object) put(k, v)} on this map once
   * for each mapping from key {@code k} to value {@code v} in the
   * specified map.  The behavior of this operation is undefined if the
   * specified map is modified while the operation is in progress.
   *
   * @param m mappings to be stored in this map
   * @throws UnsupportedOperationException if the {@code putAll} operation
   *                                       is not supported by this map
   * @throws ClassCastException            if the class of a key or value in the
   *                                       specified map prevents it from being stored in this map
   * @throws NullPointerException          if the specified map is null, or if
   *                                       this map does not permit null keys or values, and the
   *                                       specified map contains null keys or values
   * @throws IllegalArgumentException      if some property of a key or value in
   *                                       the specified map prevents it from being stored in this map
   */
  @Override
  public void putAll(Map<? extends K, ? extends V> m)
  {

  }
}
