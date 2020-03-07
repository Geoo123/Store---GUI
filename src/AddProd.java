import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class AddProd extends JFrame 
{
		private JButton add, backbutton;
		private JLabel DEP_ID, ITEM_ID, PRICE, NAME;
		private JTextField DepId, ItemId, Price, Name;
		
		public AddProd (String nume, Store magazin) 
		{
			super(nume);
			getContentPane().setBackground(new java.awt.Color(255, 238, 153));
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			setSize(300, 300);
			setMinimumSize(new Dimension(300, 300));
			add = new JButton (new ImageIcon("imagini/add2.png"));
			add.addActionListener(new ActionListener() 
			{
	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	      
	                    if(DepId.getText() != null && ItemId.getText() != null && Price.getText() != null) 
	                    {
	                    	int ok = 0;
	                    	Item item;
	                    	Department dept = null;
							Notification.NotificationType type = Notification.NotificationType.ADD;
							if (Name.getText().equals("")) 
							{
								String s = "Produs_" + DepId.getText() + "_" + ItemId.getText();
								item = new Item (s, Integer.parseInt(ItemId.getText()), Double.parseDouble(Price.getText()));
							}
							else 
							{
								item = new Item (Name.getText(), Integer.parseInt(ItemId.getText()), Double.parseDouble(Price.getText()));
							}
							for (Department dep : magazin.departamente)
							{
									if (dep.getId() == Integer.parseInt(DepId.getText())) 
									{
										dept = dep;
										break;			
									}
							}
							for (Item it : dept.products)
							{
								if (it.getID() == Integer.parseInt(ItemId.getText())) 
								{
									ok = 1;
								}
							}
							if (ok == 0) 
							{
								
								dept.addItem(item);
								Notification not = new Notification (type, Integer.parseInt(ItemId.getText()), Integer.parseInt(DepId.getText()));
								dept.notifyAllObservers(not);
								DepId.setText("");
								ItemId.setText("");
								Price.setText("");
								Name.setText("");
								JOptionPane.showMessageDialog(null, "Product has been added succesfully!", "Information", JOptionPane.INFORMATION_MESSAGE);
							}
							else
								if (ok == 1)
								{
									JOptionPane.showMessageDialog(null, "Product is already in the store!", "Warning", JOptionPane.ERROR_MESSAGE);
								}
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
			PRICE = new JLabel("Price");
			Price = new JTextField(10);
			NAME = new JLabel("Name");
			Name = new JTextField(20);
			add(DEP_ID, gbc);
			add(DepId, gbc);
			add(ITEM_ID, gbc);
			add(ItemId, gbc);
			add(PRICE, gbc);
			add(Price, gbc);
			add(NAME, gbc);
			add(Name, gbc);
			add(add);
			add(backbutton);
			validate();
			setVisible(true);
		}
}
