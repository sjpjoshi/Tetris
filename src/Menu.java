import java.awt.event.*;  
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends Achievement 
{
	private Window w;
	private JFrame wind;
	private JFrame window;
	private int diff = 1;
	
	//constructor
	public Menu()
	{
		wind = new JFrame("Menu");
		JButton play;
		JButton difficulty;
		JLabel name;
		
		//tetris name
		name = new JLabel("Tetris");
		name.setBounds(150, 120, 300, 110);
		name.setFont(new Font(name.getFont().getName(), name.getFont().getStyle(), 110));
		//play button
		play = new JButton("Play");
		play.setBounds(225, 275, 150, 50);
		play.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				wind.setVisible(false);
				System.out.println(diff);
				w = new Window(diff);
				w.Tet();
			}
		});
		//difficulty button
		difficulty = new JButton("Difficulties");
		difficulty.setBounds(225, 350, 150, 50);
		difficulty.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				wind.setVisible(false);
				Difficulty();
			}
		});
		
		wind.add(play);	
		wind.add(difficulty);
		wind.add(name);
		wind.setSize(600, 600);
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setResizable(false); 
		wind.setLocationRelativeTo(null);
		wind.setLayout(null);
		wind.setVisible(true); 
	}
	
	public void Difficulty() 
	{
		window = new JFrame("Difficulty Select");
		window.setSize(600, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		window.setResizable(false); 
		window.setLocationRelativeTo(null); 
		//label
		JLabel u = new JLabel("Please reach a higher score");
		u.setBounds(30, 300, 600, 100);
		u.setFont(new Font(u.getFont().getName(), u.getFont().getStyle(), 40));
		u.setVisible(false);
		//lable
		JLabel n = new JLabel("Please select difficulty");
		n.setBounds(0, 100, 600, 100);
		n.setFont(new Font(n.getFont().getName(), n.getFont().getStyle(), 54));
		//back button
		JButton b = new JButton("Back");
		b.setBounds(10, 500, 100, 50);
		b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				diffOff();
				menuOn();
			}
		});
		//difficulty 1
		JButton d1 = new JButton("Difficulty 1");
		d1.setBounds(10, 250, 100, 50);
		d1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setDiff(1);
				u.setVisible(false);
			}
		});
		//difficulty 2
		JButton d2 = new JButton("Difficulty 2");
		d2.setBounds(130, 250, 100, 50);
		d2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(d2Check())
				{
					setDiff(2);
					u.setVisible(false);
				}
				else
				{
					u.setVisible(true);
				}
			}
		});
		//difficulty 3
		JButton d3 = new JButton("Difficulty 3");
		d3.setBounds(245, 250, 100, 50);
		d3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(d3Check())
				{
					setDiff(3);
					u.setVisible(false);
				}
				else
				{
					u.setVisible(true);
				}
			}
		});
		//difficulty 4
		JButton d4 = new JButton("Difficulty 4");
		d4.setBounds(360, 250, 100, 50);
		d4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(d4Check())
				{
					setDiff(4);
					u.setVisible(false);
				}
				else
				{
					u.setVisible(true);
				}
			}
		});
		//difficulty 5
		JButton d5 = new JButton("Difficulty 5");
		d5.setBounds(475, 250, 100, 50);
		d5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(d5Check())
				{
					setDiff(5);
					u.setVisible(false);
				}
				else
				{
					u.setVisible(true);
				}
			}
		});
		
		window.add(d1);
		window.add(d2);
		window.add(d3);
		window.add(d4);
		window.add(d5);
		window.add(n);
		window.add(b);
		window.add(u);
		window.setLayout(null);
		window.setVisible(true);
	}
	
	public void diffOff()
	{
		window.setVisible(false);
	}
	
	public void menuOn()
	{
		wind.setVisible(true);
	}
	
	public void setDiff(int d)
	{
		diff = d;
	}
}
