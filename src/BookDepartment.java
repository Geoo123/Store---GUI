public class BookDepartment extends Department 
{
	public BookDepartment (String nume, int ID) 
	{
		this.nume = nume;
		this.ID = ID;
	}
		
	void accept (ShoppingCart basket)
	{
		basket.visit(this);
	}
}