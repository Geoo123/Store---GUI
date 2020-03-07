import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;

public abstract class ItemList 
{
	Node<Item> head;
	Node<Item> tail;
	
	static class Node<Item> 
	{
		Item item;
		int index;
		Node<Item> prev;
		Node<Item> next;
	
		public Node (Item item, Node<Item> prev, Node<Item> next) 
		{
			this.item = item;
			this.prev = prev;
			this.next = next;
		}
	}
	abstract public boolean add (Item element); 
	
	abstract public boolean addAll (Collection<? extends Item> c);
	
	abstract public Item remove (int index); 
	
	abstract public boolean remove (Item item);
	
	abstract public boolean removeAll (Collection <? extends Item> collection);
	
	public ListIterator <Item> listIterator () 
	{
		ListIterator<Item> list;
		list = new ItemIterator();
			return list;
	}
	
	public ListIterator <Item> listIterator(int index)
	{
		ListIterator<Item> list;
		list = new ItemIterator();
		for (int i = 0; i < index; i++)
			list.next();
		return list;
	}
	
	public Double getTotalPrice()
	{
		double price = 0;
		Node<Item> temp = head;
		while (temp != null) 
		{
			price = price + temp.item.getPret();
			temp = temp.next;
		}
		return price;
	}
	
	class ItemIterator<Item> implements ListIterator<Node<Item>> 
	{
		Node<Item> node;
		
		public void add (Node<Item> new_node) 
		{
			
		}
		
		public boolean hasNext() 
		{
			return node.next == null;
		}
		
		public boolean hasPrevious() 
		{
			return node.prev == null;
		}
		
		public Node<Item> next() 
		{
			return node.next;
		}
		
		public Node<Item> previous() 
		{
			return node.prev;
		}
		
		public int nextIndex() 
		{
			return node.next.index;
		}
		
		public int previousIndex() 
		{
			return node.prev.index;
		}
		
		public void remove() 
		{
			
		}
		
		public void set (Node<Item> nod) 
		{
			
		}
	}
	
	public Item getItem (int index) 
	{
		if (index > nr_elemente())
			return null;
		Node<Item> temp = head;
		while (temp != null) 
		{
			if (temp.index == index)
				return temp.item;
			temp = temp.next;
		}
		return null;
	}
	
	public Node<Item> getNode (int index) 
	{
		if (index > nr_elemente())
			return null;
		Node<Item> temp = head;
		while (temp != null) 
		{
			if (temp.index == index)
				return temp;
			temp = temp.next;
		}
		return null;
	}
	
	public int indexOf (Item item)
	{
		Node<Item> temp = head;
		while (temp != null) 
		{
			if (temp.item.getName() == item.getName() && item.getID() == temp.item.getID()) 
				return temp.index;
		temp = temp.next;
		}
		return -1;
	}
	
	public int indexOf (Node<Item> node) 
	{
		Node<Item> temp = head;
		while (temp != null)
		{
			if (temp == node)
				return temp.index;
			temp = temp.next;
		}
		return -1;
	}
	
	public boolean contains (Node<Item> node) 
	{
		Node<Item> temp = head;
		while (temp != null) 
		{
			if (temp == node)
				return true;
			temp = temp.next;
		}
		return false;
	}
	
	public boolean contains (Item item) 
	{
		Node<Item> temp = head;
		while (temp != null) 
		{
			if (temp.item.getName() == item.getName() && item.getID() == temp.item.getID())
				return true;
			temp = temp.next;
		}
		return false;
	}
	
	public boolean isEmpty () 
	{
		return head == null;
	}
	
	public void iterate () 
	{
		int ind = 0;
		Node<Item> temp = head;
		while (temp != null) 
		{
			temp.index = ind;
			ind++;
			temp = temp.next;
		}
	}
	
	public String afis ()
{
		String result = "[";
		if (head != null) 
		{
			Node<Item> temp = head;
			while (temp.next != null) 
			{
				result += temp.item.getName() + ";" + temp.item.getID() + ";" + temp.item.getPret() + ", ";
				temp = temp.next;
			}
			result += temp.item.getName() + ";" + temp.item.getID() + ";" + temp.item.getPret();
		}
		result += "]";
		return result;
	}
	
	public int nr_elemente () 
	{
		int nr = 0;
		Node<Item> temp = head;
		while (temp != null) 
		{
			nr++;
			temp = temp.next;
		}
		return nr;
	}
	
	public String toString ()
	{
		String s = null;
		Node<Item> temp = head;
		while (temp != null) 
		{
			s = temp.item + " ";
			temp = temp.next;
		}
		return s;
	}
	
	class nameComp implements Comparator<Node<Item>> 
	{
		public int compare(Node<Item> i1, Node<Item> i2)
		{
			return i1.item.getName().compareTo(i2.item.getName());
		}
	}

	class priceComp implements Comparator<Node<Item>> 
	{
		public int compare(Node<Item> i1, Node<Item> i2) 
		{ 
			if (i1.item.getPret() > i2.item.getPret())
				return 1;
			if (i1.item.getPret() == i2.item.getPret())
				return 0;
			return -1;
		}
	}
}
