package TableInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import operations.*;
import CRUD.*;
import Models.IVModel;
import TableBind.IVTableBind;

public class IVTable {
    public static boolean btnClickIVStatus = true;
	public static JTable IVTableData(JTable ivTbl) 
	{
		List<IVModel> lstIV = new ArrayList<IVModel>();
		try 
		{
			var read = new FileCRUD();
			lstIV = read.ViewDataIV();
	     	if(lstIV != null) 
			{
	     		var ivTblBind = new IVTableBind();
			 	ivTblBind.setList(lstIV);
			 	ivTbl.setModel(ivTblBind);
			 	
			 	TableCellRenderer renderBtn = new ButtonRenderer();
		        ivTbl.getColumn("DELETE").setCellRenderer(renderBtn);
		        ivTbl.getColumn("EDIT").setCellRenderer(renderBtn);
		        
		        if(btnClickIVStatus)
		        {
		        	btnClickIVStatus = false;
		        	ivTbl.addMouseListener(new MouseListener(ivTbl));
			    }
			}
			else 
		 	{
				ivTbl.setModel(new IVTableBind());
		 	}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ivTbl;
	}
}
