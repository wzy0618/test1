package system;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
public class Gui_CheckConsumption extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JLabel currentGas;
	JLabel newGas;
	TextField TextGas = new TextField(20);
	JLabel currentElec;
	JLabel newElec;
	TextField TextElec = new TextField(20);
	JButton back;
	JButton exit;
	int sequence1;
	ProvSy pro;
	ArrayList<JButton>labelList=new ArrayList<JButton>();
/**
 * 
 * @param pro the whole system
 * @param sequence the number of the administrator who has logged in
 */
	public Gui_CheckConsumption(Object pro,int sequence) {
		this.pro=(ProvSy) pro;
		sequence1 = sequence;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setBounds(100, 100, 450, 300);
		//Container container = getContentPane();
		//container.setLayout(new FlowLayout());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		j1.setLayout(new GridLayout(5,5));
		JPanel j2 = new JPanel();
		j2.setLayout(new GridLayout(1,2));
		Label1 = new JLabel("Choose the user which you want to check !");
		//currentGas = new JLabel("Current gas tarrif: 3.88p per kWh");
		//currentElec = new JLabel("Current electricity tarrif: 14.60p per kWh");
		//newGas = new JLabel("New gas tarrif: ");
		//newElec = new JLabel("New electricity tarrif: ");
		int i=0;
		while(i<this.pro.users.size()){
			JButton user = new JButton(Integer.toString(this.pro.users.get(i).household_id));
			user.addActionListener(this);
			labelList.add(user);
			j1.add(user);
			i++;
}

		back = new JButton("Back");
		exit = new JButton("Exit");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(j2,BorderLayout.SOUTH);
		/*j1.add(currentGas);
		j1.add(currentElec);
		j1.add(newGas);
		j1.add(TextGas);
		j1.add(newElec);
		j1.add(TextElec);*/

		j2.add(back);
		j2.add(exit);
		j1.setVisible(true);
		setVisible(true);

		back.addActionListener(this);
		exit.addActionListener(this);
		//container.add(contentPane);
		
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		int i;
		for(i=0;i<labelList.size();i++){
					if(event.getSource()==labelList.get(i)){
					
		new Gui_HistoryBills(this.pro,i);
					dispose();
		}	
					
		}
		 if(event.getSource() == back){
			new Gui_AdminFunction(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
