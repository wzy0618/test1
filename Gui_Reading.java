package system;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

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
public class Gui_Reading extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Label1;
	JLabel currentGasReading;
	JLabel gasreading;

	JLabel currentElecReading;
	JLabel elereading;

	JButton back;
	JButton exit;
	int sequence1;
	ProvSy pro;
	SimpleCommRxTx fake;
/**
 * 
 * @param pro the whole system
 * @param sequence the number of the administrator who has logged in
 */
	public Gui_Reading(Object pro,int sequence) {
		fake=new SimpleCommRxTx();
		Thread thread=new Thread(fake);
		thread.start();//run the electricity meter
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
		j1.setLayout(new GridLayout(2,2));
		JPanel j2 = new JPanel();
		j2.setLayout(new GridLayout(1,2));
		Label1 = new JLabel("This is the  readings and bills history of the consumer!");
		currentElecReading=new JLabel("Electricity Reading: ");
		
		elereading =new JLabel(Integer.toString(fake.sum1));
		currentGasReading=new JLabel("Gas Reading: ");
		gasreading =new JLabel(this.pro.GetGasMeter(this.pro.users.get(sequence1).household_id));


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
		j1.add(currentElecReading);
		j1.add(elereading);
		j1.add(currentGasReading);
		j1.add(gasreading);
		j2.add(back);
		j2.add(exit);
		j1.setVisible(true);
		setVisible(true);

		back.addActionListener(this);
		exit.addActionListener(this);
		//container.add(contentPane);
		Timer timer = new Timer();
		timer.schedule(new ShowTime(), 1, 1000);//refresh the reading every 5s

	}
	class ShowTime extends TimerTask {
		public void run() {
			//refresh
			elereading.setText(Integer.toString(fake.sum1));
			gasreading.setText(pro.GetGasMeter(pro.users.get(sequence1).household_id));
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		 if(event.getSource() == back){
			new Gui_CheckReading(this.pro,sequence1);
			dispose();
		}
		else if(event.getSource() == exit){
			dispose();
		}
		// TODO Auto-generated method stub
		
	}


}


