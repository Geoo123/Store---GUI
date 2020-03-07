import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Notifications extends JFrame 
{
		private DefaultListModel<String> model;
		private JButton backbutton;
		private JList<String> list;
		
		public Notifications (String nume, Customer c) 
		{
			super(nume);
			getContentPane().setBackground(new java.awt.Color(255, 238, 153));
			setLayout(new FlowLayout());
			setSize(550, 370);
		    setMinimumSize(new Dimension(550, 370)); 
		    model = new DefaultListModel<>();
		    backbutton = new JButton (new ImageIcon("imagini/back2.png"));
	        backbutton.addActionListener(new ActionListener()
	        {
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
	        if (c.notify.size() > 0) 
	        {
	        	Notification not;
	        	String result = "";
				Iterator <Notification> note = c.notify.iterator();
				for (int i = 0; i < c.notify.size(); i++) 
				{
					not = note.next();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LLLL.yyyy-HH:mm");
					result = not.type + ";  " + "Products's ID: " + not.id_product + ";  " + "Department's ID: " + not.id_department + ";  " + not.now.format(formatter);
					model.add(i, result);
				}
	        }
	        list = new JList<>(model);
	        JScrollPane scroll = new JScrollPane(list);
	        scroll.setPreferredSize(new Dimension(500,250));
	        add(scroll);
	        add(backbutton);
	        validate();
	        setVisible(true);
			
		}
}
	
