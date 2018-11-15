package system;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
/**
 * 
 * @author Group009
 * @version 3.0
 */
public class EleRun implements Runnable,Global{
public int household_id;
public int type;
public int sum;
public double cost;
public String reading;
public double EleTariff;
public String[] records;
public String position;//store the file path of meter
public int check=0;//1 for alert, 0 for not
public int level=0;// the level of consumption or cost

public EleRun(int household_id,int type){
	
	this.household_id=household_id;
	this.type=type;
	String Shid =Integer.toString(this.household_id);
	String Stype=Integer.toString(this.type+3);
	this.position=Shid+Stype;
	
	Calendar test = Calendar.getInstance();
	int year = test.get(Calendar.YEAR);  
	int month = test.get(Calendar.MONTH)+1;  
	int date = test.get(Calendar.DATE);   
	String Syear=Integer.toString(year);
	String Smonth=Integer.toString(month);
	String Sdate=Integer.toString(date);
	String T=Syear+"/"+Smonth+"/"+Sdate;

	Boolean x=Meter.createFile(Global.ElePathDay,this.household_id,this.type+3);//store the history data of the meter in a very day
	if(x==true){
	try{Meter.writeFileContent(Global.ElePathDay,this.household_id,this.type+3,T+",0,0,0");}//initialize the file that store the hodiernal data
	catch(Exception e){ e.printStackTrace();}
}

	String eletariff[]=Meter.readFile(Global.TariffPath,0,1);//get the electricity tariff
	this.EleTariff=Double.parseDouble(eletariff[eletariff.length-1]);
	this.records = Meter.readFile(Global.ElePathDay,this.household_id,this.type+3);

	this.reading=records[1];//the second of the array store the reading
	this.sum=Integer.parseInt(records[2]);//the third of the array store the usage of gas
	this.cost=Double.parseDouble(records[3]);//the fourth of the array store the cost

	if(!T.equals(records[0])){
		//store the historical data that not record in history
	try{Meter.writeFileContent(Global.ElePath,this.household_id,this.type,records[0] + ",@,"+this.sum+",$,"+this.cost+",;,");}
	catch(IOException e){  e.printStackTrace();}
	this.sum=0;
	this.cost=0;
	try{FileOutputStream testfile = new FileOutputStream("resource\\MeterReading\\EleMeterDay\\"+this.position+".txt");	
	testfile.write(new String(T+","+this.reading+","+this.sum+","+this.cost).getBytes());
	testfile.close();}
	catch(Exception e){ e.printStackTrace();}

}

}

public void run(){
	String eletariff[];//store the tariff that read from file
	String realTimeData;//store the full time
	String realDate;//store the current time 
	String budgets[];//store the budget
	double budget;
	int budgettype;
	
	//System.out.println("×ÓÏß³ÌID£º"+Thread.currentThread().getId());//output the id of new thread
	Calendar c = Calendar.getInstance();
	int second =1;
	int year = c.get(Calendar.YEAR);  
	int month = c.get(Calendar.MONTH)+1;  
	int date = c.get(Calendar.DATE);   
	int hour = c.get(Calendar.HOUR_OF_DAY);   
	int minute = c.get(Calendar.MINUTE);   
	int add;
	int IntRead;
	Random rand = new Random();
	realDate = year + "/" + month + "/" + date;//the current time 
	budgets=Meter.readFile(Global.BudgetPath,this.household_id,this.type+7);
	budget=Double.parseDouble(budgets[0]);//read the budget from file
	budgettype=Integer.parseInt(budgets[1]);
	if(budgettype==0){
	if(budget<=this.sum+getconsumption(realDate))
{this.check=1;}
	if(this.sum+getconsumption(realDate)>250)
		this.level=1;
	if(this.sum+getconsumption(realDate)>500)
		this.level=2;
	if(this.sum+getconsumption(realDate)>750)
		this.level=3;
	if(this.sum+getconsumption(realDate)>1000)
		this.level=4;
}
	if(budgettype==1){
	if(budget<=this.cost+getcost(realDate))
	{this.check=1;}
	if(this.cost+getcost(realDate)>250)
		this.level=1;
	if(this.cost+getcost(realDate)>500)
		this.level=2;
	if(this.cost+getcost(realDate)>750)
		this.level=3;
	if(this.cost+getcost(realDate)>1000)
		this.level=4;
}
	while(true){
	add=rand.nextInt(2);//the usage of gas in 30 seconds
	this.sum+=add;//add the usage to the sum of the day
	

	IntRead=Integer.parseInt(this.reading);
	IntRead=IntRead+add;
	IntRead=IntRead%10000;
	this.reading=Integer.toString(IntRead);

	if(IntRead<1000)
	this.reading="0"+this.reading;
	if(IntRead<100)
	this.reading="0"+this.reading;
	if(IntRead<10)
	this.reading="0"+this.reading;//turn the reading into 4-bit number


	while(second%5!=0){



	if(second==58&&minute==59&&hour==23){//store the whole usage of today

	try{Meter.writeFileContent(Global.ElePath,this.household_id,this.type,year + "/" + month + "/" + date + ",@,"+this.sum+",$,"+this.sum*this.EleTariff+",;,");}
	catch(IOException e){  e.printStackTrace();}
	
	this.sum=0;//clear the sum of tomorrow
	try {	Thread.currentThread().sleep(1000);}
		catch (InterruptedException e) {}
		second+=1;

}
	c = Calendar.getInstance();
	second = c.get(Calendar.SECOND);
	year = c.get(Calendar.YEAR);  
	month = c.get(Calendar.MONTH)+1;   
	date = c.get(Calendar.DATE);    
	hour = c.get(Calendar.HOUR_OF_DAY);   
	minute = c.get(Calendar.MINUTE);
}
	//jump out of the cycle
	String second2=Integer.toString(second);
	String minute2=Integer.toString(minute);
	if(second<10)
	second2="0"+second2;
	if(minute<10)
	minute2="0"+minute2;
	realTimeData=year + "/" + month + "/" + date + " " +hour + ":" +minute2 + ":" + second2;//the full time
	realDate = year + "/" + month + "/" + date;//current time

	eletariff=Meter.readFile(Global.TariffPath,0,1);//update the current tariff
	this.EleTariff=Double.parseDouble(eletariff[eletariff.length-1]);
	this.cost+=add*this.EleTariff;
	budgets=Meter.readFile(Global.BudgetPath,this.household_id,this.type+7);
	budget=Double.parseDouble(budgets[0]);//update the current budget
	budgettype=Integer.parseInt(budgets[1]);
	if(budgettype==0){
	if(budget<=this.sum+getconsumption(realDate))
	{this.check=1;}
	else{this.check=0;}
	
	if(this.sum+getconsumption(realDate)>250)
		this.level=1;
	if(this.sum+getconsumption(realDate)>500)
		this.level=2;
	if(this.sum+getconsumption(realDate)>750)
		this.level=3;
	if(this.sum+getconsumption(realDate)>1000)
		this.level=4;
}
	if(budgettype==1){
	if(budget<=this.cost+getcost(realDate))
	{this.check=1;}
	else{this.check=0;}
	
	if(this.cost+getcost(realDate)>250)
		this.level=1;
	if(this.cost+getcost(realDate)>500)
		this.level=2;
	if(this.cost+getcost(realDate)>750)
		this.level=3;
	if(this.cost+getcost(realDate)>1000)
		this.level=4;
}



	try{FileOutputStream testfile = new FileOutputStream("resource\\MeterReading\\EleMeterDay\\"+this.position+".txt");	
	testfile.write(new String(realDate+","+this.reading+","+this.sum+","+this.cost).getBytes());
	testfile.close();}
	catch(Exception e){ e.printStackTrace();}


	try {
	Thread.currentThread().sleep(1000);//system wait for a second
}
	catch (InterruptedException e) {
}
	second+=1;
}
}
/**
 * 
 * @param date the date of today
 * @return the sum of usage of today
 */
public int getconsumption(String date){


	ArrayList<String> history =Meter.readFileHistory(Global.ElePath,this.household_id,0);

	String time1[]=date.split("/");
	int month=Integer.parseInt(time1[1]);
	int year=Integer.parseInt(time1[0]);
	int consumption=0;
	for(int i=0;i<history.size();i++){
	String readings[] = history.get(i).split(",");
	String time[]=readings[0].split("/");

	if(year==Integer.parseInt(time[0])&&month==Integer.parseInt(time[1])){//judge whether they are in the same month or not
	consumption+=Integer.parseInt(readings[2]);

}

	
	
}
	return consumption;
}
/**
 * 
 * @param date the date of today
 * @return the sum of cost of today
 */
public double getcost(String date){


	ArrayList<String> history =Meter.readFileHistory(Global.ElePath,this.household_id,0);

	String time1[]=date.split("/");
	int month=Integer.parseInt(time1[1]);
	int year=Integer.parseInt(time1[0]);
	double cost=0.0;

 	for(int i=0;i<history.size();i++){
	String readings[] = history.get(i).split(",");
	String time[]=readings[0].split("/");

	if(year==Integer.parseInt(time[0])&&month==Integer.parseInt(time[1])){//judge whether they are in the same month or not
	cost+=Double.parseDouble(readings[4]);

}
	
}
		return cost;
}




}