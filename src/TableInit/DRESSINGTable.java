package TableInit;

import operations.*;
import CRUD.FileCRUD;
import java.util.List;
import javax.swing.JTable;
import java.io.IOException;
import java.util.ArrayList;
import Models.DRESSINGModel;
import javax.swing.table.TableCellRenderer;

import TableBind.DRESSINGTableBind;
import TableBind.IVTableBind;

import Frames.*;

public class DRESSINGTable {
    public static boolean btnClickDressingStatus = true;
	public static JTable DRESSINGTableData(JTable dressingTbl) 
	{
		List<DRESSINGModel> lstDressing = new ArrayList<DRESSINGModel>();
		try 
		{
			var read = new FileCRUD();
			lstDressing = read.ViewDataDressing();
	     	if(lstDressing != null) 
			{
	     		var dressingTblBind = new DRESSINGTableBind();
	     		dressingTblBind.setList(lstDressing);
			 	dressingTbl.setModel(dressingTblBind);
			 	
			 	TableCellRenderer renderBtn = new ButtonRenderer();
		        dressingTbl.getColumn("DELETE").setCellRenderer(renderBtn);
		        dressingTbl.getColumn("EDIT").setCellRenderer(renderBtn);
	        
		        if(btnClickDressingStatus)
		        {
		        	btnClickDressingStatus = false;
		        	dressingTbl.addMouseListener(new MouseListener(dressingTbl));
		        }
			}
			else 
		 	{
				dressingTbl.setModel(new IVTableBind());
		 	}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
        return dressingTbl;
	}
}
