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
public class Gui_ViewDaily extends JFrame implements ActionListener {

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
	 * @param a the type of historical data you want to view
	 * @param sequence the household_id of the user who has logged in
	 */
	public Gui_ViewDaily(Object pro,int a, int sequence) {
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
		

	
		int find=0;
		for(find=0;find<this.pro.users.size();find++){
		if(this.pro.users.get(find).household_id==sequence)
		break;
		}

		HouseSw houseSw = this.pro.users.get(find);
		
		Label1 = new JLabel("Here's your consumption and cost:");
		if(a==1){ //a==1 : daily electricity
			ArrayList<String> eleCurrent = new ArrayList<String>();
			eleCurrent = houseSw.viewDayElectricity();
			consData = eleCurrent.get(0);
			costData = " ";
		}
		else if(a==2){ //a==2 : daily gas
			ArrayList<String> gasCurrent = new ArrayList<String>();
			gasCurrent = houseSw.viewDayGas();
			consData = gasCurrent.get(0);
			costData = " ";
		}
		else if(a==3){ //a==3 : daily all
			//*************calculate consData/costData	
			consData = "Daily ELectricity: ";
			costData = "Daily Gas: ";
			ArrayList<String> eleDay = new ArrayList<String>();
			eleDay = houseSw.viewAllElectricity();
			for(int i=0; i<eleDay.size(); i++) { 
	        	showBox1.addItem(eleDay.get(i));
	        }
			ArrayList<String> gasDay = new ArrayList<String>();
			gasDay = houseSw.viewAllGas();
			for(int i=0; i<gasDay.size(); i++) { 
	        	showBox2.addItem(gasDay.get(i));
	        }
	  
		}
		else if(a==4){ //a==4 : weekly all
		//*************calculate consData/costData	
			consData = "Weekly ELectricity: ";
			costData = "Weekly Gas: ";
			ArrayList<String> eleWeek = new ArrayList<String>();
			eleWeek = houseSw.viewWeekElectricity();
			for(int i=0; i<eleWeek.size(); i++) { 
	        	showBox1.addItem(eleWeek.get(i));
	        }
			ArrayList<String> gasWeek = new ArrayList<String>();
			gasWeek = houseSw.viewWeekGas();
			for(int i=0; i<gasWeek.size(); i++) { 
	        	showBox2.addItem(gasWeek.get(i));
	        }
		}
		else if(a==5){ //a==5 : monthly all
			consData = "Monthly ELectricity: ";
			costData = "Monthly Gas: ";
			ArrayList<String> eleMonth = new ArrayList<String>();
			eleMonth = houseSw.viewMonthElectricity();
			for(int i=0; i<eleMonth.size(); i++) { 
	        	showBox1.addItem(eleMonth.get(i));
	        }
			ArrayList<String> gasMonth = new ArrayList<String>();
			gasMonth = houseSw.viewMonthGas();
			for(int i=0; i<gasMonth.size(); i++) { 
	        	showBox2.addItem(gasMonth.get(i));
	        }
			
		}
		
		consumption = new JLabel(consData);
		cost = new JLabel(costData);
		back = new JButton("Back");
		exit = new JButton("Exit");
		
		contentPane.add(j1,BorderLayout.CENTER);
		contentPane.add(Label1,BorderLayout.NORTH);
		contentPane.add(exit,BorderLayout.SOUTH);
		if(a==1||a==2){
			j1.add(consumption);
			j1.add(cost);
			j1.add(back);
		}
		else if(a==3||a==4||a==5){
			j1.setLayout(new GridLayout(5,0));
			j1.add(consumption);
			j1.add(showBox1);
			j1.add(cost);
			j1.add(showBox2);
			j1.add(back);
			showBox1.setVisible(true);
			showBox2.setVisible(true);
		}
		
		j1.setVisible(true);
		setVisible(true);
		back.addActionListener(this);
		exit.addActionListener(this);
		
		//container.add(contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == back){
			new Gui_View(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}
