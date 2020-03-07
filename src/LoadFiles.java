import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

class LoadFiles extends JFrame 
{
	private JButton button;
	private JProgressBar bar;
	private Timer timer = null;
	
	public void updateBar(int newValue)
	{
	    bar.setValue(newValue);
	}
	
	public LoadFiles (String nume) 
	{
		super(nume);
		setLayout(new GridBagLayout());
		setSize(600, 650);
		setMinimumSize(new Dimension(600, 650));
		getContentPane().setBackground(new java.awt.Color(255, 238, 153));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer = new Timer(1500, new ActionListener( ) 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (bar.getValue() == 100)
					dispose();
			}
		});
		button = new JButton("Upload The Files");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{ 
				timer.start();	
				RandomAccessFile f;
				Store magazin = null;
				String s = "test01/store.txt";
				try {
					f = new RandomAccessFile(s, "r");
					String line;
					line = f.readLine();
					magazin = new Store(line);
					Singleton.getInstance(magazin);
					while ((line = f.readLine()) != null) 
					{
						StringTokenizer st  = new StringTokenizer(line, ";");
						while (st.hasMoreTokens()) {
							String str = st.nextToken();
							if (str.equals("BookDepartment")) 
							{
								int id = Integer.parseInt(st.nextToken());
								BookDepartment book = new BookDepartment(str, id);
								if(Singleton.getInstance(magazin) != null)
								{
									magazin.addDepartment(book);
									line = f.readLine();
									int nr = Integer.parseInt(line);
									for (int i = 0; i < nr; i++)
									{
										line = f.readLine();
										st  = new StringTokenizer(line, ";");
										String bookname = st.nextToken();
										id = Integer.parseInt(st.nextToken());
										double pret = Double.parseDouble(st.nextToken());
										Item it = new Item(bookname, id, pret);
										book.addItem(it);
									}
								}
							}
							updateBar(20);
							if (str.equals("MusicDepartment")) 
							{
								int id = Integer.parseInt(st.nextToken());
								MusicDepartment music = new MusicDepartment(str, id);
								if(Singleton.getInstance(magazin) != null) 
								{
									magazin.addDepartment(music);
									line = f.readLine();
									int nr = Integer.parseInt(line);
									for (int i = 0; i < nr; i++) 
									{
										line = f.readLine();
										st  = new StringTokenizer(line, ";");
										String bookname = st.nextToken();
										id = Integer.parseInt(st.nextToken());
										double pret = Double.parseDouble(st.nextToken());
										Item it = new Item(bookname, id, pret);
										music.addItem(it);
									}
								}
							}
							updateBar(40);
							if (str.equals("SoftwareDepartment"))
							{
								int id = Integer.parseInt(st.nextToken());
								SoftwareDepartment soft = new SoftwareDepartment(str, id);
								if(Singleton.getInstance(magazin) != null) 
								{
									magazin.addDepartment(soft);
									line = f.readLine();
									int nr = Integer.parseInt(line);
									for (int i = 0; i < nr; i++) {
										line = f.readLine();
										st  = new StringTokenizer(line, ";");
										String bookname = st.nextToken();
										id = Integer.parseInt(st.nextToken());
										double pret = Double.parseDouble(st.nextToken());
										Item it = new Item(bookname, id, pret);
										soft.addItem(it);
									}
								}
							}
							updateBar(60);
							if (str.equals("VideoDepartment")) 
							{
								int id = Integer.parseInt(st.nextToken());
								VideoDepartment video = new VideoDepartment(str, id);
								if(Singleton.getInstance(magazin) != null) 
								{
									magazin.addDepartment(video);
									line = f.readLine();
									int nr = Integer.parseInt(line);
									for (int i = 0; i < nr; i++) 
									{
										line = f.readLine();
										st  = new StringTokenizer(line, ";");
										String bookname = st.nextToken();
										id = Integer.parseInt(st.nextToken());
										double pret = Double.parseDouble(st.nextToken());
										Item it = new Item(bookname, id, pret);
										video.addItem(it);
									}
									updateBar(80);
								}
							}
		
						}
					}
						
					f.close();
				}
				catch (FileNotFoundException ex) 
				{
					ex.printStackTrace();
				}
				catch (IOException ex) 
				{
					ex.printStackTrace();
				}
				
				try {
					s = "test01/customers.txt";
					f = new RandomAccessFile(s, "r");
					String line;
					line = f.readLine();
					int nr_clienti = Integer.parseInt(line);
					for (int i = 0; i < nr_clienti; i++)
					{
						line = f.readLine();
						StringTokenizer st  = new StringTokenizer(line, ";");
						String client = st.nextToken();
						double buget = Double.parseDouble(st.nextToken());
						String strateg = st.nextToken();
						ShoppingCart cart = new ShoppingCart(buget);
						WishList list = new WishList (strateg);
						Customer cumparator = new Customer(client, cart, list);
						if(Singleton.getInstance(magazin) != null) {
							magazin.enter(cumparator);
						}
					}
						
					f.close();
				}
				catch (FileNotFoundException ex) 
				{
					ex.printStackTrace();
				}
				catch (IOException ex) 
				{
					ex.printStackTrace();
				}
			updateBar(100);
			Graphic mag = new Graphic("Welcome", magazin);
			mag.setLocationRelativeTo(null);
	
			} 
		});
		button.setPreferredSize(new Dimension(200, 50));
		ImageIcon im = new ImageIcon("imagini/mag.jpeg");
		JLabel icon = new JLabel(im);
       	Graphics g = icon.getGraphics();
       	add(icon);
		bar = new JProgressBar(0, 100);
		updateBar(0);
		add(icon, gbc);
		add(button, gbc);
		add(bar, gbc);
		setVisible(true);
	}
	
}
