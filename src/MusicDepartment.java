public class MusicDepartment extends Department 
{
	public MusicDepartment (String nume, int ID) 
	{
		this.nume = nume;
		this.ID = ID;
	}
	void accept (ShoppingCart basket) 
	{
		basket.visit(this);
	}
}