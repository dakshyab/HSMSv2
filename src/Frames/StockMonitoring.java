package Frames;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



import CRUD.FileCRUD;
import TableBind.StocksTableBind;
import Models.TransferModel;

public class StockMonitoring {

    public static void GUI(){

        // Main Frame init
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        mainFrame.setSize(800,600);

        // Main Panel init
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,800,600);
        mainPanel.setBackground(Color.decode("#bdfffd"));
        mainPanel.setLayout(null);

        // Label init
        JLabel mainLabel = new JLabel("Stock Monitoring Table");
        mainLabel.setFont(new Font("Times New Roman",Font.PLAIN,25));
        mainLabel.setSize(300,50);
        mainLabel.setLocation(260,0);
        mainPanel.add(mainLabel);
        
        String msg = null;
		try {
			msg = FileCRUD.HospitalStock();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        JLabel label = new JLabel(msg);
		label.setFont(new Font("Arial", Font.PLAIN, 15));
		label.setForeground(Color.decode("#f54949"));
		label.setLocation(80,460);
		label.setSize(700, 20);
		mainPanel.add(label);
        

        // Table init
        
		  JTable stockTable = new JTable();
          stockTable.setRowHeight(30);
		  FileCRUD crd = new FileCRUD();
		  List<TransferModel> TransferHospitalModellst = new ArrayList<TransferModel>();
		  try {
			  TransferHospitalModellst =  crd.ViewTransferOrder();
		  	} catch (IOException e) {
		  		e.printStackTrace();
		  	}
		  
		  var stockstblBind = new StocksTableBind();
		  stockstblBind.setList(TransferHospitalModellst);
		  stockTable.setModel(stockstblBind);

        // Jscroll pane for the table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(stockTable);
        scrollPane.setBounds(100,50,600,400);
        stockTable.setEnabled(false);
        mainPanel.add(scrollPane);


        // Displaying on screen
        mainFrame.add(mainPanel);
        mainFrame.setResizable(false);
        mainFrame.setTitle("Stock Monitor");
        mainFrame.setLocationRelativeTo(null);
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
