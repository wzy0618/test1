package system;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * 
 * @author Group009
 * @version 1.0
 */
public class Gui_HistoryBills extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JLabel consumption;
	JLabel cost;
	JButton back;
	JButton exit;
	String consData;
	String costData;
	String[] dataFile;
	int sequence1;
	String filePath;
	String household;
	JComboBox<String> showBox1 = new JComboBox<String>();
	JComboBox<String> showBox2 = new JComboBox<String>();
	ProvSy pro;
/**
 * 
 * @param pro the whole system
 * @param sequence the household_id of the user who has logged in
 */
	public Gui_HistoryBills(Object pro,int sequence) {
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
		j1.setLayout(new GridLayout(3,0));
		
		/*File file =new File("resource\\currentHousehold.txt");
		ReadData read = new ReadData();
		dataFile = read.readFile(file);
		int who = Integer.parseInt(dataFile[0]);
		HouseSw houseSw = new HouseSw(who);*/
		
		Label1 = new JLabel("Here are the history bills:");
		consData = "History bills of ELectricity: ";
		costData = "History bills of Gas: ";
		ArrayList<String> eleMonth = new ArrayList<String>();
		eleMonth = this.pro.users.get(sequence1).viewMonthElectricity();
		for(int i=0; i<eleMonth.size()-1; i++) { 
        	showBox1.addItem(eleMonth.get(i));
        }
		ArrayList<String> gasMonth = new ArrayList<String>();
		gasMonth =  this.pro.users.get(sequence1).viewMonthGas();
		for(int i=0; i<gasMonth.size()-1; i++) { 
        	showBox2.addItem(gasMonth.get(i));
        }
		consumption = new JLabel(consData);
		cost = new JLabel(costData);
		back = new JButton("Back");
		exit = new JButton("Exit");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(exit,BorderLayout.SOUTH);
		j1.setLayout(new GridLayout(5,0));
		j1.add(consumption);
		j1.add(showBox1);
		j1.add(cost);
		j1.add(showBox2);
		j1.add(back);
		showBox1.setVisible(true);
		showBox2.setVisible(true);
		j1.setVisible(true);
		setVisible(true);
		back.addActionListener(this);
		exit.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent event) {

		 if(event.getSource() == back){
			new Gui_CheckConsumption(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}

	
}