public class Count 
{
	Item item;
	int count;
	
	public Count(Item item, int count) 
	{
		
		this.item = item;
		this.count = count;
	}
	
	public String toString() 
	{
		return item + ";" + count;
	}
}