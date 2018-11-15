package system;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
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
public class Gui_BudgetSet extends JFrame implements ActionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	JLabel Label1;
	JLabel initial;
	JLabel initialValue;
	JLabel set;
	TextField setValue;
	JButton save;
	JButton back;
	JButton exit;
	JButton consumption;
	JButton cost;

	
	JPanel j2;
	int sequence1;
	ProvSy pro;
/**
 * 
 * @param pro the who system
 * @param a to judge whether the user set a budget or not
 * @param sequence the household_id of the user who has logged in
 */
	public Gui_BudgetSet(Object pro,int a,int sequence) {
		this.pro=(ProvSy) pro;
		sequence1 = sequence;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		JPanel j2= new JPanel();
		j1.setLayout(new GridLayout(2,1));
		j2.setLayout(new GridLayout(3,1));
		if(a==1)
		Label1 = new JLabel("You haven't set a budget yet. You have to set:");
		if(a==2)
		Label1 = new JLabel("You have already set a budget. You can change one:");
		
		
		
	
		consumption = new JButton("consumption");
		cost = new JButton("cost");
		
		back = new JButton("Back");
		exit = new JButton("Exit");
		
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(j2,BorderLayout.SOUTH);

		
		j1.add(consumption);
		j1.add(cost);
		
		j2.add(back);
		j2.add(exit);
		j1.setVisible(true);
		setVisible(true);
		consumption.addActionListener(this);
		cost.addActionListener(this);
		back.addActionListener(this);
		exit.addActionListener(this);
/*		
		if(sequence == 1){
			Label1.setText("You haven't set a budget. Please set one:");
			j1.remove(initial);
			j1.remove(initialValue);
		}
*/		
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == consumption){
			new Gui_SetBudget(this.pro,0,sequence1);
			dispose();
		}
		else if(event.getSource() == cost){
			new Gui_SetBudget(this.pro,1,sequence1);
			dispose();
		}
		else if(event.getSource() == back){
			new Gui_Budget(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
