package CRUD;

import java.io.File;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import Models.DRESSINGModel;
import Models.IVModel;
import Models.PPEModel;
import Frames.MainFrame;
import Models.TransferModel;

import Frames.ManageStockFrame;
import TableInit.DRESSINGTable;
import TableInit.IVTable;
import TableInit.PPETable;

public class FileCRUD extends ManageStockFrame {
    
	//PPE
	//Read data for PPE LIST
    public static List<PPEModel> ViewDataPPE() throws IOException
	{
		File file = new File("PPE.txt");
		
		if(file.exists() == true) 
		{
			BufferedReader readerPPE = new BufferedReader(new FileReader(file));
			
			var listData = new ArrayList<PPEModel>();
			String rec;
			int i = 0;
			while((rec = readerPPE.readLine()) != null) 
			{
				if(i!=0) 
				{
					String valID = rec.split(",")[0];
					String valNAME = rec.split(",")[1];
                    String valQUANTITY = rec.split(",")[2];

					
					var obj = new PPEModel();
					obj.setID(Integer.parseInt(valID));
					obj.setNAME(valNAME);
					obj.setQUANTITY(Integer.parseInt(valQUANTITY));
					
					listData.add(obj);
				}
				i = 1;
			}
			readerPPE.close();
			return listData;
		}
		else 
		{
			return null;
		}
	}

	//get
	public static PPEModel PPEDataGet(int Id) throws IOException 
    {
 		BufferedReader readerFile = new BufferedReader( new FileReader("PPE.txt") );
 		var obj = new PPEModel();
 		String rec;
 		while((rec = readerFile.readLine()) != null) 
 		{
 			if(rec.split(",")[0].equals(String.valueOf(Id))) 
 			{
 				obj.setID(Integer.parseInt(rec.split(",")[0]));
 				obj.setNAME(rec.split(",")[1]);
 				obj.setQUANTITY(Integer.parseInt(rec.split(",")[2]));
 			}
 		}
 		readerFile.close();
 		return obj;
    }

	//Add PPE DATA

