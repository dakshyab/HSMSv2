package TableBind;

import java.util.List;
import java.util.ArrayList;
import Models.TransferModel;
import javax.swing.table.AbstractTableModel;

public class StocksTableBind extends AbstractTableModel {
	private String[] columns = {"Id","HospitalStore","PPE","PPEQty","Dressing","DressingQty","IVSupply","IVQty"};
		private List<TransferModel> listSendOrder = new ArrayList<TransferModel>();
        
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
            return listSendOrder.size();
        }

        public void setList(List<TransferModel> listSendOrder) 
        {
            this.listSendOrder = listSendOrder;
        }

        public Object getValueAt(int indexRow, int indexCol) 
        {
            switch (indexCol) 
            {
	            case 0:
	                return listSendOrder.get(indexRow).getID();
	            case 1:
	                return listSendOrder.get(indexRow).getStoreName();
	            case 2:
	                return listSendOrder.get(indexRow).getPPETYPE();
	            case 3: 
	                return listSendOrder.get(indexRow).getPPEQUANTITY();
	            case 4:
	                return listSendOrder.get(indexRow).getDRESSINGTYPE();
	            case 5: 
	                return listSendOrder.get(indexRow).getDRESSINGQUANTITY();
	            case 6: 
		            return listSendOrder.get(indexRow).getIVTYPE();
	            case 7: 
		            return listSendOrder.get(indexRow).getIVQUANTITY();
		        
	            default:
	                return null;
	            }
        }
}
