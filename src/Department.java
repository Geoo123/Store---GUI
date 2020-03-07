import java.util.Iterator;
import java.util.Vector;

interface Subject
{
	public void addObserver(Customer customer);
	public void removeObserver (Customer customer);
	public void notifyAllObservers(Notification note);
}

public abstract class Department implements Subject
{
	String nume;
	int ID;
	int viz;
	Vector <Item>products = new Vector<Item>();
	Vector <Customer>clients = new Vector<Customer>();
	Vector <Customer>observers = new Vector<Customer>();
	Vector <String>observer = new Vector<>();
	Vector <Count>nr = new Vector<>();
	Vector <Count>nr2 = new Vector<>();
	
	public void enter(Customer client) 
	{
			if (!clients.contains(client))
				clients.add(client);
	}
	
	public void exit (Customer client)
	{
		if (clients.contains(client))
			clients.remove(client);
	}
		
	public Vector<Customer> getCustomers()
	{
		return clients;
	}
	
	public int getId() 
	{
		return ID;
	}
	public String getName() 
	{
		return nume;
	}
	
	public void addItem (Item it) 
	{
		if (!products.contains(it))
			products.add(it);
	}
	
	Vector<Item> getItems()
	{
		return products;
	}
	
	public String toString () 
	{
		return nume + " " + ID;
	}
	
	public void addObserver (Customer c) 
	{
		if (!observers.contains(c))
			observers.add(c);
	}
	
	public void removeObserver (Customer c) 
	{
		if (observers.contains(c))
			observers.remove(c);
	}
	
	public void addObserv (Customer c) 
	{
			if (!observer.contains(c.nume))
			observer.add(c.nume);
	}
	
	public void transformObservertoString() 
	{
		observer.removeAllElements();
		if (observers.size() > 0) {
			Iterator <Customer> c = observers.iterator();
			for (int i = 0; i < observers.size(); i++) 
			{
				Customer cl = c.next();
				addObserv(cl);
			}
		}
	}
	
	public void notifyAllObservers(Notification note)
	{
		for (Customer c : observers)
			c.update(note);
	}
	
	public boolean checkID(int ID)
	{
		boolean var = false;
		for(int i = 0; i < products.size(); i++)
			if(products.get(i).getID() == ID)
				var = true;
		return var;
	}
	
	public void prod_dorite (WishList list) 
	{
			int ok = 0;
			WishList.Node<Item> nou = list.head;
			while (nou != null) 
			{
				ok = 0;
				for (Item it : products) 
				{
					if (it.getID() == nou.item.getID()) 
					{
						for (Count co : nr2) 
						{
							if (co.item.getID() == nou.item.getID()) 
							{
								ok = 1;
							}
						}
						if (ok == 0) 
						{
							Count c = new Count(nou.item, 1);
							nr2.add(c);
						}
						else 
						{
							for (Count cprod : nr2) 
							{
								if (cprod.item.getID() == nou.item.getID()) 
								{
									cprod.count++;
								}
							}
						}
					}
				}
				nou = nou.next;
			}
	}
	
	public void nr_produse (ShoppingCart cart) 
	{
		ShoppingCart.Node<Item> nou = cart.head;
		int ok = 0;
		while(nou != null) {
			ok = 0;
			for (Item it : products)
			{
				if (it.getID() == nou.item.getID()) 
				{
					for (Count co : nr)
					{
						if (co.item.getID() == nou.item.getID()) 
						{
							ok = 1;
						}
					}
					if (ok == 0)
					{
						Count c = new Count(nou.item, 1);
						nr.add(c);
					}
					else 
					{
						for (Count cprod : nr)
						{
							if (cprod.item.getID() == nou.item.getID())
							{
								cprod.count++;
							}
						}
					}
				}
			}
			nou = nou.next;
		}
	}
	
	abstract void accept (ShoppingCart basket);
	
}