	public static void AddData(PPEModel model) throws IOException 
     {
	 	  String recordLine = "";
   	       File ff = new File("PPE.txt");
   	       boolean chkAddorUpd = true;
   	       if(ff.exists() == false) 
	 	   {
       	        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(ff));	
       	        buffWrite.write("Id,Name,Quantity");
       	        buffWrite.newLine();
       	        buffWrite.write("1,"+model.getNAME()+","+model.getQUANTITY());
       	        buffWrite.flush();
       	        buffWrite.newLine();
       	        buffWrite.close();	
	 	    }
	 	    else 
	 	    {
		        File tf = new File("temp.txt");
   		        BufferedWriter bwWriteNew = new BufferedWriter(new FileWriter(tf));	
   	 	    	BufferedReader buffRead = new BufferedReader(new FileReader(ff));
   	 	        
   	 	    	String data = "";
   	 	        while ((data = buffRead.readLine()) != null) 
             	{
   	 	        	String id = data.split(",")[0];
	     			String name = data.split(",")[1];
	     			String qty = data.split(",")[2];
	     			int newQty = 0;
     				if(name.equals(model.getNAME())) 
	     			{
     					newQty = Integer.parseInt(qty) + model.getQUANTITY();
     					chkAddorUpd = false;
     					bwWriteNew.write(id+","+name+","+newQty);
					}
     				else
     				{
     					bwWriteNew.write(data);
     				}
     			
     				recordLine = data;
     				bwWriteNew.flush();
     				bwWriteNew.newLine();
     			}

	     		int value = Integer.parseInt(recordLine.split(",")[0]) + 1; 
	     		String recId = Integer.toString(value);

			    if(chkAddorUpd) 
       	        {
	           	    bwWriteNew.write(recId + "," + model.getNAME() + "," + model.getQUANTITY());
	           	    bwWriteNew.flush();
	           	    bwWriteNew.newLine();
       	        }
			    
			    bwWriteNew.close(); 
	     		buffRead.close();
	     		ff.delete();
	     		tf.renameTo(ff);
	 	    }
     }

	 //DELETE PPE data
	 public static void DeletePPEData(int id) throws IOException
    {
   	 	File fileTemporary = new File("temp.txt");
		File myFile = new File("PPE.txt");
    		
    	BufferedReader readerFile = new BufferedReader( new FileReader( myFile ) );
    	BufferedWriter writerFile = new BufferedWriter( new FileWriter( fileTemporary ) );
    		
    	String rc;
    	int k = 0, countData = 0;
        while(( rc = readerFile.readLine()) != null ) 
    		{
    			String recordId = rc.split(",")[0];
    			String recFil = "";
    			countData++;
    			if(k!=0) 
    			{
        			if(recordId.equals(String.valueOf(id))) 
        			{
        				continue;
        			}
        			
        			recFil = String.valueOf(k)+","+rc.split(",")[1]+","+rc.split(",")[2];
      				writerFile.write(recFil);
        		}
    			else 
    			{
    				writerFile.write(rc);
    			}

    			k++;
    			writerFile.flush();
    			writerFile.newLine();
    		}
    		
    		readerFile.close();
    		writerFile.close();
    		if(countData == 2) 
    		{
    			fileTemporary.delete();
        	}
    		
    		myFile.delete();
    		
    		
    		if(countData != 2)
    		{
    			fileTemporary.renameTo(myFile);
    		}
    		
    		System.out.println("here");
    		
    		PPETable obj = new PPETable();
    		obj.PPETableData(tablePPE);
    }

    
	//Edit PPE Data
	public static void EditRecordPPE(PPEModel obj) throws IOException
    {
 		  File file = new File("PPE.txt");
 		  File temporaryFile = new File("temp.txt");
 		
 		  BufferedReader readerFile = new BufferedReader(new FileReader(file));
 		  BufferedWriter writerFile = new BufferedWriter(new FileWriter(temporaryFile));
 		  
 		  String rec,rec1;
 		  String nameEdit= "";
 		  while( (rec = readerFile.readLine()) != null) 
 		  {    			
 			  	String IDVal = rec.split(",")[0];
	  			if(String.valueOf(obj.getID()).equals(IDVal)) 
	  			{
	  				nameEdit = obj.getNAME();
	  				writerFile.write(IDVal+","+obj.getNAME()+","+obj.getQUANTITY());
	  			} 
	  			else 
	  			{
	  				writerFile.write(rec);	
	  			}    			
	  			writerFile.flush();
	  			writerFile.newLine();
 		  }
 		  writerFile.close();
 		  readerFile.close();
 		  file.delete();    		
 		  temporaryFile.renameTo(file);    	
		  
 		  
 		  
 		  File file1 = new File("PPE.txt");
 		  BufferedReader readerFile1 = new BufferedReader(new FileReader(file1));
		  
 		  File temporaryFile1 = new File("temp1.txt");
		  BufferedWriter writerFile1 = new BufferedWriter(new FileWriter(temporaryFile1));
 		  
		  int sumQty = 0, k = 0;
		  while( (rec1 = readerFile1.readLine() ) != null ) 
 		  {    	
	  		  if(rec1.split(",")[1].equals(nameEdit))
	  		  {
	  			  sumQty = sumQty + Integer.parseInt(rec1.split(",")[2]);
	  		  }
  			  else 
  			  {
  				  if(k == 0) 
  				  {
  					  writerFile1.write(rec1);
  				  }
  				  else 
  				  {
  					writerFile1.write(k+","+ rec1.split(",")[1] +","+rec1.split(",")[2]);
  				  }
  				  k++;
  				  writerFile1.flush();
  				  writerFile1.newLine();
  			  }
  		  }
 		  
		  writerFile1.write(k+","+nameEdit+","+sumQty);
		  writerFile1.flush();
		  writerFile1.newLine();
		  
		  writerFile1.close();
 		  readerFile1.close();
 		  file1.delete();
		  temporaryFile1.renameTo(file1);
    }



	
    //Dressing

	//Load Dressing data

	public static List<DRESSINGModel> ViewDataDressing() throws IOException
	{
		File file = new File("Dressing.txt");
		
		if(file.exists() == true) 
		{
			BufferedReader readerDRESSINGModel = new BufferedReader(new FileReader(file));
			
			var listData = new ArrayList<DRESSINGModel>();
			String rec;
			int i = 0;
			while((rec = readerDRESSINGModel.readLine()) != null) 
			{
				if(i!=0) 
				{
					String valID = rec.split(",")[0];
					String valNAME = rec.split(",")[1];
					String valQUANTITY = rec.split(",")[2];
					
					var obj = new DRESSINGModel();
					obj.setID(Integer.parseInt(valID));
					obj.setNAME(valNAME);
					obj.setQUANTITY(Integer.parseInt(valQUANTITY));
					
					listData.add(obj);
				}
				i = 1;
			}
			readerDRESSINGModel.close();
			return listData;
		}
		else 
		{
			return null;
		}
	}

	//GET dressing data
	
	public static DRESSINGModel DRESSINGDataGet(int Id) throws IOException 
    {
 		BufferedReader readerFile = new BufferedReader( new FileReader("Dressing.txt") );
 		var obj = new DRESSINGModel();
 		String rec;
 		while((rec = readerFile.readLine()) != null) 
 		{
 			if(rec.split(",")[0].equals(String.valueOf(Id))) 
 			{
 				obj.setID(Integer.parseInt(rec.split(",")[0]));
 				obj.setNAME(rec.split(",")[1]);
 				obj.setQUANTITY(Integer.parseInt(rec.split(",")[2]));
 			}
 		}
 		readerFile.close();
 		return obj;
    }

	

	//Add Dressing
	public static void AddData(DRESSINGModel model) throws IOException 
    {
	 	  String recordLine = "";
  	       File ff = new File("Dressing.txt");
  	       boolean chkAddorUpd = true;
  	       if(ff.exists() == false) 
	 	   {
      	        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(ff));	
      	        buffWrite.write("Id,Name,Quantity");
      	        buffWrite.newLine();
      	        buffWrite.write("1,"+model.getNAME()+","+model.getQUANTITY());
      	        buffWrite.flush();
      	        buffWrite.newLine();
      	        buffWrite.close();	
	 	    }
	 	    else 
	 	    {
		        File tf = new File("temp.txt");
  		        BufferedWriter bwWriteNew = new BufferedWriter(new FileWriter(tf));	
  	 	    	BufferedReader buffRead = new BufferedReader(new FileReader(ff));
  	 	        
  	 	    	String data = "";
  	 	        while ((data = buffRead.readLine()) != null) 
            	{
  	 	        	String id = data.split(",")[0];
	     			String name = data.split(",")[1];
	     			String qty = data.split(",")[2];
	     			int newQty = 0;
    				if(name.equals(model.getNAME())) 
	     			{
    					newQty = Integer.parseInt(qty) + model.getQUANTITY();
    					chkAddorUpd = false;
    					bwWriteNew.write(id+","+name+","+newQty);
					}
    				else
    				{
    					bwWriteNew.write(data);
    				}
    			
    				recordLine = data;
    				bwWriteNew.flush();
    				bwWriteNew.newLine();
    			}

	     		int value = Integer.parseInt(recordLine.split(",")[0]) + 1; 
	     		String recId = Integer.toString(value);

			    if(chkAddorUpd) 
      	        {
	           	    bwWriteNew.write(recId + "," + model.getNAME() + "," + model.getQUANTITY());
	           	    bwWriteNew.flush();
	           	    bwWriteNew.newLine();
      	        }
			    
			    bwWriteNew.close(); 
	     		buffRead.close();
	     		ff.delete();
	     		tf.renameTo(ff);
	 	    }
    }

	//EDIT dressing data
	public static void EditRecordDRESSING(DRESSINGModel obj) throws IOException
    {
 		  File file = new File("Dressing.txt");
 		  File temporaryFile = new File("temp.txt");
 		
 		  BufferedReader readerFile = new BufferedReader(new FileReader(file));
 		  BufferedWriter writerFile = new BufferedWriter(new FileWriter(temporaryFile));
 		  
 		  String rec,rec1;
 		  String nameEdit= "";
 		  while( (rec = readerFile.readLine()) != null) 
 		  {    			
 			  	String IDVal = rec.split(",")[0];
	  			if(String.valueOf(obj.getID()).equals(IDVal)) 
	  			{
	  				nameEdit = obj.getNAME();
	  				writerFile.write(IDVal+","+obj.getNAME()+","+obj.getQUANTITY());
	  			} 
	  			else 
	  			{
	  				writerFile.write(rec);	
	  			}    			
	  			writerFile.flush();
	  			writerFile.newLine();
 		  }
 		  writerFile.close();
 		  readerFile.close();
 		  file.delete();    		
 		  temporaryFile.renameTo(file);    	
		  
 		  File file1 = new File("Dressing.txt");
 		  BufferedReader readerFile1 = new BufferedReader(new FileReader(file1));
		  
 		  File temporaryFile1 = new File("temp1.txt");
		  BufferedWriter writerFile1 = new BufferedWriter(new FileWriter(temporaryFile1));
 		  
		  int sumQty = 0, k = 0;
		  while( (rec1 = readerFile1.readLine() ) != null ) 
 		  {    	
	  		  if(rec1.split(",")[1].equals(nameEdit))
	  		  {
	  			  sumQty = sumQty + Integer.parseInt(rec1.split(",")[2]);
	  		  }
  			  else 
  			  {
  				  if(k == 0) 
  				  {
  					  writerFile1.write(rec1);
  				  }
  				  else 
  				  {
  					writerFile1.write(k+","+ rec1.split(",")[1] +","+rec1.split(",")[2]);
  				  }
  				  k++;
  				  writerFile1.flush();
  				  writerFile1.newLine();
  			  }
  		  }
 		  
		  writerFile1.write(k+","+nameEdit+","+sumQty);
		  writerFile1.flush();
		  writerFile1.newLine();
		  
		  writerFile1.close();
 		  readerFile1.close();
 		  file1.delete();
		  temporaryFile1.renameTo(file1);
    	}


	//DELETE Dressing data
	public static void DeleteDRESSINGData(int id) throws IOException
	{
	   	 	File fileTemporary = new File("temp.txt");
			File myFile = new File("Dressing.txt");
	    		
	    	BufferedReader readerFile = new BufferedReader( new FileReader( myFile ) );
	    	BufferedWriter writerFile = new BufferedWriter( new FileWriter( fileTemporary ) );
	    		
	    	String rc;
	    	int k = 0, countData = 0;
	        while(( rc = readerFile.readLine()) != null ) 
	    		{
	    			String recordId = rc.split(",")[0];
	    			String recFil = "";
	    			countData++;
	    			if(k!=0) 
	    			{
	        			if(recordId.equals(String.valueOf(id))) 
	        			{
	        				continue;
	        			}
	        			
	        			recFil = String.valueOf(k)+","+rc.split(",")[1]+","+rc.split(",")[2];
	      				writerFile.write(recFil);
	        		}
	    			else 
	    			{
	    				writerFile.write(rc);
	    			}

	    			k++;
	    			writerFile.flush();
	    			writerFile.newLine();
	    		}
	    		
	    		readerFile.close();
	    		writerFile.close();
	    		if(countData == 2) 
	    		{
	    			fileTemporary.delete();
	        	}
	    		
	    		myFile.delete();
	    		
	    		
	    		if(countData != 2)
	    		{
	    			fileTemporary.renameTo(myFile);
	    		}

	    		DRESSINGTable dt = new DRESSINGTable();
	    		dt.DRESSINGTableData(tableDressing);
	}



	//IV
	//GetIV
	public static IVModel IVDataGet(int Id) throws IOException 
    {
 		BufferedReader readerFile = new BufferedReader( new FileReader("IV.txt") );
 		var obj = new IVModel();
 		String rec;
 		while((rec = readerFile.readLine()) != null) 
 		{
 			if(rec.split(",")[0].equals(String.valueOf(Id))) 
 			{
 				obj.setID(Integer.parseInt(rec.split(",")[0]));
 				obj.setNAME(rec.split(",")[1]);
 				obj.setQUANTITY(Integer.parseInt(rec.split(",")[2]));
 			}
 		}
 		readerFile.close();
 		return obj;
    }


	//ViewIV
	public static List<IVModel> ViewDataIV() throws IOException
	{
		File file = new File("IV.txt");
		
		if(file.exists() == true) 
		{
			BufferedReader readerIV = new BufferedReader(new FileReader(file));
			
			var listData = new ArrayList<IVModel>();
			String rec;
			int i = 0;
			while((rec = readerIV.readLine()) != null) 
			{
				if(i!=0) 
				{
					String valID = rec.split(",")[0];
					String valNAME = rec.split(",")[1];
					String valQUANTITY = rec.split(",")[2];
					
					var obj = new IVModel();
					obj.setID(Integer.parseInt(valID));
					obj.setNAME(valNAME);
					obj.setQUANTITY(Integer.parseInt(valQUANTITY));
					
					listData.add(obj);
				}
				i = 1;
			}
			readerIV.close();
			return listData;
		}
		else 
		{
			return null;
		}
	}

	//AddIVData
	public static void AddData(IVModel model) throws IOException 
    {
	 	  String recordLine = "";
  	       File ff = new File("IV.txt");
  	       boolean chkAddorUpd = true;
  	       if(ff.exists() == false) 
	 	   {
      	        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(ff));	
      	        buffWrite.write("Id,Name,Quantity");
      	        buffWrite.newLine();
      	        buffWrite.write("1,"+model.getNAME()+","+model.getQUANTITY());
      	        buffWrite.flush();
      	        buffWrite.newLine();
      	        buffWrite.close();	
	 	    }
	 	    else 
	 	    {
		        File tf = new File("temp.txt");
  		        BufferedWriter bwWriteNew = new BufferedWriter(new FileWriter(tf));	
  	 	    	BufferedReader buffRead = new BufferedReader(new FileReader(ff));
  	 	        
  	 	    	String data = "";
  	 	        while ((data = buffRead.readLine()) != null) 
            	{
  	 	        	String id = data.split(",")[0];
	     			String name = data.split(",")[1];
	     			String qty = data.split(",")[2];
	     			int newQty = 0;
    				if(name.equals(model.getNAME())) 
	     			{
    					newQty = Integer.parseInt(qty) + model.getQUANTITY();
    					chkAddorUpd = false;
    					bwWriteNew.write(id+","+name+","+newQty);
					}
    				else
    				{
    					bwWriteNew.write(data);
    				}
    			
    				recordLine = data;
    				bwWriteNew.flush();
    				bwWriteNew.newLine();
    			}

	     		int value = Integer.parseInt(recordLine.split(",")[0]) + 1; 
	     		String recId = Integer.toString(value);

			    if(chkAddorUpd) 
      	        {
	           	    bwWriteNew.write(recId + "," + model.getNAME() + "," + model.getQUANTITY());
	           	    bwWriteNew.flush();
	           	    bwWriteNew.newLine();
      	        }
			    
			    bwWriteNew.close(); 
	     		buffRead.close();
	     		ff.delete();
	     		tf.renameTo(ff);
	 	    }
    }

	//Edit IV

	public static void EditRecordIV(IVModel obj) throws IOException
    {
 		  File file = new File("IV.txt");
 		  File temporaryFile = new File("temp.txt");
 		
 		  BufferedReader readerFile = new BufferedReader(new FileReader(file));
 		  BufferedWriter writerFile = new BufferedWriter(new FileWriter(temporaryFile));
 		  
 		  String rec,rec1;
 		  String nameEdit= "";
 		  while( (rec = readerFile.readLine()) != null) 
 		  {    			
 			  	String IDVal = rec.split(",")[0];
	  			if(String.valueOf(obj.getID()).equals(IDVal)) 
	  			{
	  				nameEdit = obj.getNAME();
	  				writerFile.write(IDVal+","+obj.getNAME()+","+obj.getQUANTITY());
	  			} 
	  			else 
	  			{
	  				writerFile.write(rec);	
	  			}    			
	  			writerFile.flush();
	  			writerFile.newLine();
 		  }
 		  writerFile.close();
 		  readerFile.close();
 		  file.delete();    		
 		  temporaryFile.renameTo(file);    	
		  
 		  File file1 = new File("IV.txt");
 		  BufferedReader readerFile1 = new BufferedReader(new FileReader(file1));
		  
 		  File temporaryFile1 = new File("temp1.txt");
		  BufferedWriter writerFile1 = new BufferedWriter(new FileWriter(temporaryFile1));
 		  
		  int sumQty = 0, k = 0;
		  while( (rec1 = readerFile1.readLine() ) != null ) 
 		  {    	
	  		  if(rec1.split(",")[1].equals(nameEdit))
	  		  {
	  			  sumQty = sumQty + Integer.parseInt(rec1.split(",")[2]);
	  		  }
  			  else 
  			  {
  				  if(k == 0) 
  				  {
  					  writerFile1.write(rec1);
  				  }
  				  else 
  				  {
  					writerFile1.write(k+","+ rec1.split(",")[1] +","+rec1.split(",")[2]);
  				  }
  				  k++;
  				  writerFile1.flush();
  				  writerFile1.newLine();
  			  }
  		  }
 		  
		  writerFile1.write(k+","+nameEdit+","+sumQty);
		  writerFile1.flush();
		  writerFile1.newLine();
		  
		  writerFile1.close();
 		  readerFile1.close();
 		  file1.delete();
		  temporaryFile1.renameTo(file1);
    	}

	//Delete IV
	public static void DeleteIVData(int id) throws IOException
	{
	   	 	File fileTemporary = new File("temp.txt");
			File myFile = new File("IV.txt");
	    		
	    	BufferedReader readerFile = new BufferedReader( new FileReader( myFile ) );
	    	BufferedWriter writerFile = new BufferedWriter( new FileWriter( fileTemporary ) );
	    		
	    	String rc;
	    	int k = 0, countData = 0;
	        while(( rc = readerFile.readLine()) != null ) 
	    		{
	    			String recordId = rc.split(",")[0];
	    			String recFil = "";
	    			countData++;
	    			if(k!=0) 
	    			{
	        			if(recordId.equals(String.valueOf(id))) 
	        			{
	        				continue;
	        			}
	        			
	        			recFil = String.valueOf(k)+","+rc.split(",")[1]+","+rc.split(",")[2];
	      				writerFile.write(recFil);
	        		}
	    			else 
	    			{
	    				writerFile.write(rc);
	    			}

	    			k++;
	    			writerFile.flush();
	    			writerFile.newLine();
	    		}
	    		
	    		readerFile.close();
	    		writerFile.close();
	    		if(countData == 2) 
	    		{
	    			fileTemporary.delete();
	        	}
	    		
	    		myFile.delete();
	    		
	    		
	    		if(countData != 2)
	    		{
	    			fileTemporary.renameTo(myFile);
	    		}

	    		IVTable ivt = new IVTable();
	    		ivt.IVTableData(tableIV);
	    }


    //Delete data from current items
    public static void DeleteItemsData(int id) throws IOException
	{
	   	 	File fileTemporary = new File("temp.txt");
			File myFile = new File("ItemsData.txt");
	    		
	    	BufferedReader readerFile = new BufferedReader( new FileReader( myFile ) );
	    	BufferedWriter writerFile = new BufferedWriter( new FileWriter( fileTemporary ) );
	    		
	    	String rc;
	    	int k = 0, countData = 0;
	        while(( rc = readerFile.readLine()) != null ) 
	    		{
	    			String recordId = rc.split(",")[0];
	    			String recFil = "";
	    			countData++;
	    			if(k!=0) 
	    			{
	        			if(recordId.equals(String.valueOf(id))) 
	        			{
	        				continue;
	        			}
	        			
	        			recFil = String.valueOf(k)+","+rc.split(",")[1]+","+rc.split(",")[2];
	      				writerFile.write(recFil);
	        		}
	    			else 
	    			{
	    				writerFile.write(rc);
	    			}

	    			k++;
	    			writerFile.flush();
	    			writerFile.newLine();
	    		}
	    		
	    		readerFile.close();
	    		writerFile.close();
	    		if(countData == 2) 
	    		{
	    			fileTemporary.delete();
	        	}
	    		
	    		myFile.delete();
	    		
	    		
	    		if(countData != 2)
	    		{
	    			fileTemporary.renameTo(myFile);
	    		}

	    		PPETable it = new PPETable();
	    		//it.ItemsTableData(tableItems);
	    		//ItemsTable.ItemsTableData(tblItems);
	    }
    
    

	//STOCK

	//Send to Store
	public static boolean AddStoreOrderData(TransferModel model ,String[] hospitalStores) throws IOException 
    {
		   var listPPE =  ViewDataPPE();  	
		   var listDressing = ViewDataDressing(); 
		   var listiv = ViewDataIV(); 
		   
		   String recordLine = "";
   	       File ff = new File("SendStockData.txt");
   	       boolean chkAddorUpd = true;
   	       boolean chkValStock = false;
   	       for (PPEModel val : listPPE) 
   	       {  	
   	    	  if(model.getPPETYPE().equals(val.getNAME())) 
   	    	  {
   	    		  chkValStock = true;
   	    		  if(model.getPPEQUANTITY() > val.getQUANTITY()) 
   	    		  {
   	    			  return false; 
   	    		  }
   	    	  }
   	       }
   	      
   	       if(chkValStock == false) 
   	       {
   	    	   return false;
   	       }
   	      
   	       for (DRESSINGModel val : listDressing) 
	       {  	
	    	  if(model.getDRESSINGTYPE().equals(val.getNAME())) 
	    	  {
	    		  chkValStock = true;
	     	      if(model.getDRESSINGQUANTITY() > val.getQUANTITY()) 
	    		  {
	    			  return false;
	    		  }
	    	  }
	    	}
   	     
   	      if(chkValStock == false) 
	      {
	    	   return false;
	      }
	      
   	       
   	       for (IVModel val : listiv) 
	       {  	
	    	  if(model.getIVTYPE().equals(val.getNAME())) 
	    	  {
	    		  chkValStock = true;
	     	      if(model.getIVQUANTITY() > val.getQUANTITY()) 
	    		  {
	    			  return false;
	    		  }
	    	  }
	    	}
   	       
   	    if(chkValStock == false) 
	       {
	    	   return false;
	       }
	      
   	       
   	       if(ff.exists() == false) 
	 	   {
       	        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(ff));	
       	        buffWrite.write("Id,HospitalStore,PPE,PPEQty,Dressing,DressingQty,IVSupply,IVQty");
       	        buffWrite.newLine();
       	        
       	        	int i = 1;
        	       for (String value : hospitalStores) 
        	       {
        	    	   if(value.equals(model.getStoreName())) 
        	    	   {
        	    		   buffWrite.write(i+","+model.getStoreName()+","+model.getPPETYPE() + "," + model.getPPEQUANTITY() + 
      	        				"," + model.getDRESSINGTYPE() + ","+ model.getDRESSINGQUANTITY() + "," + model.getIVTYPE() + 
      	        				"," + model.getIVQUANTITY());
        	    	   }
        	    	   else 
        	    	   {
        	    		   buffWrite.write(i+","+value+",-,0,-,0,-,0");
           	    	   }
		      	       	
        	    	   buffWrite.flush();
		      	       buffWrite.newLine();
		      	       i++;
        	       }
        	       
       	        buffWrite.close();	
	 	    }
	 	    else 
	 	    {
		        File tf = new File("temp.txt");
   		        BufferedWriter bwWriteNew = new BufferedWriter(new FileWriter(tf));	
   	 	    	BufferedReader buffRead = new BufferedReader(new FileReader(ff));
   	 	        
   	 	    	String data = "";
   	 	        while ((data = buffRead.readLine()) != null) 
             	{
   	 	        	String id = data.split(",")[0];
	     			String hospitalStoreName = data.split(",")[1];
	     			
	     			String ppeqty = data.split(",")[3];
	     			String dressingqty = data.split(",")[5];
	     			String ivqty = data.split(",")[7];
	     			
	     			int ppeQuantity = 0;
	     			int dressingQuantity = 0;
	     			int ivQuantity = 0;
     				
	     			if(hospitalStoreName.equals(model.getStoreName())) 
	     			{
     					ppeQuantity = Integer.parseInt(ppeqty) + model.getPPEQUANTITY(); 
     					dressingQuantity = Integer.parseInt(dressingqty) + model.getDRESSINGQUANTITY(); 
     					ivQuantity = Integer.parseInt(ivqty) + model.getIVQUANTITY(); 

     					chkAddorUpd = false;
     					
     					bwWriteNew.write(id+","+model.getStoreName()+","+model.getPPETYPE() + "," + ppeQuantity + 
       	        				"," + model.getDRESSINGTYPE() + ","+ dressingQuantity + "," + model.getIVTYPE() + 
       	        				"," + ivQuantity);
					}
     				else
     				{
     					bwWriteNew.write(data);
     				}
     			
     				recordLine = data;
     				bwWriteNew.flush();
     				bwWriteNew.newLine();
     			}

	     		int value = Integer.parseInt(recordLine.split(",")[0]) + 1; 
	     		String recId = Integer.toString(value);

			    if(chkAddorUpd) 
       	        {
	           	    bwWriteNew.write(recId + "," + model.getStoreName()+","+model.getPPETYPE() + "," + model.getPPEQUANTITY() + 
   	        				"," + model.getDRESSINGTYPE() + ","+ model.getDRESSINGQUANTITY() + "," + model.getIVTYPE() + 
   	        				"," + model.getIVQUANTITY());
	           	    bwWriteNew.flush();
	           	    bwWriteNew.newLine();
       	        }
			    
			    bwWriteNew.close(); 
	     		buffRead.close();
	     		ff.delete();
	     		tf.renameTo(ff);
	 	    }
   	       

   	       File fppe = new File("PPE.txt");
   	       File fppetempFile = new File("tppeFile.txt");
	       BufferedReader brppe = new BufferedReader(new FileReader(fppe));
  	       BufferedWriter bwppe = new BufferedWriter(new FileWriter(fppetempFile));	
  	 
	 	   int l = 0,dc = 1;
	 	   String rec = "";
	 	   while ((rec = brppe.readLine()) != null) 
        	{
	 		   
	 	       if(l == 0) 
	 	       {
	 	        	bwppe.write(rec);
	 	        	bwppe.flush();
	 	        	bwppe.newLine();
	 	        }
  	 	        else 
  	 	        {
	   	 	        if(rec.split(",")[1].equals(model.getPPETYPE())) 
  	 	        	{
  	 	        		int ppeQty =  Integer.parseInt(rec.split(",")[2]) - model.getPPEQUANTITY();
  	 	        		if(ppeQty > 0) 
  	 	        		{
  	 	        			bwppe.write(l+","+model.getPPETYPE()+","+ppeQty);
  	 	        			bwppe.flush();
  	 	        			bwppe.newLine();
  	 	        		}
  	 	        		
  	 	        		if(ppeQty == 0) 
  	 	        		{
  	 	        			dc--;
  	 	        		}
  	 	        	}
  	 	        	else 
  	 	        	{
  	 	        		
  	 	        		bwppe.write(l+","+rec.split(",")[1]+","+rec.split(",")[2]);
  	 	        		bwppe.flush();
  	 	        		bwppe.newLine();
  	 	        	}
	   	 	        dc++;
	        		
  	 	        }
  	 	        l++;
        	}
	 	        
 	   		bwppe.close(); 
 	   		brppe.close();
 	   		fppe.delete();
 	        if(dc == 0) 
 	        {
 	        	fppetempFile.delete();
 	        }
 	        else 
 	        {
 	        	fppetempFile.renameTo(fppe);
 	        }
 	        
 	        
 	        
 	        
 	       File fdressing = new File("Dressing.txt");
   	       File fdressingtempFile = new File("tdressFile.txt");
	       BufferedReader brdressing = new BufferedReader(new FileReader(fdressing));
  	       BufferedWriter bwdressing = new BufferedWriter(new FileWriter(fdressingtempFile));	
  	 
	 	   int l1 = 0,dc1 = 1;
	 	   String rec1 = "";
	 	   while ((rec1 = brdressing.readLine()) != null) 
        	{
	 	       if(l1 == 0) 
	 	       {
	 	        	bwdressing.write(rec1);
	 	        	bwdressing.flush();
	 	        	bwdressing.newLine();
	 	        }
  	 	        else 
  	 	        {
	   	 	        if(rec1.split(",")[1].equals(model.getDRESSINGTYPE())) 
  	 	        	{
  	 	        		int dressingQty =  Integer.parseInt(rec1.split(",")[2]) - model.getDRESSINGQUANTITY();
  	 	        		if(dressingQty > 0) 
  	 	        		{
  	 	        			bwdressing.write(l1+","+model.getDRESSINGTYPE()+","+dressingQty);
  	 	        			bwdressing.flush();
  	 	        			bwdressing.newLine();
  	 	        		}
  	 	        		
  	 	        		if(dressingQty == 0) 
  	 	        		{
  	 	        			dc1--;
  	 	        		}
  	 	        	}
  	 	        	else 
  	 	        	{
  	 	        		
  	 	        		bwdressing.write(l+","+rec1.split(",")[1]+","+rec1.split(",")[2]);
  	 	        		bwdressing.flush();
  	 	        		bwdressing.newLine();
  	 	        	}
	   	 	        dc1++;
	        		
  	 	        }
  	 	        l1++;
        	}
	 	        
 	   		bwdressing.close(); 
 	   		brdressing.close();
 	   		fdressing.delete();
 	        if(dc1 == 0) 
 	        {
 	        	fdressingtempFile.delete();
 	        }
 	        else 
 	        {
 	        	fdressingtempFile.renameTo(fdressing);
 	        }
 	 
 	        
 	       File fiv = new File("IV.txt");
   	       File fivtempFile = new File("tivFile.txt");
	       BufferedReader briv = new BufferedReader(new FileReader(fiv));
  	       BufferedWriter bwiv = new BufferedWriter(new FileWriter(fivtempFile));	
  	 
  	       
	 	   int l2 = 0,dc2 = 1;
	 	   String rec2 = "";
	 	   while ((rec2 = briv.readLine()) != null) 
        	{
	 	       if(l2 == 0) 
	 	       {
	 	        	bwiv.write(rec2);
	 	        	bwiv.flush();
	 	        	bwiv.newLine();
	 	        }
  	 	        else 
  	 	        {
	   	 	        if(rec2.split(",")[1].equals(model.getIVTYPE())) 
  	 	        	{
  	 	        		int ivQty =  Integer.parseInt(rec2.split(",")[2]) - model.getIVQUANTITY();
  	 	        		if(ivQty > 0) 
  	 	        		{
  	 	        			bwiv.write(l2+","+model.getIVTYPE()+","+ivQty);
  	 	        			bwiv.flush();
  	 	        			bwiv.newLine();
  	 	        		}
  	 	        		
  	 	        		if(ivQty == 0) 
  	 	        		{
  	 	        			dc2--;
  	 	        		}
  	 	        	}
  	 	        	else 
  	 	        	{
  	 	        		
  	 	        		
  	 	        		bwiv.write(l2+","+rec2.split(",")[1]+","+rec2.split(",")[2]);
  	 	        		bwiv.flush();
  	 	        		bwiv.newLine();
  	 	        	}
	   	 	        dc2++;
  	 	        }
  	 	        l2++;
        	}
	 	        
 	   		bwiv.close(); 
 	   		briv.close();
 	   		fiv.delete();
 	        if(dc2 == 0) 
 	        {
 	        	fivtempFile.delete();
 	        }
 	        else 
 	        {
 	        	fivtempFile.renameTo(fiv);
 	        }
 	        
 	        
 	        return true;
     }
	
    //LOAD data for stock monitoring
    public static List<TransferModel> ViewTransferOrder() throws IOException
	{
		File file = new File("SendStockData.txt");
		
		
		
		if(file.exists() == true) 
		{
			BufferedReader readerTransferHospital = new BufferedReader(new FileReader(file));
			
			var listData = new ArrayList<TransferModel>();
			String rec;
			int l = 0;
			while((rec = readerTransferHospital.readLine()) != null) 
			{
				if(l!=0) 
				{
					var tm = new TransferModel();
					
					tm.setID(Integer.parseInt(rec.split(",")[0]));
					
					tm.setStoreName(rec.split(",")[1]);
					
					tm.setPPETYPE(rec.split(",")[2]);
					tm.setPPEQUANTITY(Integer.parseInt(rec.split(",")[3]));
					
					tm.setDRESSINGTYPE(rec.split(",")[4]);
					tm.setDRESSINGQUANTITY(Integer.parseInt(rec.split(",")[5]));
					
					tm.setIVTYPE(rec.split(",")[6]);
					tm.setIVQUANTITY(Integer.parseInt(rec.split(",")[7]));
					listData.add(tm);
				}
				l = 1;
			}
			readerTransferHospital.close();
			return listData;
		}
		else 
		{
			return null;
		}
	}

	
    
    //Load and analyze data for stock monitoring
	public static String HospitalStock() throws IOException
	{
		File file = new File("SendStockData.txt");
		if(file.exists() == true) 
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String rec, msg = "";
			int i = 0;
			while((rec = reader.readLine()) != null) 
			{
				if(i!=0) 
				{
					String ID = rec.split(",")[0];
					String StoreName = rec.split(",")[1];
					
					String PpeQty = rec.split(",")[3];
					String DressingQty = rec.split(",")[5];
					String IVQty = rec.split(",")[7];
					
					if(Integer.valueOf(DressingQty) < 200 && Integer.valueOf(IVQty) < 200 && Integer.valueOf(PpeQty) < 200)
					{
						msg = msg + "Current stocks low for "+StoreName +  "|";
					}
					
				}
				i = 1;
			}
			reader.close();
			System.out.println(msg);
			
			return msg;
		}
		else 
		{
			return null;
		}
	}
}
