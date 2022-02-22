

public class HashTable<K, V> implements HashTableInterface<K,V> {
  private ChainNode[] table;     // hash table
  private int length = 0;         // number of entries (number of (key,value) associations)

  public HashTable() {
    table = new ChainNode[3];
  }  // end default constructor

  // table operations
  public boolean tableIsEmpty() {
    return length==0;
  }  // end tableIsEmpty

  public int tableLength() {
    return length;
  }  // end tableLength = number of (key,value) associations in the HashTable

  //implement the following 5 methods

  // if key is not already in HashTable inserts association (key,value) and returns true
  // if key is already in the HashTable it does not get re-inserted and returns false
  public boolean tableInsert(K key, V value) 
  {
	  int bucket = hashCode(key) % table.length;
	  boolean searching = true;
	  ChainNode node = table[bucket];
	  ChainNode newNode = new ChainNode(key, value, null);
	  if(length == 0)
	  {
		  table[bucket] = newNode;
		  length++;
		  return true;
	  }
	  else
	  {
		  if(tableRetrieve(key) == null)
		  {
			  if(table[bucket] == null)
			  {
				  table[bucket] = newNode;
			  }
			  else
			  {
				  ChainNode<String, Integer> curr = table[bucket];
				  while(curr.getNext() != null)
				  {
					  curr = curr.getNext();
				  }
				  curr.setNext(newNode);
				  length++;
			  }
			  return true;
		  }
	  }
	  return false;
  }
  

  // if key is in HashTable deletes the key and its association from the HashTable and returns true
  // if key is not in the HashTable returns false
  public boolean tableDelete(K searchKey) 
  {
	  int bucket = hashCode(searchKey) % table.length;
	  ChainNode<String, Integer> node = table[bucket];
	  
	  if(node != null)
	  {
		  if(node.getKey().equals(searchKey))
		  {
			  table[bucket] = node.getNext();
			  length--;
			  return true;
		  }
		  else
		  {
			  while(node.getNext().getKey().compareTo((String) searchKey) != 0)
			  {
				  node = node.getNext();
			  }
			  length--;
			  node.setNext(node.getNext().getNext());
			  return true;
		  }
	  }
	  return false;
  }
	

  //returns the value associated with searchKey in the HashTable or null if the serchKey is not in the HashTable 
  public V tableRetrieve(K searchKey)
  {
	  int bucket = hashCode(searchKey) % table.length;
	  boolean found = false;
	  ChainNode search = table[bucket];
		
	  while(search != null && !found) 
	  {
		  if(search.getKey().equals(searchKey)) 
		  {
			  found = true;
			  return (V) search.getValue();
		  }
		  search = search.getNext();
	  }
	  return null;
	}
	
	// returns the integer hashCode computed using Horner's rule - assumes K is String 
	public int hashCode(K key) 
	{
		String s = (String) key;
		s = s.toUpperCase();
		int result = 0;
		int power = 1;
		int last = s.length() - 1;
		for(int i = 0; i < s.length(); i++) 
		{
			char c = s.charAt(i);
			int rank = c - 'A' + 1;
			power = 1 << last * 5;
			last--;
			int hash = rank * power;
			result += hash;
			//result = result + (hash << (i * 5));
		}
		return result;
	}

	//display all (key,value) associations in the HashTable
	public String toString() 
	{
		String string = "";
		for (int i = 0; i < table.length; i++) 
		{
			ChainNode newNode = table[i];
			while(newNode != null)
			{
				string = string + newNode.toString() + " ";
				newNode = newNode.getNext();
			}
        }
		return string;
	}
}  // end HashTable
