package TableBind;

import Models.IVModel;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import CRUD.*;
import Frames.EditFormIV;
import Frames.ManageStockFrame;

public class IVTableBind extends AbstractTableModel {
    private String[] columns = {"Id","Name","Quantity","DELETE","EDIT"};
		private List<IVModel> ivList = new ArrayList<IVModel>();
        
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
            return ivList.size();
        }

        public void setList(List<IVModel> dataList) 
        {
            this.ivList = dataList;
        }

        public Object getValueAt(int indexRow, int indexCol) 
        {
            switch (indexCol) 
            {
	            case 0:
	                return ivList.get(indexRow).getID();
	            case 1:
	                return ivList.get(indexRow).getNAME();
	            case 2:
	                return ivList.get(indexRow).getQUANTITY();
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
									fco.DeleteIVData(ivList.get(indexRow).getID());
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
	            			var id = ivList.get(indexRow).getID();
	            			try 
	            			{
								EditFormIV.GUI(ivList.get(indexRow).getID());
								ManageStockFrame.mainFrame.setEnabled(false);
							} catch (IOException e) {
								// TODO Auto-generated catch block
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
