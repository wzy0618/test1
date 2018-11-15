package system;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * @author Group009
 * @version 2.0
 */
public class ProvSy implements Global{


 public ArrayList<HouseSw> users = new ArrayList<HouseSw>();
 public double GasTariff;
 public double EleTariff;
 public int nextNumber;
 public String[] records;

 public ProvSy(){
	File file1=new File("resource\\MeterReading\\Users\\332.txt");
	         if(!file1.exists()){
	try{
             file1.createNewFile();
	Meter.writeFileContent(Global.UserPath,3,32,"0,");
}
	catch(IOException e){
	 e.printStackTrace(); 
}
}//if there is not a file to store users, create a file named "332.txt" and write "0," in this file
	         
	this.records = Meter.readFile(Global.UserPath,3,32);//store the historical users' household_id
	this.nextNumber=Integer.parseInt(records[0]);//store the household_id that will be allocated to new user
	  
	File file2=new File("resource\\MeterReading\\Tariff\\01.txt");
	         if(!file2.exists()){
	try{
             file2.createNewFile();
	Meter.writeFileContent(Global.TariffPath,0,1,"14.6,");//read the tariff of electricity meter
}
	catch(IOException e){
	 e.printStackTrace(); 
}
	
}
	String eletariff[]=Meter.readFile(Global.TariffPath,0,1);
	this.EleTariff=Double.parseDouble(eletariff[eletariff.length-1]);

	File file3=new File("resource\\MeterReading\\Tariff\\02.txt");
	         if(!file3.exists()){
	try{
             file3.createNewFile();
	Meter.writeFileContent(Global.TariffPath,0,2,"3.88,");//read the tariff of gas meter
}
	catch(IOException e){
	 e.printStackTrace(); 
}

}
	String gastariff[]=Meter.readFile(Global.TariffPath,0,2);
	this.GasTariff=Double.parseDouble(gastariff[gastariff.length-1]);

	int intHid=0;
	for(int i=1;i<this.records.length;i++){
	intHid=Integer.parseInt(this.records[i]);
	HouseSw OldUser=new HouseSw(intHid);
	this.users.add(OldUser);
}	//add the historical users to the system


	
}
 /**
  * 
  * @return the tariff of the gas meter
  */
 public double getGasTariff(){return this.GasTariff;}
 /**
  * 
  * @return the tariff of the electricity meter
  */
 public double getEleTariff(){return this.EleTariff;}
/**
 * 
 * @param tariff the tariff of the gas meter that you want to set
 */
 public void setGasTariff(double tariff){

	this.GasTariff=tariff;
	for(int i=0;i<users.size();i++){
	this.users.get(i).meter.get(1).GasTariff=tariff;
}//traverse all the user in system
	

	try{Meter.writeFileContent(TariffPath,0,2,tariff+",");}//update the gas tariff which is stored in file
	catch(Exception e){ e.printStackTrace();}
}
/**
 * 
 * @param tariff the tariff of the electricity meter that you want to set
 */
 public void setEleTariff(double tariff){

	this.EleTariff=tariff;
	for(int i=0;i<users.size();i++){
	this.users.get(i).meter.get(0).EleTariff=tariff;
}//traverse all the user in system


	try{Meter.writeFileContent(TariffPath,0,1,tariff+",");}//update the electricity tariff which is stored in file
	catch(Exception e){ e.printStackTrace();}
}


/**
 * 
 * @return household_id of the new user that you add 
 */
 public int AddUsers(){
	this.records = Meter.readFile(Global.UserPath,3,32);
	this.nextNumber+=1;
	HouseSw NewUser=new HouseSw(this.nextNumber);
	NewUser.EleTariff=this.EleTariff;
	NewUser.GasTariff=this.GasTariff;
	this.users.add(NewUser);
	
	String SnextNumber=Integer.toString(this.nextNumber);
	String tmp=null;
	StringBuffer buf = new StringBuffer();
	buf.append(SnextNumber);
	buf.append(",");
	for(int i=1;i<this.records.length;i++){
	buf.append(this.records[i]);
	buf.append(",");
}
	buf.append(this.nextNumber);
	buf.append(",");

	tmp = buf.toString(); 

	try{FileOutputStream testfile = new FileOutputStream("resource\\MeterReading\\Users\\332.txt");
	testfile.write(new String(tmp).getBytes());
	testfile.close();}
	catch(Exception e){ e.printStackTrace();}
	int j=this.nextNumber;
	return j;
}
/**
 * 
 * @param id the household_id of the user that you want to delete
 * @return return 1 if delete unsuccessfully, else return 0
 */
 public int RemUsers(int id){
	this.records = Meter.readFile(Global.UserPath,3,32);
	
	int i=0;
	int index=0;
	if(this.users.size()==0){
	System.out.println("Empty !!!");
	index=1;
	return index;
}
	for(i=0;i<this.users.size();i++){
	if(this.users.get(i).household_id==id)
	break;
	if(i==this.users.size()-1){
	System.out.println("No Such user !!!");
	index=1;
	return index;
}		
}
	String SnextNumber=Integer.toString(this.nextNumber);
	String Sid=Integer.toString(id);
	String tmp=null;
	StringBuffer buf = new StringBuffer();
	buf.append(SnextNumber);//store the household_id that will be allocated to new user 
	buf.append(",");
	for(int j=1;j<this.records.length;j++){
	if(this.records[j].equals(Sid))
	continue;
	
	buf.append(this.records[j]);
	buf.append(",");
}

	tmp = buf.toString(); 
	try{FileOutputStream testfile = new FileOutputStream("resource\\MeterReading\\Users\\332.txt");	
	testfile.write(new String(tmp).getBytes());
	testfile.close();}
	catch(Exception e){ e.printStackTrace();}


	this.users.remove(i);
	return index;
}
/**
 * 
 * @param id the household_id of the user whose electricity meter reading you want to read 
 * @return return null if there is no such user, else return the users' electricity meter reading
 */
 public String GetEleMeter(int id){
		
		int i=0;
		for(i=0;i<this.users.size();i++){
		if(this.users.get(i).household_id==id)
		break;
		if(i==this.users.size()-1){
		
		return null;	
	}		
	}
		return this.users.get(i).meter.get(0).elerun.reading;
		
	}
 /**
  * 
  * @param id the household_id of the user whose gas meter reading you want to read 
  * @return return null if there is no such user, else return the users' gas meter reading
  */
	 public String GetGasMeter(int id){
		
		int i=0;
		for(i=0;i<this.users.size();i++){
		if(this.users.get(i).household_id==id)
		break;
		if(i==this.users.size()-1){
	
		return null;	

	}		
	}
		return this.users.get(i).meter.get(1).gasrun.reading;
		
	}

/**
 * 
 * @param id the household_id of the user whose electricity budget you want to set
 * @param budget the electricity meter budget you want to set
 * @param budgettype the type of the budget. 0 for consumption, 1 for cost.
 */
 public void setEleBudget(int id,double budget,int budgettype){
	int i=0;
	for(i=0;i<this.users.size();i++){
	if(this.users.get(i).household_id==id)
	break;
	if(i==this.users.size()-1){
	System.out.println("No Such user !!!");
	return;	
}		
	
}
	this.users.get(i).setEleBudget(budget,budgettype);
}
 /**
  * 
  * @param id the household_id of the user whose gas budget you want to set
  * @param budget the gas meter budget you want to set
  * @param budgettype the type of the budget. 0 for consumption, 1 for cost.
  */
 public void setGasBudget(int id,double budget,int budgettype){
	int i=0;
	for(i=0;i<this.users.size();i++){
	if(this.users.get(i).household_id==id)
	break;
	if(i==this.users.size()-1){
	System.out.println("No Such user !!!");
	return;	
}		
	
}
	this.users.get(i).setGasBudget(budget,budgettype);
}


}
