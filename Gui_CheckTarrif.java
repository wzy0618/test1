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
public class Gui_CheckTarrif extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JLabel elec;
	JLabel gas;
	JButton back;
	int sequence1;
	ProvSy pro;
/**
 * 
 * @param pro the whole system
 * @param sequence the household_id of the user who has logged in
 */
	public Gui_CheckTarrif(Object pro,int sequence) {
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
		j1.setLayout(new GridLayout(2,0));
		
		Label1 = new JLabel("Here's the current tarrif: ");
		double e=this.pro.getEleTariff();
		double g=this.pro.getGasTariff();
		elec = new JLabel("Electricity tariff: "+e+"p per kWh");
		gas = new JLabel("Gas tarrif: "+g+"p per kWh");
		
		back = new JButton("Back");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(back,BorderLayout.SOUTH);
		j1.add(elec);
		j1.add(gas);
		j1.setVisible(true);
		setVisible(true);
		back.addActionListener(this);
		
		//container.add(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == back){
			new Gui_ResidentFunction(this.pro,sequence1);
			dispose();
		}
		
		// TODO Auto-generated method stub
		
	}


}

