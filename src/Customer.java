import java.util.Iterator;
import java.util.Vector;

interface Observer 
{
	public void update (Notification notification);
}

public class Customer implements Observer
{
	String nume;
	ShoppingCart cart;
	WishList list;
	Vector<Notification> notify = new Vector<>();
	
	public Customer (String nume, ShoppingCart cart, WishList list) 
	{
		this.nume = nume;
		this.cart = cart;
		this.list = list;
	}

	public String printNotification()
	{
		String result = "[";
		Notification not;
		if (notify.size() > 0) {
			Iterator <Notification> note = notify.iterator();
			for (int i = 0; i < notify.size() - 1; i++) 
			{
				not = note.next();
				result += not.type + ";" + not.id_product + ";" + not.id_department + ", ";
			}
			not = note.next();	
			result += not.type + ";" + not.id_product + ";" + not.id_department;
		}
		result += "]";
		return result;
	}
	
	public void update (Notification notification)
	{
		notify.add(notification);
	}
	
	public String toString()
	{
		return nume;
	}
}
