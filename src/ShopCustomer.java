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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ShopCustomer extends JFrame 
{
		private JButton shoppingcart, wishlist, backbutton, see;
		private JList<Customer> customerslist;
		private DefaultListModel<Customer> list;
		
		public ShopCustomer (String nume, Store magazin) 
		{
			super(nume);
			getContentPane().setBackground(new java.awt.Color(255, 238, 153));
			setLayout(new FlowLayout());
			setSize(200, 300);
		    	setMinimumSize(new Dimension(300, 350));  
		    	GridBagLayout grid = new GridBagLayout();
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			JPanel panel = new JPanel(grid);
	        list = new DefaultListModel<>();
	        int i = 0;
	        for (Customer c : magazin.clienti) 
	        {
	        	list.add(i, c);
	        	i++;
			}
	        backbutton = new JButton (new ImageIcon("imagini/back2.png"));
	        backbutton.addActionListener(new ActionListener() 
	        {
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
	        see = new JButton(new ImageIcon("imagini/n2.png"));
	        see.addActionListener(new ActionListener()
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                    if(customerslist.isSelectionEmpty()) 
	                    {
	                    	JOptionPane.showMessageDialog(null, "You first need to select a client!", "Warning", JOptionPane.WARNING_MESSAGE);
	                        return;
	                    }
	                    else 
	                    {
	                        for (int i = 0; i < list.size(); i++) 
	                        {
	                            if (i == customerslist.getSelectedIndex()) 
	                            {
	                            		Notifications not = new Notifications(list.get(i).nume + "'s notifications", list.get(i));
	                            		not.setLocationRelativeTo(null);
	                            }
	                        }
	                    }
	            	}
		    });
	        shoppingcart = new JButton(new ImageIcon("imagini/s2.png"));
	        shoppingcart.addActionListener(new ActionListener() 
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	                    if(customerslist.isSelectionEmpty())
	                    {
	                    	JOptionPane.showMessageDialog(null, "You first need to select a client!", "Warning", JOptionPane.WARNING_MESSAGE);
	                        return;
	                    }
	                    else 
	                    {
	                        for (int i = 0; i < list.size(); i++)
	                        {
	                            if (i == customerslist.getSelectedIndex()) 
	                            {
	                            		ShopC vizshop = new ShopC(list.get(i).nume + "'s Shopping Cart", list.get(i), magazin);
	                            		vizshop.setLocationRelativeTo(null);
	                            }
	                        }
	                    }
	            	}
		    });
	        wishlist = new JButton(new ImageIcon("imagini/w2.png"));
	        wishlist.addActionListener(new ActionListener() 
	        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                    if(customerslist.isSelectionEmpty())
                    {
                    	JOptionPane.showMessageDialog(null, "You first need to select a client!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    else 
                    {
                        for (int i = 0; i < list.size(); i++) 
                        {
                            if (i == customerslist.getSelectedIndex())
                            {
                            		Wish vizwish = new Wish(list.get(i).nume + "'s Wish List", list.get(i), magazin);
                            		vizwish.setLocationRelativeTo(null);
                            }
                        }
                    }
            	}
	        });
	        customerslist = new JList<>(list);
	        customerslist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        customerslist.setVisibleRowCount(-1); 
	        JScrollPane scroll = new JScrollPane(customerslist);
	        scroll.setPreferredSize(new Dimension(200,300));
	        add(scroll);
	        
	        panel.add(shoppingcart, gbc);
	        panel.add(wishlist, gbc);
	        panel.add(see, gbc);
	        panel.add(backbutton);
	        add(panel);
	        validate();
	        setVisible(true);
	        pack();
		}
}
