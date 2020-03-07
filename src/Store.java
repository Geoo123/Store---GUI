import java.util.Iterator;
import java.util.Vector;

public class Store
{
	String nume;
	Vector <Department>departamente = new Vector <Department>();
	Vector <Customer>clienti = new Vector <Customer>();
	
	public Store (String nume) 
	{
		this.nume = nume;
	}
		
	public void enter(Customer client) 
	{
		if (!clienti.contains(client))
			clienti.add(client);
	}
	
	public void exit (Customer client)
	{
		if (clienti.contains(client))
			clienti.removeElement(client);
	}
	
	public ShoppingCart getShoppingCart (Double buget) 
	{
		for (Customer client : clienti)
			if (client.cart.buget == buget)
				return client.cart;
		return null;
	}
	
	public Vector<Customer> getCustomers() 
	{
		return clienti;
	}
	
	public Vector<Department> getDepartments()
	{
		return departamente;
	}
	
	public void addDepartment (Department dep) 
	{
		if (!departamente.contains(dep))
			departamente.add(dep);
	}
	
	public Department getDepartment (int id) 
	{
		Iterator <Department> dep = departamente.iterator();
		while (dep.hasNext())
			if (dep.next().getId() == id)
				return (Department)dep;
		return null;
	}
}