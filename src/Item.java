class Item 
{
	private String name;
	private int ID;
	private double pret;
	
	public Item (String name, int ID, double pret) 
	{
		this.name = name;
		this.ID = ID;
		this.pret = pret;
	}
	
	public String toString()
	{
		return name + ";" + ID + ";" + pret;
	}
	
	public String getName () 
	{
		return name;
	}
	
	public int getID () 
	{
		return ID;
	}
	
	public double getPret () 
	{
		return pret;
	}
	
	public void ajustarePret(double pret_nou)
	{
		pret = pret_nou;
	}
}
