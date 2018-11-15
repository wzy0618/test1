package system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * 
 * @author Group009
 * @version 1.0
 */
public class Gui_FailtoRemove extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JLabel id;
	TextField TextId = new TextField(20);
	JButton check;
	JButton back;
	JButton exit;
	int sequence1;
	ProvSy pro;
/**
 * 
 * @param pro the whole system
 * @param sequence the number of the administrator who has logged in
 */
	public Gui_FailtoRemove(Object pro,int sequence) {
		this.pro=(ProvSy) pro;
		sequence1 = sequence;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(2, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		
		Label1 = new JLabel("                             Error! No such userid. Please Try again:");
		id = new JLabel("User_ID: ");                   
		check = new JButton("Delete");
		back = new JButton("Back");
		exit = new JButton("Exit");
		JPanel j2=new JPanel();
		JPanel j3=new JPanel();
		contentPane.add(Label1);
	    contentPane.add(j1);	
		j1.setLayout(new GridLayout(2,0));
		j1.add(j2);
		j1.add(j3);
		j2.add(id);
		j2.add(TextId);
		j3.add(check);
		j3.add(back);
		j3.add(exit);
		j1.setVisible(true);
		setVisible(true);
		check.addActionListener(this);
		back.addActionListener(this);
		exit.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == check){
			
			String idText = TextId.getText().trim();
			int i=-1;
			try{ i=Integer.parseInt(idText);}
			catch(Exception e){System.out.println("Wrong format !!!");}
		
		
			int index=this.pro.RemUsers(i);
			if(index==0){//index=0 means successful
			new Gui_SuccessRemove(this.pro,sequence1);}
			else{new Gui_FailtoRemove(this.pro,sequence1);}
			dispose();
		}
		else if(event.getSource() == back){
			new Gui_AdminFunction(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
