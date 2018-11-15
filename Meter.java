package system;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * 
 * @author Group009
 * @version 2.0
 */
public abstract class Meter implements Global
{

 public String reading;
 public int household_id;
 public int type;
 public double GasTariff;
 public double EleTariff;
 public EleRun elerun;
 public GasRun gasrun;

 public Meter(){}
 /**
  * 
  * @param household_id the household_id of new user
  */
 public Meter(int household_id){
	this.household_id=household_id;
	this.reading = "0000";
	
	
}
/**
 * 
 * @return the reading of the meter
 */
 public String getReading(){return this.reading;}


/**
 * 
 * @return tariff of the electricity meter 
 */
 public double getEleTariff(){return this.EleTariff;}
/**
 * 
 * @return tariff of the gas meter 
 */
 public double getGasTariff(){return this.GasTariff;}



/**
 * 
 * @param path the part of path of the file 
 * @param hhid the household_id of the user
 * @param type the meter type 
 * @return return true if create successfully, else return false
 */
  public static boolean createFile(String path,int hhid,int type){
	Boolean bool = false;
	 String Shid =Integer.toString(hhid);
	 String Stype=Integer.toString(type);
	 String filename=Shid+Stype;
         File file=new File(path+filename+".txt");
         if(!file.exists()){
	try{
             file.createNewFile();
		bool=true;
}
	catch(IOException e){
	 e.printStackTrace(); 
}
}
	return bool;
     }
  /**
   * 
   * @param path the part of path of the file 
   * @param hhid the household_id of the user
   * @param type the meter type 
   * @return return true if delete successfully, else return false
   */
    public static boolean delFile(String path,int hhid,int type){
        Boolean bool = false;
	String Shid =Integer.toString(hhid);
	 String Stype=Integer.toString(type);
	 String filename=Shid+Stype;

        File file  = new File(path+filename+".txt");
        try {
            if(file.exists()){
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
	 e.printStackTrace(); 
        }
        return bool;
    }

/**
 * 
 * @param path the part of path of the file 
 * @param hhid the household_id of the user
 * @param type the meter type 
 * @param newstr the content that you want to write into the file
 * @return return true if write successfully, else return false
 * @throws IOException the path may be not exist
 */
  	 public static boolean writeFileContent(String path,int hhid,int type,String newstr)throws IOException{
        Boolean bool = false;
    	 String Shid =Integer.toString(hhid);
	 String Stype=Integer.toString(type);
	 String filename=Shid+Stype;
	 String filepath=path+filename+".txt";

        String filein = newstr+"\r\n";//line feed of txt in windows
        String temp  = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//full path of file
            
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            
            //content of the file
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                
                buffer = buffer.append(System.getProperty("line.separator"));//line feed which can used in windows or linux system
            }
            buffer.append(filein);
            
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);

		char[] charArray =buffer.toString().toCharArray();
            pw.write(charArray);//turn to array of String

            pw.flush();//clear the buffer
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //close the file
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }
  	 /**
  	  * 
  	  * @param path the full path of the file 
  	  * @param newstr the content that you want to write into the file
  	  * @return return true if write successfully, else return false
  	  * @throws IOException the path may be not exist
  	  */
  	public static boolean write(String path,String newstr)throws IOException{
        Boolean bool = false;
    	 
	 String filepath=path;

        String filein = newstr+"\r\n";//line feed of txt in windows
        String temp  = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//full path of file
            
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            
            //content of the file
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                
                buffer = buffer.append(System.getProperty("line.separator"));//line feed which can used in windows or linux system
            }
            buffer.append(filein);
            
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);

		char[] charArray =buffer.toString().toCharArray();
            pw.write(charArray);//turn into array of String

            pw.flush();//clear the buffer
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //close the file
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }
/**
 * 
 * @param path path the part path of the file 
 * @param hhid the household_id of the user
 * @param type the meter type 
 * @return An array of all the content in file which is splitted by "," 
 */

	public static String[] readFile(String path,int hhid,int type)
	{  
	 String Shid =Integer.toString(hhid);
	 String Stype=Integer.toString(type);
	 String filename=Shid+Stype;
	 String filepath=path+filename+".txt";
	
	File file =new File(filepath); 
		
        StringBuilder result = new StringBuilder();  
        try {  
            BufferedReader br =new BufferedReader(new FileReader(file));  
            String s =null;  
            while((s =br.readLine()) != null) {
                //result.append(System.lineSeparator() +s);
                result.append(s);
            }
            br.close();  
        } catch (FileNotFoundException e){  
          e.printStackTrace();  
        } catch (IOException e){  
          e.printStackTrace();  
        }
        String reading = result.toString();
        String readings[] = reading.split(",");
        return readings;
    }  

/**
 * 
 * @param path the part of path of file you want to read
 * @param hhid the household_id of the user
 * @param type the meter type 
 * @return the Arraylist of every line in file
 */

	public static ArrayList<String> readFileHistory(String path,int hhid,int type)
	{  
	 String Shid =Integer.toString(hhid);
	 String Stype=Integer.toString(type);
	 String filename=Shid+Stype;
	 String filepath=path+filename+".txt";
	 ArrayList<String> history = new ArrayList<String>();
	File file =new File(filepath); 
		

        try {  
            BufferedReader br =new BufferedReader(new FileReader(file));  
            String s =null;  
            while((s =br.readLine()) != null) {
                //result.append(System.lineSeparator() +s);
                history.add(s);
            }
            br.close();  
        } catch (FileNotFoundException e){  
          e.printStackTrace();  
        } catch (IOException e){  
          e.printStackTrace();  
        }
              return history;
    }
	/**
	 * 
	 * @param path the full path of file you want to read
	 * @return the Arraylist of every line in file
	 */
	public static ArrayList<String> read(String path)
	{  

	 ArrayList<String> history = new ArrayList<String>();
	File file =new File(path); 
		

        try {  
            BufferedReader br =new BufferedReader(new FileReader(file));  
            String s =null;  
            while((s =br.readLine()) != null) {
                //result.append(System.lineSeparator() +s);
                history.add(s);
            }
            br.close();  
        } catch (FileNotFoundException e){  
          e.printStackTrace();  
        } catch (IOException e){  
          e.printStackTrace();  
        }
              return history;
    }
	
	

}


