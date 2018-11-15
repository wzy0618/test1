package system;

public class GasMeter extends Meter{
    

	public GasMeter(int household_id){
	super(household_id);
	this.type=1;//the type of meter
	this.gasrun=new GasRun(this.household_id,this.type);
	Thread thread=new Thread(this.gasrun);
	thread.start();//run the gas meter
}


}