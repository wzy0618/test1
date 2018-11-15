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
public class Gui_SetBudget extends JFrame implements ActionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	JLabel Label1;
	JLabel Label2;
	JButton save;
	JButton back;
	JButton exit;
	JLabel ele;
	JLabel gas;
	TextField elebudget;
	TextField gasbudget;
	int sequence1;
	int type;
	ProvSy pro;
/**
 * 
 * @param pro the whole system
 * @param a the type of budget 
 * @param sequence the household_id of the user who has logged in
 */
	public Gui_SetBudget(Object pro,int a,int sequence) {
		this.pro=(ProvSy) pro;
		sequence1 = sequence;
		type=a%2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		JPanel j2= new JPanel();
		JPanel top=new JPanel();
		j1.setLayout(new GridLayout(2,2));
		j2.setLayout(new GridLayout(3,1));
		top.setLayout(new GridLayout(2,1));
		if(type==0)
		Label1 = new JLabel("Set your budget in consumption:");
		if(type==1)
		Label1 = new JLabel("Set your budget in cost:");
		
		top.add(Label1);
		
		if(a>1){
		Label2 = new JLabel("Set successfully!");
		top.add(Label2);
		}
		elebudget= new TextField(20);
		gasbudget= new TextField(20);
		
	
		ele = new JLabel("Electricity");
		gas = new JLabel("Gas");
		save=new JButton("save");
		back = new JButton("Back");
		exit = new JButton("Exit");
		
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(j2,BorderLayout.SOUTH);

		
		j1.add(ele);
		j1.add(elebudget);
		j1.add(gas);
		j1.add(gasbudget);
		j2.add(save);
		j2.add(back);
		j2.add(exit);
		j1.setVisible(true);
		setVisible(true);
		
		save.addActionListener(this);
		back.addActionListener(this);
		exit.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == save){
			int judge=0;//judge whether the input is in correct format
			int i=0;
			for(i=0;i<this.pro.users.size();i++){
				if(this.pro.users.get(i).household_id==sequence1)
					break;
			}
			if(elebudget.getText().length()!=0){
			String a = elebudget.getText().trim();
			try{Double x=Double.parseDouble(a);
			if(x>0){
			this.pro.users.get(i).setEleBudget(x,type);
			judge=1;}}
			catch(Exception e){System.out.print("Wrong format !!!");}
			}
		
				if(gasbudget.getText().length()!=0){
				String b = gasbudget.getText().trim();
				try{Double y=Double.parseDouble(b);
				if(y>0){
				this.pro.users.get(i).setGasBudget(y,type);
				judge=1;}}
				catch(Exception e){System.out.print("Wrong format !!!");}
				}

			if((elebudget.getText().length()!=0||gasbudget.getText().length()!=0)&&judge==1)
			{type+=2;}
			new Gui_SetBudget(this.pro,type,sequence1);
			dispose();
		}

		else if(event.getSource() == back){
			new Gui_BudgetSet(this.pro,2,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
