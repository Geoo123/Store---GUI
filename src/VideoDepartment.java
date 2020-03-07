public class VideoDepartment extends Department 
{
	public VideoDepartment (String nume, int ID) 
	{
		this.nume = nume;
		this.ID = ID;
	}
	
	void accept (ShoppingCart basket) 
	{
			basket.visit(this);
	}
}