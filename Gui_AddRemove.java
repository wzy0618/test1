package system;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Calendar;
/**
 * 
 * @author Group009
 * @version 1.0
 */
public class Gui_AddRemove extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JLabel id;
	TextField TextId = new TextField(20);
	JLabel date;
	TextField TextDate = new TextField(20);
	JLabel addr;
	TextField TextAddr = new TextField(20);
	JButton check;
	JButton back;
	JButton exit;
	int sequence1;
	ProvSy pro;
/**
 * 
 * @param pro the whole system
 * @param sequence the number of administrator who has logged in
 */
	public Gui_AddRemove(Object pro,int sequence) {
		this.pro=(ProvSy) pro;
		sequence1 = sequence;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		j1.setLayout(new GridLayout(4,0));
		JPanel j2=new JPanel();
		j2.setLayout(new GridLayout(3,0));
		JPanel j3=new JPanel();
		j3.setLayout(new GridLayout(1,2));
		Label1 = new JLabel("Here is the information of the user which you add:");
		id = new JLabel("Resident_ID");
		date = new JLabel("Resident_Date");
		addr = new JLabel("Resident_Address");
		check = new JButton("Add");
		back = new JButton("Back");
		exit = new JButton("Exit");
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		j1.add(j2);
		j1.add(j3);
		j2.add(id);
		j2.add(date);
		j2.add(addr);
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
			
			
			int number=this.pro.AddUsers();
			Calendar test = Calendar.getInstance();
			int year = test.get(Calendar.YEAR);  
			int month = test.get(Calendar.MONTH)+1;  
			int date = test.get(Calendar.DATE);   
			String time=year+"/"+month+"/"+date;
			try{Meter.write("resource\\HouseholdInfo.txt",number+",1234,"+time+",0,");}
			catch(Exception e){}
			new Gui_successadd(this.pro,sequence1,number);
			//send number to Gui_Successadd 
			dispose();
		}
		else if(event.getSource() == back){
			new Gui_AdminFunction(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}

		
	}


}
