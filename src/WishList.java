import java.util.Collection;

interface Strategy 
{
	Item execute(WishList wish);
}

class StrategyA implements Strategy 
{
	public Item execute (WishList wish)
	{
		if (wish.nr_elemente() > 1)
			wish.sortPrice();
		return wish.head.item;
	}	
}

class StrategyB implements Strategy 
{
	public Item execute (WishList wish) 
	{
		if (wish.nr_elemente() > 1)
			wish.sortName();
		return wish.head.item;
	}	
}

class StrategyC implements Strategy 
{
	public Item execute (WishList wish) 
	{
		if (wish.nr_elemente() > 1)
			return wish.tail.item;
		return wish.head.item;
	}	
}

public class WishList extends ItemList 
{
	Strategy s;
	String strateg;
	
	public WishList(String strateg)
	{
		this.strateg = strateg;
	}
	
	public Item executeStrategy (ShoppingCart cart) 
	{
		if (strateg.equals("A")) 
		{
			s = new StrategyA();
			Item item = s.execute(this);
			remove(item);
			cart.add(item);
			return item;
		}
		if (strateg.equals("B")) 
		{
			s = new StrategyB();
			Item item = s.execute(this);
			remove(item);
			cart.add(item);
			return item;
		}
		if (strateg.equals("C")) 
		{
			s = new StrategyC();
			Item item = s.execute(this);
			remove(item);
			cart.add(item);
			return item;
		}
		return null;
	}
	
	public boolean add(Item element) 
	{
		Item nou = new Item(element.getName(), element.getID(), element.getPret());
		Node<Item> new_node = new Node<>(nou, tail, null);
		if (tail != null) 
			tail.next = new_node;
		tail = new_node;
		if (head == null)
		{
			head = new_node;
			tail = new_node;
			head.index = 0;
			return true;
		}
		iterate();
		return true;
	}
	
	void sortName () 
	{
		nameComp nume = new nameComp();
		Node<Item> temp = head;
		Node<Item> temp2 = head;
		Item it;
		while(temp.next != null) 
		{
			temp2 = temp.next;
			while(temp2 != null) 
			{
				if(nume.compare(temp, temp2) > 0) 
				{
					it = (Item)temp.item;
					temp.item = temp2.item;
					temp2.item = it;	
				}
				temp2 = temp2.next;
			}
			temp = temp.next;
		}
		iterate();
	}
	
	void sortPrice () 
	{
		priceComp price = new priceComp();
		Node<Item> temp = head;
		Node<Item> temp2 = head;
		Item it;
		while(temp.next != null)
		{
			temp2 = temp.next;
			while(temp2 != null) 
			{
				if(price.compare(temp, temp2) > 0) 
				{
					it = (Item)temp.item;
					temp.item = temp2.item;
					temp2.item = it;	
				}
				temp2 = temp2.next;
			}
			temp = temp.next;
		}
		iterate();
	}
	
	public boolean addAll (Collection<? extends Item> c) 
	{
		boolean modified = false;
		for (Item item : c)
			if (add(item))
				modified = true;
		return modified;
	}
	
	public Item remove (int index) 
	{
		int size = nr_elemente();
		Node<Item> temp = head;
		if (index > size - 1)
			return null;
		if (size == 1) 
		{
			head = null;
			tail = null;
		}
		else 
		{
			for (int i = 0; i < index; i++)
				temp = temp.next;
			if (index == 0) 
			{
				temp = head;
				head = head.next;
				temp.prev = null;
				iterate();
			}
			if (index > 0 && index < size - 1) 
			{
				temp.next.prev = temp.prev;
				temp.prev.next = temp.next;
				iterate();
			}
			if (index == size - 1)
			{
				temp = tail;
				tail = tail.prev;
				tail.next = null;
			}
		}
			return (Item)temp.item;
		}
	
	public boolean remove (Item item)
	{
		int index = indexOf(item);
		if (index == -1)
			return false;
		if (remove(index) != null) 
		{
			return true;
		}
		return false;
	}
	
	public boolean removeAll (Collection <? extends Item> collection) 
	{
		boolean modified = false;
		for (Item item : collection)
			if (remove(item))
				modified = true;
		return modified;
	}
}
