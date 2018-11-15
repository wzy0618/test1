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
import java.util.Calendar;
/**
 * 
 * @author Group009
 * @version 1.0
 */
public class Gui_AdminFunction extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JButton add;
	JButton remove;
	JButton change;
	JButton alert;
	JButton bill;
	JLabel email;
	JButton exit;
	int sequence1;
	ProvSy pro;
	/**
	 * 
	 * @param pro the whole system
	 * @param sequence the number of administrator who has logged in
	 */
	public Gui_AdminFunction(Object pro,int sequence) {
		this.pro=(ProvSy) pro;
		sequence1 = sequence;
		Calendar test = Calendar.getInstance();
		int date = test.get(Calendar.DATE);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//Container container = getContentPane();
		//container.setLayout(new FlowLayout());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		j1.setLayout(new GridLayout(6,0));
		
		Label1 = new JLabel("You have successfully logged in! Please choose what you want:");
		add = new JButton("Add new resident");
		remove = new JButton("Remove resident");
		change = new JButton("Change tarrif");
		alert = new JButton("Check gas and electricity meter readings");
		bill = new JButton("Check bills history");
		exit = new JButton("Exit");
		if(date==1)
		email = new JLabel("You have sent the bills to all residents by email!");
		else
		email = new JLabel("Today is not first day of this month");
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(exit,BorderLayout.SOUTH);
		j1.add(add);
		j1.add(remove);
		j1.add(change);
		j1.add(alert);
		j1.add(bill);
		j1.add(email);
		j1.setVisible(true);
		setVisible(true);
		add.addActionListener(this);
		remove.addActionListener(this);
		change.addActionListener(this);
		alert.addActionListener(this);
		bill.addActionListener(this);
		exit.addActionListener(this);
		
		//container.add(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == add){
			new Gui_AddRemove(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == remove){
			new Gui_remove(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == change){
			new Gui_ChangeTarrif(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == alert){
			new Gui_CheckReading(this.pro,sequence1);
			
			dispose();
		}
		else if(event.getSource() == bill){
			new Gui_CheckConsumption(this.pro,sequence1);
			
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
