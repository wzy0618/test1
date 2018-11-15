package system;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * @author Group009
 * @version 2.0
 */
public class HouseSw implements Global{
 
 public int household_id;
 public Date setDate;
 public double EleBudget;
 public double GasBudget;
 public double GasTariff;
 public double EleTariff;
 public ArrayList<Meter> meter = new ArrayList<Meter>(2);

 public HouseSw(){}
 /**
  * 
  * @param household_id the unique household_id of the user
  */
 public HouseSw(int household_id){
	this.household_id=household_id;
	this.setDate=new Date(System.currentTimeMillis());

		File file=new File(Global.BudgetPath+this.household_id+"7"+".txt");
	         if(!file.exists()){
	try{
             file.createNewFile();
	Meter.writeFileContent(Global.BudgetPath,this.household_id,7,"9999999,0");
}
	catch(IOException e){
	 e.printStackTrace(); 
}
}//initialize the budget of electricity meter
		File file2=new File(Global.BudgetPath+this.household_id+"8"+".txt");
	         if(!file2.exists()){
	try{
             file2.createNewFile();
	Meter.writeFileContent(Global.BudgetPath,this.household_id,8,"9999999,0");
}
	catch(IOException e){
	 e.printStackTrace(); 
}
}//initialize the budget of gas meter
	String[] elebudget=Meter.readFile(Global.BudgetPath,this.household_id,7);//read the electricity budget from the file
	this.EleBudget=Double.parseDouble(elebudget[0]);
	String[] gasbudget=Meter.readFile(Global.BudgetPath,this.household_id,8);//read the gas budget from the file
	this.GasBudget=Double.parseDouble(gasbudget[0]);

	Boolean x=Meter.createFile(Global.ElePath,household_id,0);//create the file to store the historical data of electricity meter

	EleMeter elemeter=new EleMeter(household_id);//create the electricity meter of the user
	Boolean z=Meter.createFile(Global.GasPath,household_id,1);//create the file to store the historical data of gas meter

	GasMeter gasmeter=new GasMeter(household_id);//create the gas meter of the user

	if(x==true&&z==true)
	System.out.println("Create successful!");
	meter.add(elemeter);
	meter.add(gasmeter);

}
/**
 * 
 * @param budget the budget you want to set to the electricity meter
 * @param budgettype the type of the budget. 0 for consumption, 1 for cost.
 */
 public void setEleBudget(double budget,int budgettype){
	
	this.EleBudget=budget;
	try{FileOutputStream testfile = new FileOutputStream(Global.BudgetPath+this.household_id+"7"+".txt");	
	testfile.write(new String(budget+","+budgettype).getBytes());
	testfile.close();}
	catch(Exception e){ e.printStackTrace();}


}
 /**
  * 
  * @return the budget of the electricity meter
  */
 public double getEleBudget(){return this.EleBudget;}
 /**
  * 
  * @param budget the budget you want to set to the gas meter
  * @param budgettype the type of the budget. 0 for consumption, 1 for cost.
  */
 public void setGasBudget(double budget,int budgettype){

	this.GasBudget=budget;
	try{FileOutputStream testfile = new FileOutputStream(Global.BudgetPath+this.household_id+"8"+".txt");	
	testfile.write(new String(budget+","+budgettype).getBytes());
	testfile.close();}
	catch(Exception e){ e.printStackTrace();}


}
 /**
  * 
  * @return the budget of the gas meter
  */
 public double getGasBudget(){return this.GasBudget;}
/**
 * 
 * @return An Arraylist which stores all the historical data of electricity meter 
 */
 public ArrayList<String> viewAllElectricity(){
	ArrayList<String> history = new ArrayList<String>();
	ArrayList<String> output = new ArrayList<String>();

	String data=null;

	history=Meter.readFileHistory(Global.ElePath,this.household_id,0);
	for(int i=0;i<history.size();i++){
	StringBuilder result = new StringBuilder(); 

	String readings[] = history.get(i).split(",");
	result.append(readings[0]);
	result.append("        ");
	result.append("Consumption =");
	result.append(readings[2]);
	result.append("        ");
	result.append("Cost =");
	result.append(readings[4]);
	data=result.toString();
	output.add(data);

}

	return output;
}
/**
 * 
 * @return An Arraylist which stores all the historical data of gas meter 
 */
 public ArrayList<String> viewAllGas(){
	ArrayList<String> history = new ArrayList<String>();
	ArrayList<String> output = new ArrayList<String>();

	String data=null;

	history=Meter.readFileHistory(Global.GasPath,this.household_id,1);
	for(int i=0;i<history.size();i++){
	StringBuilder result = new StringBuilder(); 

	String readings[] = history.get(i).split(",");
	result.append(readings[0]);
	result.append("        ");
	result.append("Consumption =");
	result.append(readings[2]);
	result.append("        ");
	result.append("Cost =");
	result.append(readings[4]);
	data=result.toString();
	output.add(data);

}

	return output;
}
/**
 * 
 * @return An Arraylist which stores the hodiernal data of electricity meter
 */
 public ArrayList<String> viewDayElectricity(){
	ArrayList<String> output = new ArrayList<String>();
	String readings[]=Meter.readFile(Global.ElePathDay,this.household_id,3);
	StringBuilder result = new StringBuilder(); 
	String data=null;

	result.append(readings[0]);
	result.append("        ");
	result.append("Consumption =");
	result.append(readings[2]);
	result.append("        ");
	result.append("Cost =");
	result.append(readings[3]);
	data=result.toString();
	output.add(data);
	return output;

}
/**
 * 
 * @return An Arraylist which stores the hodiernal data of gas meter
 */
 public ArrayList<String> viewDayGas(){
	ArrayList<String> output = new ArrayList<String>();
	String readings[]=Meter.readFile(Global.GasPathDay,this.household_id,4);
	StringBuilder result = new StringBuilder(); 
	String data=null;

	result.append(readings[0]);
	result.append("        ");
	result.append("Consumption =");
	result.append(readings[2]);
	result.append("        ");
	result.append("Cost =");
	result.append(readings[3]);
	data=result.toString();
	output.add(data);
	return output;
}
/**
 * 
 * @return An Arraylist which stores the monthly historical data of electricity meter. If there is no data, return the Arraylist which contains null 
 */
 public ArrayList<String> viewMonthElectricity(){

	ArrayList<String> history =Meter.readFileHistory(Global.ElePath,this.household_id,0);
	ArrayList<String> output = new ArrayList<String>();
	String data=null;
	if(history.size()>0){
	String readings1[] = history.get(0).split(",");
	String time1[]=readings1[0].split("/");
	int month=Integer.parseInt(time1[1]);
	int year=Integer.parseInt(time1[0]);
	int consumption=0;
	double cost=0.0;

 	for(int i=0;i<history.size();i++){
	String readings[] = history.get(i).split(",");
	String time[]=readings[0].split("/");
	StringBuilder result = new StringBuilder(); 

	if(year==Integer.parseInt(time[0])&&month==Integer.parseInt(time[1])){//judge whether they are in the same month or not
	consumption+=Integer.parseInt(readings[2]);
	cost+=Double.parseDouble(readings[4]);
	if(i==history.size()-1){
	result.append(year+"/"+month);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
}
}
	else{

	result.append(year+"/"+month);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
	year=Integer.parseInt(time[0]);
	month=Integer.parseInt(time[1]);
	consumption=Integer.parseInt(readings[2]);
	cost=Double.parseDouble(readings[4]);
	if(i==history.size()-1){
	result.append(year+"/"+month);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
}
}

	
}
	return output;
}
	else{
	output.add(null);
	return output;
}
}
 /**
  * 
  * @return An Arraylist which stores the monthly historical data of gas meter. If there is no data, return the Arraylist which contains null
  */
 public ArrayList<String> viewMonthGas(){
	

	ArrayList<String> history =Meter.readFileHistory(Global.GasPath,this.household_id,1);
	ArrayList<String> output = new ArrayList<String>();
	String data=null;
	if(history.size()>0){
	String readings1[] = history.get(0).split(",");
	String time1[]=readings1[0].split("/");
	int month=Integer.parseInt(time1[1]);
	int year=Integer.parseInt(time1[0]);
	int consumption=0;
	double cost=0.0;

 	for(int i=0;i<history.size();i++){
	String readings[] = history.get(i).split(",");
	String time[]=readings[0].split("/");
	StringBuilder result = new StringBuilder(); 

	if(year==Integer.parseInt(time[0])&&month==Integer.parseInt(time[1])){//judge whether they are in the same month or not
	consumption+=Integer.parseInt(readings[2]);
	cost+=Double.parseDouble(readings[4]);
	if(i==history.size()-1){
	result.append(year+"/"+month);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
}
}
	else{

	result.append(year+"/"+month);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
	year=Integer.parseInt(time[0]);
	month=Integer.parseInt(time[1]);
	consumption=Integer.parseInt(readings[2]);
	cost=Double.parseDouble(readings[4]);
	if(i==history.size()-1){
	result.append(year+"/"+month);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
}
}

	
}
	return output;
}
	else{
	output.add(null);
	return output;
}

}
/**
 * 
 * @param day the date in format of year/month/day
 * @return the week number of the year
 */
 public int StringToWeek(String day){

	
	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");  
	Date date = null;  
	try {  
    date = (Date)format.parse(day);  
} catch (ParseException e) {  
    e.printStackTrace();  
}  

Calendar calendar = Calendar.getInstance();  
calendar.setFirstDayOfWeek(Calendar.MONDAY);  
calendar.setTime(date);  
int week=calendar.get(Calendar.WEEK_OF_YEAR);
return week;
}

/**
 * 
 * @return An Arraylist which stores the weekly historical data of electricity meter. If there is no data, return the Arraylist which contains null
 */
 public ArrayList<String> viewWeekElectricity(){
	

	ArrayList<String> history =Meter.readFileHistory(Global.ElePath,this.household_id,0);
	ArrayList<String> output = new ArrayList<String>();
	String data=null;
	if(history.size()>0){

	String readings1[] = history.get(0).split(",");
	String time1[]=readings1[0].split("/");

	int year=Integer.parseInt(time1[0]);
	int week=StringToWeek(readings1[0]);
	int consumption=0;
	double cost=0.0;

 	for(int i=0;i<history.size();i++){
	String readings[] = history.get(i).split(",");
	String time[]=readings[0].split("/");
	StringBuilder result = new StringBuilder(); 


	if(year==Integer.parseInt(time[0])&&week==StringToWeek(readings[0])){//judge whether they are in the same week or not
	consumption+=Integer.parseInt(readings[2]);
	cost+=Double.parseDouble(readings[4]);
	if(i==history.size()-1){
	result.append(year+"/"+week);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
}
	
}
	else{

	result.append(year+"/"+week);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
	year=Integer.parseInt(time[0]);
	week=StringToWeek(readings[0]);
	consumption=Integer.parseInt(readings[2]);
	cost=Double.parseDouble(readings[4]);
	if(i==history.size()-1){
	result.append(year+"/"+week);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
}
	
}

}

	return output;
}

	else{
	output.add(null);
	return output;
}
}
/**
 * 
 * @return An Arraylist which stores the weekly historical data of gas meter. If there is no data, return the Arraylist which contains null
 */
 public ArrayList<String> viewWeekGas(){
	

	ArrayList<String> history =Meter.readFileHistory(Global.GasPath,this.household_id,1);
	ArrayList<String> output = new ArrayList<String>();
	String data=null;
	if(history.size()>0){

	String readings1[] = history.get(0).split(",");
	String time1[]=readings1[0].split("/");

	int year=Integer.parseInt(time1[0]);
	int week=StringToWeek(readings1[0]);
	int consumption=0;
	double cost=0.0;

 	for(int i=0;i<history.size();i++){
	String readings[] = history.get(i).split(",");
	String time[]=readings[0].split("/");
	StringBuilder result = new StringBuilder(); 


	if(year==Integer.parseInt(time[0])&&week==StringToWeek(readings[0])){//judge whether they are in the same week or not
	consumption+=Integer.parseInt(readings[2]);
	cost+=Double.parseDouble(readings[4]);
	if(i==history.size()-1){
	result.append(year+"/"+week);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
}
	
}
	else{

	result.append(year+"/"+week);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
	year=Integer.parseInt(time[0]);
	week=StringToWeek(readings[0]);
	consumption=Integer.parseInt(readings[2]);
	cost=Double.parseDouble(readings[4]);
	if(i==history.size()-1){
	result.append(year+"/"+week);
	result.append("        ");
	result.append("Consumption ="+consumption);
	result.append("        ");
	result.append("Cost="+cost);
	data=result.toString();
	output.add(data);
}
	
}

}

	return output;
}

	else{
	output.add(null);
	return output;
}

}

}