import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Shop extends JFrame 
{
	private JButton view, backbutton, depart;
	
	public Shop (String nume, Store magazin) 
	{
		super(nume);
		setLayout(new GridBagLayout());
		getContentPane().setBackground(new java.awt.Color(255, 238, 153));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		Insets margin = new Insets(20, 150, 20, 150); 
		setSize(300, 300);
		setMinimumSize(new Dimension(450, 300));
		view = new JButton("View All Products");
		view.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{       
					ViewProd view_prod = new ViewProd("Products", magazin);
					view_prod.setLocationRelativeTo(null);
			}
		});
		view.setMargin(margin);
		depart = new JButton("Departments");
		depart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{       
				Departamente dep = new Departamente("Departments", magazin);
				dep.setLocationRelativeTo(null);
			}
		});
		depart.setMargin(margin);
		backbutton = new JButton (new ImageIcon("imagini/back2.png"));
		backbutton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		add(view, gbc);
		add(depart, gbc);
		add(backbutton);
		validate();
		setVisible(true);
	}
}
