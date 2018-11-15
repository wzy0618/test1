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
public class Gui_View extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JButton elec;
	JButton gas;
	JButton history;
	JButton back;
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
	public Gui_View(Object pro,int sequence) {
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
		j1.setLayout(new GridLayout(3,0));
		
		Label1 = new JLabel("You have successfully logged in! Please choose what you want:");
		elec = new JButton("View consumption and cost of electricity of the day");
		gas = new JButton("View consumption and cost of gas of the day");
		history = new JButton("View history");
		back = new JButton("Back");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(back,BorderLayout.SOUTH);
		j1.add(elec);
		j1.add(gas);
		j1.add(history);
		j1.setVisible(true);
		setVisible(true);
		elec.addActionListener(this);
		gas.addActionListener(this);
		history.addActionListener(this);
		back.addActionListener(this);
		
		//container.add(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == elec){
			new Gui_ViewDaily(this.pro,1,sequence1);
			dispose();
		}
		else if(event.getSource() == gas)
		{
			new Gui_ViewDaily(this.pro,2,sequence1);
			dispose();
		}
		else if(event.getSource() == history)
		{
			new Gui_History(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == back)
		{
			new Gui_ResidentFunction(this.pro,sequence1);
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
