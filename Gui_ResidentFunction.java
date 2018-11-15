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
public class Gui_ResidentFunction extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JButton view;
	JButton budget;
	JButton tarrif;
	JButton warnings;
	JButton exit;
	int sequence1;
	ProvSy pro;
	
/**
 * 
 * @param pro the whole system
 * @param sequence the household_id of the user who has logged in
 */
	public Gui_ResidentFunction(Object pro,int sequence) {
		
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
		j1.setLayout(new GridLayout(4,0));
		
		Label1 = new JLabel("You have successfully logged in! Please choose what you want:");
		view = new JButton("View consumption and cost");
		budget = new JButton("Manage budget");
		tarrif = new JButton("Check tarrif");
		warnings = new JButton("Check warning");
		exit = new JButton("Exit");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(exit,BorderLayout.SOUTH);
		j1.add(view);
		j1.add(budget);
		j1.add(tarrif);
		j1.add(warnings);
		j1.setVisible(true);
		setVisible(true);
		view.addActionListener(this);
		budget.addActionListener(this);
		tarrif.addActionListener(this);
		warnings.addActionListener(this);
		exit.addActionListener(this);
		
		//container.add(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == view){
			new Gui_View(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == budget){
			new Gui_Budget(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == tarrif){
			new Gui_CheckTarrif(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == warnings){
			new Alert(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
