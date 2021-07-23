package Frames;

import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;



public class MainFrame {
	
	public static JFrame mainFrame;
	
	public static void main(String[] args) {
		mainGUI();
	}

	public static void mainGUI() {

		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Left Panel
		JPanel LeftPanel = new JPanel();
		
		LeftPanel.setBackground(Color.decode("#17a7cf"));
		LeftPanel.setBounds(0,0,200,600);
		LeftPanel.setLayout(null);

		//Main Panel

		JLabel dt = new JLabel("Welcome to Hospital Stock Management System!!");
		JLabel dt1 = new JLabel("Created by Sabal Gautam.");
		JLabel image = new JLabel();
		LocalDate today = LocalDate.now();
		JLabel date = new JLabel("( "+today.toString()+" )");
	
		
		dt.setFont(new Font("Times New Roman", Font.BOLD, 16));
		dt.setLocation(220,20);
		dt.setSize(360,20);

		dt1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		dt1.setLocation(295,50);
		dt1.setSize(250,20);

		image.setLocation(240,120);
		image.setSize(300,300);
		image.setBorder(BorderFactory.createSoftBevelBorder(1));

		ImageIcon pic = new ImageIcon("HSMS.jpg");
		image.setIcon(pic);

		JPanel MainPanel = new JPanel();
		MainPanel.add(dt);
		MainPanel.add(dt1);
		MainPanel.add(image);
		MainPanel.setBackground(Color.decode("#bdfffd"));
		MainPanel.setBounds(200,0,200,600);
		MainPanel.setLayout(null);

		//buttons

		JButton BtnManageStock = new JButton("Manage Stock");
		BtnManageStock.setLocation(15,15);
		BtnManageStock.setSize(170,40);

		JButton BtnViewStock = new JButton("Current Stock");
		BtnViewStock.setLocation(15,55);
		BtnViewStock.setSize(170,40);

		JButton BtnSendStock = new JButton("Transfer Stock");
		BtnSendStock.setLocation(15,95);
		BtnSendStock.setSize(170,40);	
		

		//labels settings
		date.setLocation(50,150);
		date.setSize(200,60);
		date.setFont(new Font("Arial", Font.BOLD, 16));
		date.setForeground(Color.WHITE);
		
		//adding buttons to the panel
		LeftPanel.add(BtnManageStock);
		LeftPanel.add(BtnViewStock);
		LeftPanel.add(BtnSendStock);
		LeftPanel.add(date);
		

		//Button events
		BtnManageStock.addActionListener(actionEvent -> 
		{ 	
			 //ManagePpe mppe = new ManagePpe();
			ManageStockFrame.GUI();
			mainFrame.setVisible(false);
		});

		BtnSendStock.addActionListener(actionEvent -> 
		{ 	
			 //ManagePpe mppe = new ManagePpe();
			mainFrame.setVisible(false);
			ManageStockFrame.GUI();
			ManageStockFrame.mainFrame.setEnabled(false);
			//ManageStockFrame.mainFrame.toBack();
			TransferToStoreFrame.GUI();
			//TransferToStoreFrame.mainFrame.toFront();

		});

		BtnViewStock.addActionListener(actionEvent ->
		{
			mainFrame.setVisible(false);
			StockMonitoring.GUI();
		});


		//loading the main frame
		mainFrame.add(LeftPanel);

		mainFrame.add(MainPanel);
		mainFrame.setSize(600,600);
		mainFrame.setResizable(false);
		mainFrame.setTitle("Hospital Stock Management System");
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

}
