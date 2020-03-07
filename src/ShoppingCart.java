import java.util.Collection;

interface Visitor
{
	void visit(BookDepartment bookDepartment);
	void visit(MusicDepartment musicDepartment);
	void visit(SoftwareDepartment softwareDepartment);
	void visit(VideoDepartment videoDepartment);
}

public class ShoppingCart extends ItemList implements Visitor 
{
	double buget;
	
	public ShoppingCart (double buget) 
	{
		this.buget = buget;
	}
	
	public boolean add (Item element) 
	{
		Item nou = new Item(element.getName(), element.getID(), element.getPret());
		Node<Item> new_node = new Node<>(nou, tail, null);
		if (nou.getPret() > buget)
			return false;
		if (head == null) 
		{
			head = new_node;
			tail = new_node;
			head.index = 0;
			buget = buget - nou.getPret();
			return true;
		}
		if (tail != null) 
			tail.next = new_node;
		tail = new_node;
		buget = buget - nou.getPret();
		sort();
		iterate();
		return true;
	}

	void sort()
	{
		nameComp nume = new nameComp();
		priceComp pret = new priceComp();
		Node<Item> temp = head;
		Node<Item> temp2 = temp;
		Item it;
		while (temp != null) 
		{
			temp2 = temp.next;
			while (temp2 != null) 
			{
				if (pret.compare(temp, temp.next) > 0) 
				{
					it = (Item)temp.item;
					temp.item = temp.next.item;
					temp.next.item = it;
				}
				else
					if (pret.compare(temp, temp.next) == 0) 
					{
						if (nume.compare(temp, temp2) > 0) 
						{
							it = (Item)temp.item;
							temp.item = temp.next.item;
							temp.next.item = it;
						}
					}
				temp2 = temp2.next;
			}
		temp = temp.next;
		}
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
		if (size == 1) {
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
			buget = buget + temp.item.getPret();
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
	
	public void removeAll() 
	{
		while(head != null) 
		{
			head = head.next;
		}
		tail = null;
	}
	
	public void visit(BookDepartment bookDepartment)
	{
		double suma = 0;
		ShoppingCart.Node<Item> temp = head;
		while (temp != null) 
		{
			if (bookDepartment.checkID(((Item)temp.item).getID()))
			{
				suma = suma + temp.item.getPret();
				((Item)temp.item).ajustarePret(((Item)temp.item).getPret() * 90 / 100);
			}
			temp = temp.next;
		}
		buget = buget + suma / 10; 
	}
	
	public void visit(MusicDepartment musicDepartment) 
	{
		double suma = 0;
		for (Item item : musicDepartment.products) 
		{
			ShoppingCart.Node<Item> temp = head;
			while (temp != null) 
			{
				if (temp.item.getName() == item.getName())
					suma = suma + temp.item.getPret();
			temp = temp.next;
			
			}
		}
		buget = buget + suma / 10;
	}
	
	public void visit(SoftwareDepartment softwareDepartment) 
	{
		double min = 999999;
		double suma = 0;
		ShoppingCart.Node<Item> temp = head;
		for (Item item : softwareDepartment.products)
			if (item.getPret() < min)
				min = item.getPret();
		if (buget < min)
		{
			while(temp != null) 
			{
				if (softwareDepartment.checkID(((Item)temp.item).getID()) == true) 
				{
					suma = suma + temp.item.getPret();
					((Item)temp.item).ajustarePret(((Item)temp.item).getPret() * 80 / 100);
				}
				temp = temp.next;
			}
			buget = buget + suma / 5;
		}
	}
	
	public void visit(VideoDepartment videoDepartment) 
	{
		double total = 0;
		for (Item item : videoDepartment.products) 
		{
			ShoppingCart.Node<Item> temp = head;
			while (temp != null) 
			{
				if (temp.item.getName() == item.getName())
					total = total + temp.item.getPret();
				temp = temp.next;
			}
		}
		buget = buget + total / 20;
		double max = -1;
		for (Item item : videoDepartment.products)
			if (item.getPret() > max)
				max = item.getPret();
		ShoppingCart.Node<Item> temp = head;
		if (total > max)
		{
			while (temp != null) 
			{
				if (videoDepartment.checkID(((Item)temp.item).getID()) == true) 
				{
					((Item)temp.item).ajustarePret(((Item)temp.item).getPret() * 85 / 100);
				}
				temp = temp.next;
			}
		
			buget = buget + total * 15 / 100;
		}
	}
			
}