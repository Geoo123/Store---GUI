import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ShopC extends JFrame 
{
		private JButton backbutton, add, remove, order, getitem;
		private DefaultListModel<Item> model, model0;
		private DefaultListModel<String> model1;
		public JList<Item> list1;
		public JList<String> shoplist;
		JLabel buget, nr_prod, strategy, get;
		JTextField Buget, Nr_prod, strateg, geti;
		String NR, BUGET;
		String rez;

		public void adaugare(Store magazin) 
		{
			model = new DefaultListModel<>();
			int i = 0;
			for (Department dep : magazin.departamente)
			{
				for (Item it : dep.products) 
				{
					model.add(i, it);
					i++;
				}
			}
		
			list1 = new JList<>(model);
		}
		public ShopC (String nume, Customer c, Store magazin)
		{
			super(nume);
			getContentPane().setBackground(new java.awt.Color(255, 238, 153));
			GridBagLayout grid = new GridBagLayout();
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			setLayout(new FlowLayout());
			setSize(1200, 430);
		    setMinimumSize(new Dimension(1200, 430)); 
		    model1 = new DefaultListModel<>();
		    model0 = new DefaultListModel<>();
		    buget = new JLabel("Budget");
		    nr_prod = new JLabel("Nr. of products");
		    strategy = new JLabel("Strategy:");
		    get = new JLabel("Get Item");
		    String itis;
		    if (c.list.isEmpty())
		    	geti = new JTextField("");
		    else
		    {
			    if (c.list.strateg.equals("A") && !(c.list.isEmpty())) 
			    {
			    	c.list.sortName();
			    	itis = c.list.head.item + "";
			    	geti = new JTextField(itis);
			    }
			    if (c.list.strateg.equals("B") && !(c.list.isEmpty())) 
			    {
			    	c.list.sortPrice();
			    	itis = c.list.head.item + "";
			    	geti = new JTextField(itis);
			    }
			    if (c.list.strateg.equals("C") && c.list.nr_elemente() > 1)
			    {
			    	itis = c.list.tail.item + "";
			    	geti = new JTextField(itis);
			    }
			    else 
			    	if (c.list.strateg.equals("C") && c.list.nr_elemente() == 1)
			    	{
			    		itis = c.list.head.item + "";
			    		geti = new JTextField(itis);
			    	}
		    }
		    geti.setEditable(false);
		    strateg = new JTextField(c.list.strateg);
		    strateg.setEditable(false);
		    BUGET = c.cart.buget + "";
		    Buget = new JTextField(BUGET);
		    Buget.setEditable(false);
		    NR = c.cart.nr_elemente() + "";
		    Nr_prod = new JTextField(NR);
		    Nr_prod.setEditable(false);
		    getitem = new JButton("Apply the strategy");
		    getitem.addActionListener(new ActionListener()
		    {
				public void actionPerformed(ActionEvent e) 
				{
					Item item = null;
					Department dept = null;
					int nr = 0;
					if (geti.getText().equals(""))
						return;
					else 
					{
						item = c.list.executeStrategy(c.cart);
						model1.removeAllElements();
	            		model0.removeAllElements();
	            		ItemList.Node<Item> nod2 = c.cart.head;
	        			int index = 0;
	            		while (nod2 != null) 
	            		{
	            			for (Department dep : magazin.departamente)
	            				for (Item it : dep.products)
	            					if (it.getID() == nod2.item.getID())
	            						dept = dep;
	            			rez = "Name: " + nod2.item.getName() + "  Department's ID: " + dept.getId() + "  Products'ID: " + nod2.item.getID() + "  Price: " + nod2.item.getPret();
	            			model1.add(index, rez);
	            			model0.add(index, nod2.item);
	            			nod2 = nod2.next;
	            			index++;
	            		}
	            		
	            		String itis;
	            		if (c.list.isEmpty())
	            		{
	            			geti.setText("");
	            		}
	            		else 
	            		{
		        		    if (c.list.strateg.equals("A")) 
		        		    {
		        		    	c.list.sortPrice();
		        		    	itis = c.list.head.item + "";
		        		    	geti.setText(itis);
		        		    }
		        		    else
		        		    	if (c.list.strateg.equals("B"))
		        		    	{
		        		    		c.list.sortName();
		        		    		itis = c.list.head.item + "";
		        		    		geti.setText(itis);
		        		    	}
		        		    	else
		        		    		if (c.list.strateg.equals("C") && c.list.nr_elemente() > 1)
		        		    		{
		        		    			itis = c.list.tail.item + "";
		        		    			geti.setText(itis);
		        		    		}
		        		    		else 
		        		    			if (c.list.strateg.equals("C") && c.list.nr_elemente() == 1) 
		        		    			{
		        		    				itis = c.list.head.item + "";
		        		    				geti.setText(itis);
		        		    			}
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
							if (c.list.contains(iti)) 
							{
								nr++;
							}	
						if (nr == 0) 
						{
							dept.removeObserver(c);
						}						
					}
				}
			});
		    order = new JButton("Order Now");
		    order.addActionListener(new ActionListener() 
		    {
				public void actionPerformed(ActionEvent e) 
				{
					for (Department dep : magazin.departamente)
						dep.viz = -1;
					ShoppingCart.Node<Item> nod = c.cart.head;
					while(nod != null)
					{
						for (Department dep : magazin.departamente) 
						{
							for (Item it : dep.products) 
							{
								if (nod.item.getID() == it.getID())
								{
									if (dep.viz != dep.getId()) 
									{
										dep.nr_produse(c.cart);
									}
									if (dep.getId() == 0)
										dep.viz = 0;
									if (dep.getId() == 1)
										dep.viz = 1;
									if (dep.getId() == 2)
										dep.viz = 2;
									if (dep.getId() == 3)
										dep.viz = 3;
									break;
								}
							}
						}
						nod = nod.next;
						
					}
					c.cart.removeAll();
					dispose();
					ShopC nou = new ShopC(c.nume, c, magazin);
					nou.setLocationRelativeTo(null);
				}
			});
		    backbutton = new JButton (new ImageIcon("imagini/back2.png"));
	        backbutton.addActionListener(new ActionListener() 
	        {
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
	        add = new JButton("Add to Shopping Cart");
	        add.addActionListener(new ActionListener() 
	        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if ("Add to Shopping Cart".equals(e.getActionCommand()))
                {
                    if(list1.isSelectionEmpty())
                        return;
                    else 
                    {
                        for (int i = 0; i < model.size(); i++) 
                        {
                            if (i == list1.getSelectedIndex()) 
                            {
	                            	Department dept = null;
	                        		int ItemId = model.get(i).getID();
	                        		for (Department dep : magazin.departamente)
	                        		{
	                        			for (Item item : dep.products)
	                        				if (item.getID() == ItemId) 
	                        				{
	                        					dept = dep;
	                        					break;			
	                        				}
	                        		}
                            		if(c.cart.add(model.get(i)))
                            		{
                            			dept.enter(c);
                            			NR = c.cart.nr_elemente() + "";
                            			Nr_prod.setText(NR);
                            			BUGET = c.cart.buget + "";
                            		    Buget.setText(BUGET);
                            		}
                            		else
                            		{
        									c.list.add(model.get(i));
        									dept.addObserver(c);
        									JOptionPane.showMessageDialog(null, "Insufficient Budget! Product is added to Wish List.", "Warning", JOptionPane.ERROR_MESSAGE); 
        									
        							}
                            		model1.removeAllElements();
                            		model0.removeAllElements();
                            		ItemList.Node<Item> nod2 = c.cart.head;
                            		int index = 0;
                            		while (nod2 != null)
                            		{
                            			for (Department dep : magazin.departamente)
                            				for (Item it : dep.products)
                            					if (it.getID() == nod2.item.getID())
                            						dept = dep;
                            			rez = "Name: " + nod2.item.getName() + "  Department's ID: " + dept.getId() + "  Products'ID: " + nod2.item.getID() + "  Price: " + nod2.item.getPret();
                            			model1.add(index, rez);
                            			model0.add(index, nod2.item);
                            			nod2 = nod2.next;
                            			index++;
                            		}
                            		
                            }
                        }
                    }
                	list1.clearSelection();}
            	}
	        });
	        remove = new JButton("Remove from Shopping Cart");
	        remove.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e)
	            {
	                if ("Remove from Shopping Cart".equals(e.getActionCommand()))
	                {
	                    if(shoplist.isSelectionEmpty())
	                        return;
	                    else 
	                    {
	                    	Department dept = null;
	                        for (int i = 0; i < model1.size(); i++) 
	                        {
	                            if (i == shoplist.getSelectedIndex()) 
	                            {
	                            		if(c.cart.remove(model0.get(i)))
	                            		{
	                            			NR = c.cart.nr_elemente() + "";
	                            			Nr_prod.setText(NR);
	                            			BUGET = c.cart.buget + "";
	                            		    Buget.setText(BUGET);
	                            		}
	                            		model1.removeAllElements();
	                            		model0.removeAllElements();
	                            		ItemList.Node<Item> nod2 = c.cart.head;
	                            		int index = 0;
	                            		while (nod2 != null) 
	                            		{
	                            			for (Department dep : magazin.departamente)
	                            				for (Item it : dep.products)
	                            					if (it.getID() == nod2.item.getID())
	                            						dept = dep;
	                            			rez = "Name: " + nod2.item.getName() + "  Department's ID: " + dept.getId() + "  Products'ID: " + nod2.item.getID() + "  Price: " + nod2.item.getPret();
	                            			model1.add(index, rez);
	                            			model0.add(index, nod2.item);
	                            			nod2 = nod2.next;
	                            			index++;
	                            		}
	                            		
	                            }
	                        }
	                    }
	                	list1.clearSelection();
	                }
	            }
		    });
	        adaugare(magazin);
		    list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        list1.setVisibleRowCount(-1); 
	        JScrollPane scroll = new JScrollPane(list1);
	        scroll.setPreferredSize(new Dimension(250,360));
	        
	        shoplist = new JList<>(model1);
	        shoplist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        shoplist.setVisibleRowCount(-1); 
	        JScrollPane scroll1 = new JScrollPane(shoplist);
	        scroll1.setPreferredSize(new Dimension(500,360));
    		model1.removeAllElements();
    		model0.removeAllElements();
    		ItemList.Node<Item> nod2 = c.cart.head;
    		Department dept = null;
    		int index = 0;
    		while (nod2 != null) 
    		{
    			for (Department dep : magazin.departamente)
    				for (Item it : dep.products)
    					if (it.getID() == nod2.item.getID())
    						dept = dep;
    			rez = "Name: " + nod2.item.getName() + "  Department's ID: " + dept.getId() + "  Products'ID: " + nod2.item.getID() + "  Price: " + nod2.item.getPret();
    			model1.add(index, rez);
    			model0.add(index, nod2.item);
    			nod2 = nod2.next;
    			index++;
    		}
    		
    		JPanel panel = new JPanel(grid);
    		panel.setBackground(new java.awt.Color(255, 238, 153));
    		panel.add(strategy, gbc);
    		panel.add(strateg, gbc);
    		panel.add(get, gbc);
    		panel.add(geti, gbc);
    		panel.add(getitem, gbc);
    		panel.add(buget, gbc);
    		panel.add(Buget, gbc);
	        panel.add(nr_prod, gbc);
	        panel.add(Nr_prod, gbc);
	        panel.add(add, gbc);
	        panel.add(remove, gbc);
	        panel.add(order, gbc);
	        panel.add(backbutton);
	        add(scroll);
	        add(panel);
	        add(scroll1);
	        validate();
	        setVisible(true);
			
		}
		
}
