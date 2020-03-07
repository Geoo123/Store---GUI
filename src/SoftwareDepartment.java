public class SoftwareDepartment extends Department 
{
	public SoftwareDepartment (String nume, int ID) 
	{
		this.nume = nume;
		this.ID = ID;
	}
	
	void accept (ShoppingCart basket) 
	{
		basket.visit(this);
	}
}
