package Frames;

import javax.swing.*;
import javax.swing.border.Border;

import TableInit.*;

import java.awt.*;

public class ManageStockFrame{
    
    public static JFrame mainFrame;

    public static JTable tablePPE; 
	public static JButton ppeAdd;
	
	public static JTable tableDressing; 
	public static JButton dressingAdd;
	
	public static JTable tableIV; 
	public static JButton ivAdd;

    //public static JTable tblItems;

    public static void GUI(){

        mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(1000,900);
        mainFrame.setTitle("Manage Stocks");
        mainFrame.setEnabled(true);
        mainFrame.setLocationRelativeTo(null);

        // Main display panel 
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.decode("#17a7cf"));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));       
        
        // bottom Panel
        JPanel parentPanel = new JPanel();
        parentPanel.setBackground(Color.decode("#17a7cf"));
        parentPanel.setLayout(new BorderLayout());

        
        parentPanel.add(centerPanel,BorderLayout.CENTER);
        
        //PPE panel
        JPanel ppePanel = new JPanel();
        ppePanel.setLayout(new BorderLayout());
        ppePanel.setBackground(Color.decode("#17a7cf"));

        Border bdrPPE = BorderFactory.createTitledBorder("PPE SUPPLIES");
        ppePanel.setBorder(bdrPPE);

        JLabel ppeLabel = new JLabel("PPE Table:");
        ppeLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));

        tablePPE = new JTable();
        PPETable objPPE = new PPETable();
        JTable tblPPE = objPPE.PPETableData(tablePPE);
        tblPPE.setRowHeight(30);
        tblPPE.setEnabled(false);

        JScrollPane ppeScrl = new JScrollPane(tblPPE);
        ppeAdd = new JButton("Add PPE");
        ppeAdd.addActionListener(actionEvent ->
        {
            PPEAdd.GUI(tablePPE);
            mainFrame.setEnabled(false);
        }
        ); 

        ppePanel.add(ppeScrl, BorderLayout.CENTER);
        ppePanel.add(ppeAdd, BorderLayout.NORTH);

        centerPanel.add(ppePanel);

        //Dressing Panel
        JPanel dressPanel = new JPanel();
        dressPanel.setLayout(new BorderLayout());
        dressPanel.setBackground(Color.decode("#17a7cf"));

        Border dressbdr = BorderFactory.createTitledBorder("DRESSING SUPPLIES");
        dressPanel.setBorder(dressbdr);

        tableDressing = new JTable();
        DRESSINGTable drs = new DRESSINGTable();
        JTable tblDRESSING = drs.DRESSINGTableData(tableDressing);
        tblDRESSING.setRowHeight(30);
        tblDRESSING.setEnabled(false);

        JScrollPane dressingScrl = new JScrollPane(tblDRESSING);
        dressingAdd = new JButton("Add Dressing");
        dressingAdd.addActionListener(actionEvent -> 
        {
             DressingAdd.GUI(tableDressing);
             mainFrame.setEnabled(false);
        });

        tblDRESSING.setEnabled(true);

		dressPanel.add(dressingScrl, BorderLayout.CENTER);
		dressPanel.add(dressingAdd, BorderLayout.NORTH);
        centerPanel.add(dressPanel);
        
        //IV Panel
        JPanel panelIV1 = new JPanel();
		  panelIV1.setLayout(new BorderLayout());
		  panelIV1.setBackground(Color.decode("#17a7cf"));

		  Border ivBorder = BorderFactory.createTitledBorder("IV SUPPLIES");
		  panelIV1.setBorder(ivBorder);
		  
		 
		  tableIV = new JTable();
		  IVTable objIV = new IVTable();
		  JTable tblIV = objIV.IVTableData(tableIV);
		  tblIV.setRowHeight(30);
		  tblIV.setEnabled(false);
		  
		  ivAdd = new JButton("Add IV");
		  JScrollPane ivScrollPane = new JScrollPane(tblIV); 
			
		  ivAdd.addActionListener(actionEvent -> 
		  {
			  	IVAdd.GUI(tableIV);
                mainFrame.setEnabled(false);
	      });
			
		  tblDRESSING.setEnabled(false);

		  panelIV1.add(ivScrollPane, BorderLayout.CENTER);
		  panelIV1.add(ivAdd, BorderLayout.NORTH);
		  centerPanel.add(panelIV1);

          mainFrame.add(parentPanel,BorderLayout.CENTER);
          mainFrame.setVisible(true);

        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setVisible(false);
                MainFrame.mainFrame.setVisible(true);
            }
            
        });
        
    }
    
}