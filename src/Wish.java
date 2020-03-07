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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Wish extends JFrame
{
		private JButton backbutton, add, remove;
		private DefaultListModel<Item> model, model1;
		private DefaultListModel<String> model0;
		public JList<Item> list1;
		public JList<String> listwish;
		Department dept = null;
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
		public Wish (String nume, Customer c, Store magazin) 
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
		    backbutton = new JButton (new ImageIcon("imagini/back2.png"));
	        backbutton.addActionListener(new ActionListener() 
	        {
				public void actionPerformed(ActionEvent e)
				{
					dispose();
				}
			});
	        add = new JButton("Add to Wish List");
	        add.addActionListener(new ActionListener()
	        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if ("Add to Wish List".equals(e.getActionCommand()))
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
                            		c.list.add(model.get(i));
                            		dept.addObserver(c);
                            		model1.removeAllElements();
                            		model0.removeAllElements();
                            		ItemList.Node<Item> nod2 = c.list.head;
                        			int index = 0;
                            		while (nod2 != null) 
                            		{
                            			for (Department dep : magazin.departamente)
                            				for (Item it : dep.products)
                            					if (it.getID() == nod2.item.getID())
                            						dept = dep;
                            			rez = "Name: " + nod2.item.getName() + "  Department's ID: " + dept.getId() + "  Products'ID: " + nod2.item.getID() + "  Price: " + nod2.item.getPret();
                            			model0.add(index, rez);
                            			model1.add(index, nod2.item);
                            			nod2 = nod2.next;
                            			index++;
                            		}
                            		
                            }
                        }
                    }
                	list1.clearSelection();}
            	}
	        });
	        remove = new JButton("Remove from Wish List");
	        remove.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                if ("Remove from Wish List".equals(e.getActionCommand()))
	                {
	                    if(listwish.isSelectionEmpty())
	                        return;
	                    else 
	                    {
	                        for (int i = 0; i < model1.size(); i++) 
	                        {
	                            if (i == listwish.getSelectedIndex()) 
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
	                            		c.list.remove(model1.get(i));
	                            		int nr = 0;
	    								for (Item iti : dept.products)
	    										if (c.list.contains(iti))
	    										{
	    											nr++;
	    										}
	    								if (nr == 0) 
	    								{
	    									dept.removeObserver(c);
	    								}
	                            		model1.removeAllElements();
	                            		model0.removeAllElements();
	                            		ItemList.Node<Item> nod2 = c.list.head;
                            			int index = 0;
	                            		while (nod2 != null) 
	                            		{
	                            			for (Department dep : magazin.departamente)
	                            				for (Item it : dep.products)
	                            					if (it.getID() == nod2.item.getID())
	                            						dept = dep;
	                            			rez = "Name: " + nod2.item.getName() + "  Department's ID: " + dept.getId() + "  Products'ID: " + nod2.item.getID() + "  Price: " + nod2.item.getPret();
	                            			model0.add(index, rez);
	                            			model1.add(index, nod2.item);
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
	        
	        listwish = new JList<>(model0);
	        listwish.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        listwish.setVisibleRowCount(-1); 
	        JScrollPane scroll1 = new JScrollPane(listwish);
    		model1.removeAllElements();
    		model0.removeAllElements();
    		ItemList.Node<Item> nod2 = c.list.head;
			int index = 0;
    		while (nod2 != null)
    		{
    			for (Department dep : magazin.departamente)
    				for (Item it : dep.products)
    					if (it.getID() == nod2.item.getID())
    						dept = dep;
    			rez = "Name: " + nod2.item.getName() + "  Department's ID: " + dept.getId() + "  Products'ID: " + nod2.item.getID() + "  Price: " + nod2.item.getPret();
    			model0.add(index, rez);
    			model1.add(index, nod2.item);
    			nod2 = nod2.next;
    			index++;
    		}
	        scroll1.setPreferredSize(new Dimension(500,360));
	        JPanel panel = new JPanel(grid);
	        add(scroll);
	        panel.setBackground(new java.awt.Color(255, 238, 153));
	        panel.add(add, gbc);
	        panel.add(remove, gbc);
	        panel.add(backbutton);
	        validate();
	        add(panel);
	        add(scroll1);
	        setVisible(true);	
		}
	}

