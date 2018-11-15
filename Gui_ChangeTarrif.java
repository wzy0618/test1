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
public class Gui_ChangeTarrif extends JFrame implements ActionListener {

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
	JLabel successful;
	TextField TextElec = new TextField(20);
	JButton check;
	JButton back;
	JButton exit;
	JPanel j1;
	int sequence1;
	ProvSy pro;
	/**
	 * 
	 * @param pro the whole system
	 * @param sequence the number of the administrator who has logged in
	 */
	public Gui_ChangeTarrif(Object pro,int sequence) {
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
		j1 = new JPanel();
		j1.setLayout(new GridLayout(5,1));
		
		Label1 = new JLabel("Change the tarrif:");
		currentGas = new JLabel("Current gas tarrif: "+this.pro.getGasTariff()+"p per kWh");
		currentElec = new JLabel("Current electricity tarrif: "+this.pro.getEleTariff()+"p per kWh");
		newGas = new JLabel("New gas tarrif: ");
		newElec = new JLabel("New electricity tarrif: ");
		check = new JButton("OK");
		back = new JButton("Back");
		exit = new JButton("Exit");
		successful=new JLabel("Change Successfully!");
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(exit,BorderLayout.SOUTH);
		j1.add(currentGas);
		j1.add(currentElec);
		j1.add(newGas);
		j1.add(TextGas);
		j1.add(newElec);
		j1.add(TextElec);
		j1.add(check);
		j1.add(back);
		j1.setVisible(true);
		setVisible(true);
		check.addActionListener(this);
		back.addActionListener(this);
		exit.addActionListener(this);
		//container.add(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == check){
			int judge=0;//judge the whether the input is in correct format
			if(TextGas.getText().length()!=0){
				double gas;
				try{gas=Double.parseDouble(TextGas.getText());
				if(gas>0){
				this.pro.setGasTariff(gas);
				judge=1;}}
				catch(Exception e){System.out.println("Wrong format !!!");}
			
			}
			if(TextElec.getText().length()!=0){
				double ele;
				try{ele=Double.parseDouble(TextElec.getText());
				if(ele>0){
				this.pro.setEleTariff(ele);
				judge=1;}}
				catch(Exception e){System.out.println("Wrong format !!!");}
			}

			//Gui_View view = new Gui_View(sequence1);
			if((TextGas.getText().length()!=0||TextElec.getText().length()!=0)&&judge==1){
			TextElec.setText("");
			TextGas.setText("");
			contentPane.removeAll();
			contentPane.repaint();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			//Container container = getContentPane();
			//container.setLayout(new FlowLayout());
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			j1 = new JPanel();
			j1.setLayout(new GridLayout(5,1));
			
			Label1 = new JLabel("Change the tarrif:");
			currentGas = new JLabel("Current gas tarrif: "+this.pro.getGasTariff()+"p per kWh");
			currentElec = new JLabel("Current electricity tarrif: "+this.pro.getEleTariff()+"p per kWh");
			newGas = new JLabel("New gas tarrif: ");
			newElec = new JLabel("New electricity tarrif: ");
			check = new JButton("OK");
			back = new JButton("Back");
			exit = new JButton("Exit");
			successful=new JLabel("Change Successfully!");
			contentPane.add(j1,BorderLayout.CENTER);
			contentPane.add(Label1,BorderLayout.NORTH);
			contentPane.add(exit,BorderLayout.SOUTH);
			j1.add(currentGas);
			j1.add(currentElec);
			j1.add(newGas);
			j1.add(TextGas);
			j1.add(newElec);
			j1.add(TextElec);
			j1.add(check);
			j1.add(back);
			j1.add(successful);
			j1.setVisible(true);
			setVisible(true);
			check.addActionListener(this);
			back.addActionListener(this);
			exit.addActionListener(this);
		}
			else{
				contentPane.removeAll();
				contentPane.repaint();
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			//Container container = getContentPane();
			//container.setLayout(new FlowLayout());
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			j1 = new JPanel();
			j1.setLayout(new GridLayout(5,1));

			Label1 = new JLabel("Change the tarrif:");
			currentGas = new JLabel("Current gas tarrif: "+this.pro.getGasTariff()+"p per kWh");
			currentElec = new JLabel("Current electricity tarrif: "+this.pro.getEleTariff()+"p per kWh");
			newGas = new JLabel("New gas tarrif: ");
			newElec = new JLabel("New electricity tarrif: ");
			check = new JButton("OK");
			back = new JButton("Back");
			exit = new JButton("Exit");
			successful=new JLabel("Change Successfully!");
			contentPane.add(j1,BorderLayout.CENTER);
			contentPane.add(Label1,BorderLayout.NORTH);
			contentPane.add(exit,BorderLayout.SOUTH);
			j1.add(currentGas);
			j1.add(currentElec);
			j1.add(newGas);
			j1.add(TextGas);
			j1.add(newElec);
			j1.add(TextElec);
			j1.add(check);
			j1.add(back);
			j1.setVisible(true);
			setVisible(true);
			check.addActionListener(this);
			back.addActionListener(this);
			exit.addActionListener(this);
			//container.add(contentPane);}
		}
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
