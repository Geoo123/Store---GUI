import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ModifyPrice extends JFrame 
{
		private JButton modify, backbutton;
		private JLabel DEP_ID, ITEM_ID, PRICE;
		private JTextField DepId, ItemId, Price, Name;
		
		public ModifyPrice (String nume, String IDit, String IDdep, Store magazin) 
		{
			super(nume);
			getContentPane().setBackground(new java.awt.Color(255, 238, 153));
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			setSize(300, 300);
			setMinimumSize(new Dimension(300, 300));
			modify = new JButton (new ImageIcon("imagini/mod2.png"));
			modify.addActionListener(new ActionListener() 
			{
	            @Override
	            public void actionPerformed(ActionEvent e)
	            {
	                    if(DepId.getText() != null && ItemId.getText() != null && Price.getText() != null) 
	                    {
	                    	Department dept = null;
	                    	Notification.NotificationType type = Notification.NotificationType.MODIFY;
							for (Department dep : magazin.departamente) 
							{
									if (dep.getId() == Integer.parseInt(DepId.getText())) 
									{
										dept = dep;
										for (Item item : dep.products) 
										{
											if (item.getID() == Integer.parseInt(ItemId.getText())) {
												item.ajustarePret(Double.parseDouble(Price.getText()));
											}							
										}
									}
							}
							Notification not = new Notification (type, Integer.parseInt(ItemId.getText()), Integer.parseInt(DepId.getText()));
							dept.notifyAllObservers(not);
							for (Customer c : magazin.clienti) 
							{
								ItemList.Node<Item> nod = c.cart.head;
								ItemList.Node<Item> nod2 = c.list.head;
								while (nod != null) {
									if (((Item)nod.item).getID() == Integer.parseInt(ItemId.getText())) 
									{
										c.cart.buget += ((Item)nod.item).getPret();
										((Item)nod.item).ajustarePret(Double.parseDouble(Price.getText()));
										c.cart.buget -= Double.parseDouble(Price.getText());
										c.cart.sort();
									}
									nod = nod.next;
								}
								while (nod2 != null) 
								{
									if (((Item)nod2.item).getID() == Integer.parseInt(ItemId.getText())) 
									{
										((Item)nod2.item).ajustarePret(Double.parseDouble(Price.getText()));
									}
									nod2 = nod2.next;
								}
							}
							DepId.setText("");
							ItemId.setText("");
							Price.setText("");
							DepId.setEditable(true);
							ItemId.setEditable(true);
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
			DEP_ID = new JLabel("ID(Departament)");
			DepId = new JTextField(10);
			ITEM_ID = new JLabel("ID(Produs)");
			ItemId = new JTextField(15);
			PRICE = new JLabel("New Price");
			Price = new JTextField(10);
			if (IDit != null & IDdep != null) 
			{
				DepId.setText(IDdep);
				DepId.setEditable(false);
				ItemId.setText(IDit);
				ItemId.setEditable(false);			
			}
			add(DEP_ID, gbc);
			add(DepId, gbc);
			add(ITEM_ID, gbc);
			add(ItemId, gbc);
			add(PRICE, gbc);
			add(Price, gbc);
			add(modify);
			add(backbutton);
			validate();
			setVisible(true);
		}
	}
