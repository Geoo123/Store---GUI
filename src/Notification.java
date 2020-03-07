import java.time.LocalDateTime;


class Notification 
{
	LocalDateTime now = LocalDateTime.now();
	public enum NotificationType 
	{ 
		ADD, REMOVE, MODIFY; 
	}
	Notification note;
	NotificationType type;
	int id_department;
	int id_product;
	public Notification (NotificationType type, int id_product, int id_department)
	{
		this.id_department = id_department;
		this.id_product = id_product;
		this.type = type;
	}
	
	public String toString() 
	{
		return note.type + ";" + note.id_product + ";" + note.id_department;
	}
}