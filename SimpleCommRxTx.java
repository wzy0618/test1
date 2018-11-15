
package system;


import java.io.*;
import java.util.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import gnu.io.*;
 
/**
 * 
 * @author Group009
 * @version 2.0
 */
public class SimpleCommRxTx  implements Runnable{

    static CommPortIdentifier portId;

    static CommPort com;

    static SerialPort ser;
    static int receive=-1;
    static int sum = 0;
    static int sum1 = 0;
    static double EleTariff = 0.027;//this value can be changed
    static double cost = 0.0;
    static char[] buttons=new char[4];
    static int a=0;
    static int b=0;
    static int c=0;
    static int d=0;
    static int binary=0;
    static int flag=0;
    static int num=0;
// receive:00001111,4 buttons all pressed; 00000001, button4 is pressed; 00001001, button1&4 are pressed
// receive: 1,+12; 2,+10; 3,+22; 4,+8; 5,+20; 6,+18; 7+30; 8+5; 9+17; 10+15; 11+27; 12+13; 13+25; 14+23; 15+35

    public void run() {
    	String eletariff[]=Meter.readFile(Global.TariffPath,0,1);//get the electricity tariff
    	EleTariff=Double.parseDouble(eletariff[eletariff.length-1]);
        try {
			// TODO: identify the COM port from Windows' control panel
            portId = CommPortIdentifier.getPortIdentifier("COM3");

            com = portId.open("MCS51COM", 2000);
            ser = (SerialPort)com;
			// Baud rate = 9600, Data bits = 8, 1 stop bit, Parity OFF
            ser.setSerialPortParams(9600, SerialPort.DATABITS_8, 
                                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (Exception e){
            e.printStackTrace(System.out);
        }

		/*
		// Wait for 1 second if 8051 needs time to initialize
        try { 
            Thread.sleep(1000);
        } catch (InterruptedException e){}
		*/
        
while(true){


 try {
	// Test RX: display first 4 chars received
   InputStream comIn = ser.getInputStream();
   receive= (byte)comIn.read();

	if(receive==4)
		a=(a+1)%2;
	if(receive==3)
		b=(b+1)%2;
	if(receive==2)
		c=(c+1)%2;
	if(receive==1)
		d=(d+1)%2;
	binary=a*1+b*2+c*4+d*8;
	
	while(comIn.available() == 0){
		     	// close the streams
     flag=0;
            Date dateMCU=new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd,HH:mm");

	    String day = sdf.format(dateMCU); 
            char[] dateInfo = day.toCharArray();

	    try {
	    OutputStream comOut = ser.getOutputStream();
	    
	    for(int i = 0; i<dateInfo.length; i++){
	    	System.out.print(dateInfo[i]);
		comOut.write(dateInfo[i]);		
	    Thread.sleep(20);
	    }


	    char space = ' ';
	    comOut.write(space);
	    Thread.sleep(20);

			// close the streams
            comOut.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }



	    if(binary==1){
		sum+=12;
	    }
	    else if(binary==2){
		sum+=10;
	    }
	    else if(binary==3){
		sum+=22;
	    }
	    else if(binary==4){
		sum+=8;
	    }
	    else if(binary==5){
		sum+=20;
	    }
	    else if(binary==6){
		sum+=18;
	    }
	    else if(binary==7){
		sum+=30;
            }
	    else if(binary==8){
		sum+=5;
	    }
	    else if(binary==9){
		sum+=17;
	    }
	    else if(binary==10){
		sum+=15;
	    }
	    else if(binary==11){
		sum+=27;
	    }
	    else if(binary==12){
		sum+=13;
	    }
	    else if(binary==13){
		sum+=25;
	    }
	    else if(binary==14){
		sum+=23;
	    }
	    else if(binary==15){
		sum+=35;
	    }
	    else{
		sum +=0;
            }
	    cost = sum*EleTariff;

	    System.out.println(sum+"$$$$"+cost);
	   
	    if(sum>9999){
	    	sum1=sum%10000;
	    	int judge=sum/10000;
	    	if(judge>num){
	    		num++;
	    		flag=1;
	    	}
	    	}
	    if(sum<=9999)
	    {
	    	sum1=sum;
	    }
	    String strReading = Integer.toString(sum1);

	    if(sum1<1000)
	    	strReading="0"+strReading;
	    if(sum1<100)
	    	strReading="0"+strReading;
	    if(sum1<10)
	    	strReading="0"+strReading;
	    
	    System.out.println(strReading+"^^^^^^^^");
	    char[] dataReading = strReading.toCharArray();

	    try {
            OutputStream comOut = ser.getOutputStream();
	    for(int i = 0; i<dataReading.length; i++){
		comOut.write(dataReading[i]);
		 
		Thread.sleep(20);
	    }
	    
	    comOut.write('*');
	    Thread.sleep(20);

            comOut.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
	    

	    if(binary==1){
		buttons[0] = '-';
		buttons[1] = '-';
		buttons[2] = '-';
		buttons[3] = '0';
	    }
	    else if(binary==2){
		buttons[0] = '-';
		buttons[1] = '-';
		buttons[2] = '0';
		buttons[3] = '-';
	    }
	    else if(binary==3){
		buttons[0] = '-';
		buttons[1] = '-';
		buttons[2] = '0';
		buttons[3] = '0';
	    }
	    else if(binary==4){
		buttons[0] = '-';
		buttons[1] = '0';
		buttons[2] = '-';
		buttons[3] = '-';
	    }
	    else if(binary==5){
		buttons[0] = '-';
		buttons[1] = '0';
		buttons[2] = '-';
		buttons[3] = '0';
	    }
	    else if(binary==6){
		buttons[0] = '-';
		buttons[1] = '0';
		buttons[2] = '0';
		buttons[3] = '-';
	    }
	    else if(binary==7){
		buttons[0] = '-';
		buttons[1] = '0';
		buttons[2] = '0';
		buttons[3] = '0';
            }
	    else if(binary==8){
		buttons[0] = '0';
		buttons[1] = '-';
		buttons[2] = '-';
		buttons[3] = '-';
	    }
	    else if(binary==9){
		buttons[0] = '0';
		buttons[1] = '-';
		buttons[2] = '-';
		buttons[3] = '0';
	    }
	    else if(binary==10){
		buttons[0] = '0';
		buttons[1] = '-';
		buttons[2] = '0';
		buttons[3] = '-';
	    }
	    else if(binary==11){
		buttons[0] = '0';
		buttons[1] = '-';
		buttons[2] = '0';
		buttons[3] = '0';
	    }
	    else if(binary==12){
		buttons[0] = '0';
		buttons[1] = '0';
		buttons[2] = '-';
		buttons[3] = '-';
	    }
	    else if(binary==13){
		buttons[0] = '0';
		buttons[1] = '0';
		buttons[2] = '-';
		buttons[3] = '0';
	    }
	    else if(binary==14){
		buttons[0] = '0';
		buttons[1] = '0';
		buttons[2] = '0';
		buttons[3] = '-';
	    }
	    else if(binary==15){
		buttons[0] = '0';
		buttons[1] = '0';
		buttons[2] = '0';
		buttons[3] = '0';
	    }
	    else{
		buttons[0] = '-';
		buttons[1] = '-';
		buttons[2] = '-';
		buttons[3] = '-';
            }
	    String content="";
	    for(int i=0;i<buttons.length;i++)
          	content+=buttons[i];
	    System.out.println("TX: "+content);
	    
	    try {
            OutputStream comOut = ser.getOutputStream();
	    for(int i =0; i<4;i++){
		comOut.write(buttons[i]);
		Thread.sleep(20);
	    }

	    comOut.write(',');
	    Thread.sleep(20);

	    String strCost = Double.toString(cost);
	    char[] dataCost = strCost.toCharArray();
	    for(int i = 0; i<dataCost.length; i++){
	    	if(i>5)
	    		break;
	    	
		comOut.write(dataCost[i]);
		Thread.sleep(20);
	    }
	    comOut.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
	    
	    try {
            OutputStream comOut = ser.getOutputStream();
            if(sum>200&&sum<400){
            	comOut.write('%');//budget level 1
            	Thread.sleep(20);
            }
            if(sum>400&&sum<600){
            	comOut.write('#');//budget level 2
            	Thread.sleep(20);
            }
            if(sum>600&&sum<800){
            	comOut.write('@');//budget level 3
            	Thread.sleep(20);
            }
            if(sum>800){
            	comOut.write('$');//budget level 4
            	Thread.sleep(20);
            }
            if(flag==1){
            	comOut.write('^');//reading out of range
            	Thread.sleep(150);
            }
            comOut.write('&');//second line
            Thread.sleep(20);
            comOut.close();
            }
	    catch(Exception e){
	    	 e.printStackTrace(System.out);
	    	 
	    }


	 
        try { 
            Thread.sleep(800);//Thread wait for 1 second

        } catch (InterruptedException e){}


	}//end of while
    comIn.close();
 } catch (Exception e) {
     e.printStackTrace(System.out);
 }
}
    }

}
