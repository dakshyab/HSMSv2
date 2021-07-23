package TableInit;

import java.util.List;
import javax.swing.JTable;
import java.util.ArrayList;
import java.io.IOException;
import javax.swing.table.TableCellRenderer;
import operations.MouseListener;
import operations.ButtonRenderer;

import Models.PPEModel;
import TableBind.*;
import CRUD.FileCRUD;

public class PPETable {
    
	public static boolean btnClickPPEStatus = true;
 	public static JTable PPETableData(JTable ppeTbl) 
	{
		List<PPEModel> lstPPE = new ArrayList<PPEModel>();
		try 
		{
			var read = new FileCRUD();
			lstPPE = read.ViewDataPPE();
	     	if(lstPPE != null) 
			{
			 	var ppeTblBind = new PPETableBind();
			 	ppeTblBind.setList(lstPPE);
			 	ppeTbl.setModel(ppeTblBind);
		 	
		        TableCellRenderer renderBtn = new ButtonRenderer();
		        ppeTbl.getColumn("DELETE").setCellRenderer(renderBtn);
		        ppeTbl.getColumn("EDIT").setCellRenderer(renderBtn);
   
		        if(btnClickPPEStatus)
		        {
		        	ppeTbl.addMouseListener(new MouseListener(ppeTbl));
		        	btnClickPPEStatus = false;
		        }
			}
			else 
		 	{
				ppeTbl.setModel(new PPETableBind());
		 	}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
        return ppeTbl;
	}
}
