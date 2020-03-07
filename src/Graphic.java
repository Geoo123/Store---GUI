import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

class Graphic extends JFrame
{
	private JButton b1, b2, backbutton;
	Store magazin;
	
	public Graphic (String nume, Store magazin) 
	{
		super(nume);
		this.magazin = magazin;
		getContentPane().setBackground(new java.awt.Color(255, 238, 153));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		Insets margin = new Insets(20, 150, 20, 150); 
		setSize(300, 300);
		setMinimumSize(new Dimension(450, 300));
		b1 = new JButton("View the shop");
		b1.setMargin(margin);
		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{       
					Shop shop = new Shop("Shop", magazin);
					shop.setLocationRelativeTo(null);
			}
		});
		b2 = new JButton("Customer");
		b2.setMargin(margin);
		b2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{       
					ShopCustomer sh = new ShopCustomer("Customers", magazin);
					sh.setLocationRelativeTo(null);
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
		add(b1, gbc);
		add(b2, gbc);
		add(backbutton);
		validate();
		setVisible(true);	
	}
}
