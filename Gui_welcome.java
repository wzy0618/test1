package system;


import java.awt.BorderLayout;
import java.awt.EventQueue;
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
public class Gui_welcome extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JLabel Label2;
	JButton admin;
	JButton resident;
	JButton exit;
	ProvSy pro;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProvSy pro=new ProvSy();
					Gui_welcome frame = new Gui_welcome(pro);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

/**
 * 
 * @param pro the whole system
 */
	public Gui_welcome(Object pro) {
		this.pro=(ProvSy) pro;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//Container container = getContentPane();
		//container.setLayout(new FlowLayout());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel j1 = new JPanel();
		j1.setLayout(new GridLayout(3,0));
		
		Label1 = new JLabel("Welcome to the smart energy management and monitoring system!");
		Label2 = new JLabel("Please check your identity.");
		admin = new JButton("Administrator");
		resident = new JButton("Resident");
		exit = new JButton("Exit");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(exit,BorderLayout.SOUTH);
		j1.add(Label2);
		j1.add(admin);
		j1.add(resident);
		j1.setVisible(true);
		setVisible(true);
		admin.addActionListener(this);
		resident.addActionListener(this);
		exit.addActionListener(this);
		
		//container.add(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == admin){
			new Gui_AdminLog(this.pro);
			dispose();
		}
		else if(event.getSource() == resident){
			new Gui_ResidentLog(this.pro);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
