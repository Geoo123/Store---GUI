import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SingleDepartment extends JFrame 
{
		private JButton observ, customer, expensive, wish, shopped, backbutton;
		
		public SingleDepartment (String nume, Department dep, Store magazin)
		{
			super(nume);
			getContentPane().setBackground(new java.awt.Color(255, 238, 153));
			setLayout(new FlowLayout());
			setSize(320, 230);
			setMinimumSize(new Dimension(320, 230));
			observ = new JButton("Observers");
			observ.setPreferredSize(new Dimension(150, 30));
			
			observ.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					Observ o = new Observ(dep.getName() + "'s Observers", dep);
					o.setLocationRelativeTo(null);
				}
			});
			customer = new JButton("Customers");
			customer.setPreferredSize(new Dimension(150, 30));
			customer.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					Customers c = new Customers(dep.getName() + "'s Observers", dep);
					c.setLocationRelativeTo(null);
				}

			});
			expensive = new JButton("Most expensive");
			expensive.setPreferredSize(new Dimension(150, 30));
			expensive.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					double max = 0;
					Item expensive_item = null;
					for (Item item : dep.products)
					{
						if (max < item.getPret()) 
						{
							expensive_item = item;
							max = item.getPret();
						}
							
					}
					String info = "Name: " + expensive_item.getName() + "  Product's ID: " + expensive_item.getID() + "  Price: " + expensive_item.getPret(); 
					JOptionPane.showMessageDialog(null, info, "The most expensive product", JOptionPane.INFORMATION_MESSAGE);
				}

			});
			shopped = new JButton("Most sold");
			shopped.setPreferredSize(new Dimension(150, 30));
			shopped.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					if (dep.nr.isEmpty()) 
					{
						JOptionPane.showMessageDialog(null, "No product has been sold yet", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else 
					{
						int max = 0;
							Item wanted_item = null;
							for (Count co : dep.nr) {
								if (max < co.count) {
									wanted_item = co.item;
									max = co.count;
								}
								
							}
						String info = "Name: " + wanted_item.getName() + "  Product's ID: " + wanted_item.getID() + "  Price: " + wanted_item.getPret() + "\n" + "Number of demands: " + max;; 
						JOptionPane.showMessageDialog(null, info, "The most expensive product", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			wish = new JButton("Most desirable");
			wish.setPreferredSize(new Dimension(250, 30));
			wish.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					if (dep.observers.isEmpty()) 
					{
						JOptionPane.showMessageDialog(null, "No product has been added in a Wish List", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					else 
					{
						int max = 0;
						dep.nr2.removeAllElements();
							Item wanted_item = null;
							for (Customer c : dep.observers)
						    {
								for (Department dep : magazin.departamente) 
								{
									dep.prod_dorite(c.list);
								}
								
							}
							for (Count co : dep.nr2) 
							{
								if (co.count > max) {
									max = co.count;
									wanted_item = co.item;
								}
							}
						String info = "Name: " + wanted_item.getName() + "  Product's ID: " + wanted_item.getID() + "  Price: " + wanted_item.getPret() + "\n" + "Number of desired products: " + max;; 
						JOptionPane.showMessageDialog(null, info, "The most desirable product", JOptionPane.INFORMATION_MESSAGE);
					}
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
	        add(observ);
	        add(customer);
	        add(shopped);
	        add(expensive);
	        add(wish);
	        add(backbutton);
	        validate();
	        setVisible(true);
		}
}
