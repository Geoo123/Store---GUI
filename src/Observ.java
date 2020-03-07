import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

class Observ extends JFrame 
{
		private JButton backbutton;
		public DefaultListModel<String> model;
		public JList<String> list;
		
		public Observ(String nume, Department dep) 
		{
			super(nume);
			getContentPane().setBackground(new java.awt.Color(255, 238, 153));
			setLayout(new FlowLayout());
			setSize(300, 320);
			setMinimumSize(new Dimension(350, 320));
			model = new DefaultListModel<>();
			int index = 0;
			if (!dep.observers.isEmpty())
			{
				for (Customer c : dep.observers) 
				{
					model.add(index, c.nume);
					index++;
				}
			}
			list = new JList<>(model);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        list.setVisibleRowCount(-1); 
	        JScrollPane scroll = new JScrollPane(list);
	        scroll.setPreferredSize(new Dimension(250,200));
	        backbutton = new JButton (new ImageIcon("imagini/back2.png"));
			backbutton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
			add(scroll);
			add(backbutton);
			validate();
			setVisible(true);
			
		}
}
	
