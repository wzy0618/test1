package system;

public class EleMeter extends Meter{


	public EleMeter(int household_id){
	super(household_id);
	this.type=0;//the type of the meter
	this.elerun=new EleRun(this.household_id,this.type);
	Thread thread=new Thread(this.elerun);
	thread.start();//run the electricity meter

}

}