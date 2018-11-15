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
public class Gui_SuccessRemove extends JFrame implements ActionListener {

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
	 * @param sequence the number of the administrator who has logged in
	 */
	public Gui_SuccessRemove(Object pro,int sequence) {
		this.pro=(ProvSy) pro;
		sequence1 = sequence;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		Label1 = new JLabel("You have successfully removed a resident!");
		back = new JButton("Back");
		exit = new JButton("Exit");
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		j1.add(back);
		j1.add(exit);
		j1.setVisible(true);
		setVisible(true);
		back.addActionListener(this);
		exit.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == back){
			new Gui_remove(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
