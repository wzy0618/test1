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
public class Gui_Budget extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JButton set;
	JButton change;
//	JButton history;
	JButton back;
	int sequence1;
	ProvSy pro;
	/**
	 * 
	 * @param pro the whole system
	 * @param sequence the household_id of the user who has logged in
	 */
	public Gui_Budget(Object pro,int sequence) {
		this.pro=(ProvSy) pro;
		sequence1 = sequence;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		j1.setLayout(new GridLayout(2,0));
		
		Label1 = new JLabel("Please choose what you want:");
		set = new JButton("Set budget");
		change = new JButton("Change budget");
		back = new JButton("Back");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(back,BorderLayout.SOUTH);
		j1.add(set);
		j1.add(change);
	//	j1.add(history);
		j1.setVisible(true);
		setVisible(true);
		set.addActionListener(this);
		change.addActionListener(this);
//		history.addActionListener(this);
		back.addActionListener(this);
		
		//container.add(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == set){
			int a = 1;
			new Gui_BudgetSet(this.pro,a,this.sequence1);
			dispose();
		}
		else if(event.getSource() == change){
			int a = 2;
			new Gui_BudgetSet(this.pro,a,this.sequence1);
			dispose();
		}
		else if(event.getSource() == back){
			new Gui_ResidentFunction(this.pro,sequence1);
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
