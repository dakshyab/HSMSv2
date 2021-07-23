package TableBind;

import java.util.List;
import java.io.IOException;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.AbstractTableModel;

import CRUD.*;
import Models.PPEModel;
import Frames.EditFormPPE;

import Frames.*;


public class PPETableBind extends AbstractTableModel{
    
    		private String[] columns = {"Id","Name","Quantity","DELETE","EDIT"};
		private List<PPEModel> ppeList = new ArrayList<PPEModel>();
        
        public String getColumnName(int column) 
        {
            return columns[column];
        }

        public int getColumnCount() 
        {
            return columns.length;
        }
        
        public int getRowCount() 
        {
            return ppeList.size();
        }

        public void setList(List<PPEModel> dataList) 
        {
            this.ppeList = dataList;
        }

        public Object getValueAt(int indexRow, int indexCol) 
        {
            switch (indexCol) 
            {
	            case 0:
	                return ppeList.get(indexRow).getID();
	            case 1:
	                return ppeList.get(indexRow).getNAME();
	            case 2:
	                return ppeList.get(indexRow).getQUANTITY();
	            case 3: 
	            	final JButton btnDel = new JButton(columns[indexCol]);
	            	btnDel.addActionListener(new ActionListener() 
	            	{
	            		public void actionPerformed(ActionEvent arg) 
	            		{
	            			int dialog = JOptionPane.showConfirmDialog (null, "Would You Like to delete it?","Warning",JOptionPane.YES_NO_OPTION);
	            			if(dialog == JOptionPane.YES_OPTION)
	            			{
            					FileCRUD fco = new FileCRUD();
            					try 
            					{
									fco.DeletePPEData(ppeList.get(indexRow).getID());
								} catch (IOException ee) 
            					{
									ee.printStackTrace();
								}
	            			}
	            		}
	            	});
	            	return btnDel;
	            case 4: 
	            	final JButton btnEdit = new JButton(columns[indexCol]);
	            	btnEdit.addActionListener(new ActionListener() 
	            	{
	            		public void actionPerformed(ActionEvent arg) 
	            		{
	            			try 
	            			{
								EditFormPPE.GUI(ppeList.get(indexRow).getID());
								ManageStockFrame.mainFrame.setEnabled(false);
							} 
	            			catch (IOException e) 
	            			{
								e.printStackTrace();
							}
	            		}
	            	});

	            	return btnEdit;

	            default:
	                return null;
	            }
        }
}
