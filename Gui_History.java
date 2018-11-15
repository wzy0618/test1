package system;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Group009
 * @version 1.0
 */
public class Gui_History extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JButton daily;
	JButton weekly;
	JButton monthly;
	JButton back;
	JButton exit;
	String elecDay;
	String gasDay;
	String[] dataFile;
	int sequence1;
	ProvSy pro;
/**
 * 
 * @param pro the whole system
 * @param sequence the household_id of the user who has logged in
 */
	public Gui_History(Object pro,int sequence) {
		this.pro=(ProvSy) pro;
		sequence1 = sequence;
		//FilePath filepath = new FilePath();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//Container container = getContentPane();
		//container.setLayout(new FlowLayout());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		j1.setLayout(new GridLayout(4,0));
		
		Label1 = new JLabel("Please choose the period you want:");
		daily = new JButton("Daily");
		weekly = new JButton("Weekly");
		monthly = new JButton("Monthly");
		back = new JButton("Back");
		exit = new JButton("Exit");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(exit,BorderLayout.SOUTH);
		j1.add(daily);
		j1.add(weekly);
		j1.add(monthly);
		j1.add(back);
		j1.setVisible(true);
		setVisible(true);
		daily.addActionListener(this);
		weekly.addActionListener(this);
		monthly.addActionListener(this);
		back.addActionListener(this);
		exit.addActionListener(this);
		
		//container.add(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == daily){
			new Gui_ViewDaily(this.pro,3,sequence1);
			dispose();
		}
		else if(event.getSource() == weekly)
		{
			new Gui_ViewDaily(this.pro,4,sequence1);
			dispose();
		}
		else if(event.getSource() == monthly)
		{
			new Gui_ViewDaily(this.pro,5,sequence1);
			dispose();
		}
		else if(event.getSource() == back)
		{
			new Gui_View(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit)
		{
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
