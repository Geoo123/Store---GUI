import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ViewProdByPrice1 extends JFrame
{
		private JButton backbutton, sort1, sort2, sort3, no_sort, remove, add, refresh, modify;
		Vector<Item> price_products_asc = new Vector<>();
		DefaultListModel<Item> model;
		public JList<Item> list2;
			
		class priceComp implements Comparator<Item> 
		{
			public int compare(Item i1, Item i2) 
			{
				if (i1.getPret() > i2.getPret())
					return 1;
				if (i1.getPret() == i2.getPret())
					return 0;
				return -1;				
			}
		}
						
		public void adaugare(Store magazin)
		{
			model = new DefaultListModel<>();
			int i;
			for (Department dep : magazin.departamente)
			{
				for (Item it : dep.products) 
				{
					price_products_asc.add(it);
				}
				Collections.sort(price_products_asc, new priceComp());
				for (i = 0; i < price_products_asc.size(); i++)
					model.add(i, price_products_asc.get(i));
			}
			
			list2 = new JList<>(model);
		}
			
		public ViewProdByPrice1 (String nume, Store magazin) 
		{
			super(nume);
			getContentPane().setBackground(new java.awt.Color(255, 238, 153));
			setLayout(new FlowLayout());
			setSize(200, 300);
		    setMinimumSize(new Dimension(300, 500));  
		    backbutton = new JButton (new ImageIcon("imagini/back2.png"));
		    no_sort = new JButton("No Filter");
		    no_sort.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent e)
				{       
						ViewProd view = new ViewProd("Products", magazin);
						view.setLocationRelativeTo(null);
						dispose();
				}
			});
		    sort1 = new JButton("Sort by name");
		    sort1.addActionListener(new ActionListener() 
		    {
		    	public void actionPerformed(ActionEvent e)
		    	{       
					ViewProdByName view1 = new ViewProdByName("Products", magazin);
					view1.setLocationRelativeTo(null);
					dispose();
				}
		    });
		    sort2 = new JButton("Sort ascending by price");
		    sort3 = new JButton("Sort descending by price");
		    sort3.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent e)
				{       
						ViewProdByPrice2 view3 = new ViewProdByPrice2("Products", magazin);
						view3.setLocationRelativeTo(null);
						dispose();
				}
			});
	        backbutton.addActionListener(new ActionListener() 
	        {
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
	        modify = new JButton ("Modify Price of a Product");
	        modify.setPreferredSize(new Dimension(250, 29));
	        modify.addActionListener(new ActionListener()
	        {
				public void actionPerformed(ActionEvent e)
				{  
					 if(list2.isSelectionEmpty()) 
					 {
						 ModifyPrice modp = new ModifyPrice("Modify Price", null, null, magazin);
						 modp.setLocationRelativeTo(null);
					 }
					 else 
					 {
						 for (int i = 0; i < model.size(); i++) 
						 {
	                            if (i == list2.getSelectedIndex()) 
	                            {
	                            	Department dept = null;
	                            	for (Department dep : magazin.departamente)
	                            	{
	    								for (Item item : dep.products) 
	    								{
	    									if (item.getID() == model.get(i).getID()) 
	    									{
	    												dept = dep;
	    												break;
	    									}
	    								}
	                            	}
	                            	String it_id = model.get(i).getID() + "";
	                            	String dep_id = dept.getId() + "";
	                            	ModifyPrice modp = new ModifyPrice("Modify Price", it_id, dep_id, magazin);
	                            	modp.setLocationRelativeTo(null);
	                            }
						 }
					 }
				}
	        });
	        add = new JButton ("Add Product");
	        add.addActionListener(new ActionListener()
	        {
				public void actionPerformed(ActionEvent e)
				{       
					AddProd addp = new AddProd("Add Product", magazin);
					addp.setLocationRelativeTo(null);
				}
	        });
	        refresh = new JButton (new ImageIcon("imagini/ref2.png"));
	        refresh.addActionListener(new ActionListener() 
	        {
	        	public void actionPerformed(ActionEvent e) 
	        	{
	        		dispose();
	        		ViewProdByPrice1 viewnou = new ViewProdByPrice1("Products", magazin);
					viewnou.setLocationRelativeTo(null);
	        	}
	        });
	        remove = new JButton ("Remove Product");
	        remove.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                if ("Remove Product".equals(e.getActionCommand()))
	                {
	                    if(list2.isSelectionEmpty()) 
	                    {
	                    	JOptionPane.showMessageDialog(null, "You first need to select a product!", "Warning", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }
	                    else 
	                    {
	                        for (int i = 0; i < model.size(); i++) 
	                        {
	                            if (i == list2.getSelectedIndex()) 
	                            {
	                                int nr = 0;
	                                Department dept = null;
	                                Item it = null;
	                                int ItemId = list2.getModel().getElementAt(i).getID();
	    							Notification.NotificationType type = Notification.NotificationType.REMOVE;
	    							for (Department dep : magazin.departamente)
	    							{
	    								for (Item item : dep.products) 
	    								{
	    									if (item.getID() == ItemId) 
	    									{
	    												dept = dep;
	    												it = item;
	    									}
	    								}
	    							}
	    							Notification not = new Notification (type, ItemId, dept.getId());
	    							for (Customer c : magazin.clienti) 
	    							{
	    									c.list.remove(it);
	    									c.cart.remove(it);
	    									nr = 0;
	    									for (Item iti : dept.products) 
	    									{
	    										if (c.list.contains(iti)) 
	    										{
	    											nr++;
	   											}
	 										}
	    									if (nr == 0) {
	    										dept.removeObserver(c);
	    									}
	    							}
	    							price_products_asc.remove(i);
	    							model.removeAllElements();
	    							dept.products.remove(it);
	    							int index = 0;
	    							for (Department dep : magazin.departamente)
	    							{
	    								for (Item item : dep.products) 
	    								{
	    									model.add(index, item);
	    									index++;
	    								}
	    							}
	    							dept.notifyAllObservers(not);
	    							list2.clearSelection();
	                            }
	                        }
	                    }
	                }
	            }
	        });
	        adaugare(magazin);
	        
	        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        list2.setVisibleRowCount(-1); 
	        JScrollPane scroll2 = new JScrollPane(list2);
	        scroll2.setPreferredSize(new Dimension(250,200));
	        
	        add(scroll2);
	        add(no_sort);
	        add(sort1);
	        add(sort2);
	        add(sort3);
	        add(remove);
	        add(add);
	        add(modify);
	        add(refresh);
	        add(backbutton);
	        validate();
	        setVisible(true);
	         
			}
}
