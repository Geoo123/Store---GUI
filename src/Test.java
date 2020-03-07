//Țaran Elena-Georgiana 325CC
import java.io.*;
import java.util.*;
import java.io.PrintWriter;
import java.io.File;
import javax.swing.*;
		
public class Test
{
	public static void main (String args[])
	{
		try 
		{
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
		LoadFiles frame = new LoadFiles("Welcome");
		frame.setLocationRelativeTo(null);
		String filename = "output.txt";
		File file = new File(filename);
        try 
        {
            file.createNewFile();
        } 
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        try 
        {
	        PrintWriter pw = new PrintWriter(file);	
			RandomAccessFile f;
			Store magazin = null;
			String s = "test06/store.txt";
			try 
			{
				f = new RandomAccessFile(s, "r");
				String line;
				line = f.readLine();
				magazin = new Store(line);
				Singleton.getInstance(magazin);
				while ((line = f.readLine()) != null) 
				{
					StringTokenizer st  = new StringTokenizer(line, ";");
					while (st.hasMoreTokens())
					{
						String str = st.nextToken();
						if (str.equals("BookDepartment")) 
						{
							int id = Integer.parseInt(st.nextToken());
							BookDepartment book = new BookDepartment(str, id);
							if(Singleton.getInstance(magazin) != null)
							{
								magazin.addDepartment(book);
								line = f.readLine();
								int nr = Integer.parseInt(line);
								for (int i = 0; i < nr; i++) 
								{
									line = f.readLine();
									st  = new StringTokenizer(line, ";");
									String bookname = st.nextToken();
									id = Integer.parseInt(st.nextToken());
									double pret = Double.parseDouble(st.nextToken());
									Item it = new Item(bookname, id, pret);
									book.addItem(it);
								}
							}
						}
						if (str.equals("MusicDepartment")) 
						{
							int id = Integer.parseInt(st.nextToken());
							MusicDepartment music = new MusicDepartment(str, id);
							if(Singleton.getInstance(magazin) != null) 
							{
								magazin.addDepartment(music);
								line = f.readLine();
								int nr = Integer.parseInt(line);
								for (int i = 0; i < nr; i++) 
								{
									line = f.readLine();
									st  = new StringTokenizer(line, ";");
									String bookname = st.nextToken();
									id = Integer.parseInt(st.nextToken());
									double pret = Double.parseDouble(st.nextToken());
									Item it = new Item(bookname, id, pret);
									music.addItem(it);
								}
							}
						}
						if (str.equals("SoftwareDepartment")) 
						{
							int id = Integer.parseInt(st.nextToken());
							SoftwareDepartment soft = new SoftwareDepartment(str, id);
							if(Singleton.getInstance(magazin) != null) 
							{
								magazin.addDepartment(soft);
								line = f.readLine();
								int nr = Integer.parseInt(line);
								for (int i = 0; i < nr; i++) 
								{
									line = f.readLine();
									st  = new StringTokenizer(line, ";");
									String bookname = st.nextToken();
									id = Integer.parseInt(st.nextToken());
									double pret = Double.parseDouble(st.nextToken());
									Item it = new Item(bookname, id, pret);
									soft.addItem(it);
								}
							}
						}
						if (str.equals("VideoDepartment")) 
						{
							int id = Integer.parseInt(st.nextToken());
							VideoDepartment video = new VideoDepartment(str, id);
							if(Singleton.getInstance(magazin) != null) 
							{
								magazin.addDepartment(video);
								line = f.readLine();
								int nr = Integer.parseInt(line);
								for (int i = 0; i < nr; i++) 
								{
									line = f.readLine();
									st  = new StringTokenizer(line, ";");
									String bookname = st.nextToken();
									id = Integer.parseInt(st.nextToken());
									double pret = Double.parseDouble(st.nextToken());
									Item it = new Item(bookname, id, pret);
									video.addItem(it);
								}
							}
						}
	
					}
				}
					
				f.close();
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			try 
			{
				s = "test06/customers.txt";
				f = new RandomAccessFile(s, "r");
				String line;
				line = f.readLine();
				int nr_clienti = Integer.parseInt(line);
				for (int i = 0; i < nr_clienti; i++)
				{
					line = f.readLine();
					StringTokenizer st  = new StringTokenizer(line, ";");
					String client = st.nextToken();
					double buget = Double.parseDouble(st.nextToken());
					String strateg = st.nextToken();
					ShoppingCart cart = new ShoppingCart(buget);
					WishList list = new WishList (strateg);
					Customer cumparator = new Customer(client, cart, list);
					if(Singleton.getInstance(magazin) != null) {
						magazin.enter(cumparator);
					}
				}
					
				f.close();
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				if(Singleton.getInstance(magazin) != null)
				{
					s = "test06/events.txt";
					f = new RandomAccessFile(s, "r");
					String line;
					Item it = null;
					Customer cl = null;
					Department dept = null;
					line = f.readLine();
					int nr_linii = Integer.parseInt(line);
					for (int i = 0; i < nr_linii; i++)
					{
						line = f.readLine();
						StringTokenizer st  = new StringTokenizer(line, ";");
						String event = st.nextToken();
						if (event.equals("addItem")) 
						{
							int ItemId = Integer.parseInt(st.nextToken());
							for (Department dep : magazin.departamente)
								for (Item item : dep.products)
									if (item.getID() == ItemId)
									{
										dept = dep;
										it = item;
										break;			
							}
							String location = st.nextToken();
							String client = st.nextToken();
							for (Customer c : magazin.clienti)
							{
								if (c.nume.equals(client)) 
								{
									cl = c;
									break;
								}
							}
							if (location.equals("WishList")) 
							{
								cl.list.add(it);
								dept.addObserver(cl);
							}
							if (location.equals("ShoppingCart"))
							{
								if (cl.cart.add(it)) 
								{
									dept.enter(cl);

								}
								else
								{
									cl.list.add(it);
									dept.addObserver(cl);
								}
							}
						}
						
						if (event.equals("delItem")) 
						{
							int ItemId = Integer.parseInt(st.nextToken());
							for (Department dep : magazin.departamente)
								for (Item item : dep.products)
									if (item.getID() == ItemId) 
									{
										dept = dep;
										it = item;
										break;			
									}
							String location = st.nextToken();
							String client = st.nextToken();
							for (Customer c : magazin.clienti)
							{
								if (c.nume.equals(client)) 
								{
									cl = c;
									break;
								}
							}
							if (location.equals("WishList"))
							{
								cl.list.remove(it);
								int nr = 0;
								for (Item iti : dept.products)
										if (cl.list.contains(iti)) 
										{
											nr++;
										}
								if (nr == 0) 
								{
									dept.removeObserver(cl);
								}
							}
							if (location.equals("ShoppingCart")) 
							{
								cl.cart.remove(it);
							}		
						}
						
						if (event.equals("addProduct")) 
						{
							int ok = 0;
							Notification.NotificationType type = Notification.NotificationType.ADD;
							int DepId = Integer.parseInt(st.nextToken());
							int ItemId = Integer.parseInt(st.nextToken());
							double price = Double.parseDouble(st.nextToken());
							String name = st.nextToken();
							Item item = new Item (name, ItemId, price);
							for (Department dep : magazin.departamente) 
							{
									if (dep.getId() == DepId) 
									{
										dept = dep;
										break;			
									}
							}
							for (Item iti : dept.products) 
							{
								if (iti.getID() == ItemId) 
								{
									ok = 1;
								}
							}
							if (ok == 0)
							{		
								dept.addItem(item);
								Notification not = new Notification (type, ItemId, DepId);
								dept.notifyAllObservers(not);
							}
						}
						
						if (event.equals("modifyProduct"))
						{
							Notification.NotificationType type = Notification.NotificationType.MODIFY;
							int DepId = Integer.parseInt(st.nextToken());
							int ItemId = Integer.parseInt(st.nextToken());
							double price = Double.parseDouble(st.nextToken());
							for (Department dep : magazin.departamente)
									if (dep.getId() == DepId)
									{
										dept = dep;
										for (Item item : dep.products)
											if (item.getID() == ItemId)
											{
												item.ajustarePret(price);
											}								
									}
							Notification not = new Notification (type, ItemId, DepId);
							dept.notifyAllObservers(not);
							for (Customer c : dept.observers) 
							{
								ItemList.Node<Item> nod = c.cart.head;
								ItemList.Node<Item> nod2 = c.list.head;
								while (nod != null) {
									if (nod.item.getID() == ItemId) 
									{
										c.cart.buget += nod.item.getPret();
										nod.item.ajustarePret(price);
										c.cart.buget -= price;
										c.cart.sort();
									}
									nod = nod.next;
								}
								while (nod2 != null) 
								{
									if (nod2.item.getID() == ItemId) 
									{
										nod2.item.ajustarePret(price);
									}
									nod2 = nod2.next;
								}
							}
						}
						
						if (event.equals("delProduct"))
						{
							int nr = 0;
							Notification.NotificationType type = Notification.NotificationType.REMOVE;
							int ItemId = Integer.parseInt(st.nextToken());
							Notification not = null;
							for (Department dep : magazin.departamente)
								for (Item item : dep.products)
									if (item.getID() == ItemId) 
									{
												dept = dep;
												it = item;
												not = new Notification (type, ItemId, dept.getId());
												for (Customer c : magazin.clienti) 
												{
													c.list.remove(item);
													c.cart.remove(item);
													nr = 0;
													for (Item iti : dept.products)
														if (c.list.contains(iti)) 
														{
															nr++;
														}
													if (nr == 0) 
													{
														dept.removeObserver(c);
													}
												}
												break;
									}
							dept.products.remove(it);
							if (not != null)
								dept.notifyAllObservers(not);
						}
						
						if (event.equals("getItem")) 
						{
							String name = st.nextToken();
							Item item = null;
							int nr = 0;
							for (Customer c : magazin.clienti)
								if (c.nume.equals(name)) 
								{
									nr = 0;
									cl =  c;
									item = c.list.executeStrategy(c.cart);
									pw.printf("%s;%d;%.2f\n", item.getName(), item.getID(), item.getPret());
								}
							for (Department dep : magazin.departamente) 
							{
								for (Item iti : dep.products)
									if (iti.getID() == item.getID())
									{
										dept = dep;										
									}
							}
							for (Item iti : dept.products)
								if (cl.list.contains(iti)) 
								{
									nr++;
								}	
							if (nr == 0) 
							{
								dept.removeObserver(cl);
							}						
						}
							
						if (event.equals("getItems")) 
						{
							String location = st.nextToken();
							String name = st.nextToken();
							for (Customer c : magazin.clienti)
								if (c.nume.equals(name)) 
								{
									if (location.equals("ShoppingCart")) 
									{
										pw.println(c.cart.afis());
									}
									if (location.equals("WishList"))
									{
										pw.println(c.list.afis());
									}
									
								}
						}
						
						if (event.equals("getTotal")) 
						{
							String location = st.nextToken();
							String name = st.nextToken();
							for (Customer c : magazin.clienti)
								if (c.nume.equals(name)) 
								{
									if (location.equals("ShoppingCart")) 
									{
										pw.printf("%.2f\n", Math.round(c.cart.getTotalPrice() * 100) / 100.00);
									}
									if (location.equals("WishList")) 
									{
										pw.printf("%.2f\n", Math.round(c.list.getTotalPrice() * 100) / 100.00);
								
									}
								}
						}
						
						if (event.equals("accept")) 
						{
							int DepId = Integer.parseInt(st.nextToken());
							String name = st.nextToken();
							for (Department dep : magazin.departamente) 
							{
								if (dep.getId() == DepId)
									for (Customer c : magazin.clienti)
										if (c.nume.equals(name)) 
										{
											dep.accept(c.cart);
										}
							}
						}
						
						if (event.equals("getObservers")) 
						{
							int nr = 0;
							int DepId = Integer.parseInt(st.nextToken());
							for (Department dep : magazin.departamente)
								if (dep.getId() == DepId) 
								{
									for (Customer c : magazin.clienti) 
									{
										nr = 0;
										for (Item item : dep.products) 
										{
											ItemList.Node<Item> nod = c.list.head;
											while (nod != null) 
											{
												if (item.getName() == nod.item.getName() && item.getID() == nod.item.getID()) {
												nr++;
												}
												nod = nod.next;
											}
										}
										if (nr == 0) 
										{
											dep.removeObserver(c);
										}
									}
									dep.transformObservertoString();
									pw.println(dep.observer);
								}
						}
						
						if (event.equals("getNotifications")) 
						{
							String name = st.nextToken();
							for (Customer c : magazin.clienti)
								if (c.nume.equals(name))
								{
									pw.println(c.printNotification());
								}		
						}
						
					}
					f.close();
				}
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			pw.close();
        }
        catch (FileNotFoundException e) 
        {
        	e.printStackTrace();
        }
	}
}
