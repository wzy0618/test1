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
public class Gui_ResidentLog extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JLabel LabelId;
	JLabel LabelPass;
	TextField TextId = new TextField(20);
	TextField TextPass = new TextField(20);
	JButton check;
	JButton back;
	JButton Exit;
	int flagId = 0;
	int flagPass = 0;
	int sequence = 0;
	ProvSy pro;
/**
 * 
 * @param pro the whole system
 */
	public Gui_ResidentLog(Object pro) {
		this.pro=(ProvSy) pro;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel j1 = new JPanel();
		j1.setLayout(new GridLayout(3,1));
		
		Label1 = new JLabel("As a resident, please input your id and password:");
		LabelId = new JLabel("ID: ");
		LabelPass = new JLabel("Password: ");
		check = new JButton("Check");
		back = new JButton("Back");
		Exit = new JButton("Exit");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(Exit,BorderLayout.SOUTH);
		j1.add(LabelId);
		j1.add(TextId);
		j1.add(LabelPass);
		j1.add(TextPass);
		j1.add(check);
		j1.add(back);
		
		setVisible(true);
		j1.setVisible(true);
		check.addActionListener(this);
		back.addActionListener(this);
		Exit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		String idText = TextId.getText().trim();
		String passText = TextPass.getText().trim();
		
		if(event.getSource() == check){
			
			ArrayList<String> login = new ArrayList<String>();
			login=Meter.read("resource\\HouseholdInfo.txt");
			
			
			for(int i=0; i<login.size();i++){
				String[] dataFile = login.get(i).split(",");

				if(dataFile[0].equals(idText)==true){
					sequence = Integer.parseInt(dataFile[0]);// dataFile[sequence] stored i		
					if(dataFile[1].equals(passText)==true)
						flagPass=1;
				}
			}
			
		
			if(flagPass !=1){
				Label1.setText("Error! Either your id or password is wrong. Please try again:");
				TextId.setText("");
				TextPass.setText("");
			}
		
			else if(flagPass == 1){
				new Gui_ResidentFunction(this.pro,sequence);
				dispose();
			}
		}
		
		else if(event.getSource() == back){
			new Gui_welcome(this.pro);
			dispose();
		}
		
		else if(event.getSource() == Exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}

}

