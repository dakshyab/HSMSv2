package TableBind;

import CRUD.*;
import Frames.EditFormDressing;
import Frames.ManageStockFrame;
import operations.*;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import javax.swing.JButton;
import Models.DRESSINGModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.AbstractTableModel;

public class DRESSINGTableBind extends AbstractTableModel {
	private String[] columns = {"Id","Name","Quantity","DELETE","EDIT"};
	private List<DRESSINGModel> dressingList = new ArrayList<DRESSINGModel>();
	
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
		return dressingList.size();
	}

	public void setList(List<DRESSINGModel> dataList) 
	{
		this.dressingList = dataList;
	}

	public Object getValueAt(int indexRow, int indexCol) 
	{
		switch (indexCol) 
		{
			case 0:
				return dressingList.get(indexRow).getID();
			case 1:
				return dressingList.get(indexRow).getNAME();
			case 2:
				return dressingList.get(indexRow).getQUANTITY();
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
								fco.DeleteDRESSINGData(dressingList.get(indexRow).getID());
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
						try {
							EditFormDressing.GUI(dressingList.get(indexRow).getID());
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
