package system;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  
/**
 * 
 * @author Group009
 * @version 1.0
 */
public class Alert extends JFrame implements ActionListener { 
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JButton back;
public Color colors;
JFrame f1;
ProvSy pro;
int sequence1;
/**
 * 
 * @param pro the whole system
 * @param sequence the household_id of the user who has logged in
 */
public Alert (Object pro,int sequence) { 
this.pro=(ProvSy)pro;
sequence1=sequence;
int i=0;
for(i=0;i<this.pro.users.size();i++){
	 if(this.pro.users.get(i).household_id==sequence1)
	 break;
	} 

   JLabel Label;
   JLabel Label1;
   JPanel contentPane;
   
   f1 = new JFrame();
   f1.setTitle("Alert state");
   f1.setSize(400, 400);
   f1.setDefaultCloseOperation(EXIT_ON_CLOSE); 
   //judge the color of the light
   if((this.pro.users.get(i).meter.get(1).gasrun.check==1)||
  (this.pro.users.get(i).meter.get(0).elerun.check==1))
    colors=Color.red;
   else
    colors=Color.green;
   
   JPanel j1 = new JPanel();
   JPanel j2 = new newPanel();
   j1.setLayout(new GridLayout(2,0));
   Label1 = new JLabel("Here's the state of your warning: ");
   Label = new JLabel("Gas Alert Level: "+ this.pro.users.get(i).meter.get(1).gasrun.level+
		   "     Electricity Alert Level: " + this.pro.users.get(i).meter.get(0).elerun.level);//show the level
   back = new JButton("Back");
   back.addActionListener(this);
   
   j2.setVisible(true);
   j1.add(Label);
   j1.add(j2);
   j1.setVisible(true);
 
   contentPane = new JPanel();
   contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
   contentPane.setLayout(new BorderLayout(0, 0));
   contentPane.add(j1,BorderLayout.CENTER);
   contentPane.add(Label1,BorderLayout.NORTH);
   contentPane.add(back,BorderLayout.SOUTH);
   contentPane.setVisible(true);
   
   Container container = getContentPane();
   container.setLayout(new FlowLayout());
   container.setVisible(true);
   
   f1.add(contentPane);
   f1.setVisible(true);     
}

class newPanel extends JPanel {   
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected void paintComponent(Graphics g) {
  super.paintComponent(g);
   g.setColor(colors);  
   g.fillOval(145, 20, 50, 50);  
   g.drawOval(145, 20, 50, 50);
   
 }
}



//@Override
public void actionPerformed(ActionEvent event) { 
   if(event.getSource()== back){
    new Gui_ResidentFunction(this.pro,sequence1);
    f1.dispose();
   }
}
}