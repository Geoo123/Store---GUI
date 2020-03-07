import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Departamente extends JFrame 
{
	private JButton dep1, dep2, dep3, dep4, backbutton;
	Department dep = null, dept = null;
	
	public Departamente (String nume, Store magazin)
	{
		super(nume);
		getContentPane().setBackground(new java.awt.Color(255, 238, 153));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		Insets margin = new Insets(20, 150, 20, 150); 
		setSize(300, 300);
		setMinimumSize(new Dimension(500, 450));
		dep1 = new JButton("Music Department");
		dep1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (Department dep : magazin.departamente)
					if (dep.getName().equals("MusicDepartment")) 
						dept = dep;
				SingleDepartment sgdep = new SingleDepartment("Music Department", dept, magazin);
				sgdep.setLocationRelativeTo(null);
			}
		});
		dep2 = new JButton("Book Department");
		dep2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				for (Department dep : magazin.departamente)
					if (dep.getName().equals("BookDepartment")) 
						dept = dep;
				SingleDepartment sgdep = new SingleDepartment("Book Department", dept, magazin);
				sgdep.setLocationRelativeTo(null);
			}
		});
		dep3 = new JButton("Software Department");
		dep3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				for (Department dep : magazin.departamente)
					if (dep.getName().equals("SoftwareDepartment")) 
						dept = dep;
				SingleDepartment sgdep = new SingleDepartment("Software Department", dept, magazin);
				sgdep.setLocationRelativeTo(null);
			}
		});
		dep4 = new JButton("Video Department");
		dep4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				for (Department dep : magazin.departamente)
					if (dep.getName().equals("VideoDepartment")) 
						dept = dep;
				SingleDepartment sgdep = new SingleDepartment("Video Department", dept, magazin);
				sgdep.setLocationRelativeTo(null);
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
		dep1.setMargin(margin);
		dep2.setMargin(margin);
		dep3.setMargin(margin);
		dep4.setMargin(margin);
		add(dep1, gbc);
		add(dep2, gbc);
		add(dep3, gbc);
		add(dep4, gbc);
		add(backbutton);
		validate();
		setVisible(true);
	}
	
}
