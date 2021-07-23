package operations;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTable;

public class MouseListener extends MouseAdapter 
{
        private final JTable tbl;
        public MouseListener(JTable table) 
        {
            this.tbl = table;
        }

        public void mouseClicked(MouseEvent e) 
        {
            int c = tbl.getColumnModel().getColumnIndexAtX(e.getX()); 
            int r = e.getY()/tbl.getRowHeight(); 

            if (r < tbl.getRowCount() && r >= 0 && c < tbl.getColumnCount() && c >= 0) 
            {
                Object val = tbl.getValueAt(r, c);
                if (val instanceof JButton) 
                {
                	((JButton)val).doClick();
                }
            }
        }
}