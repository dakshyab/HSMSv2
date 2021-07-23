package Frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import CRUD.FileCRUD;
import Models.IVModel;
import TableInit.*;

public class EditFormIV extends ManageStockFrame{
    public static void GUI(int Id) throws IOException{
    	
    	IVModel pm = FileCRUD.IVDataGet(Id);

        // Main Frame Initialization
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainFrame.setSize(600,600);

        // Labels
        JLabel headingLabel = new JLabel("Edit IV !");
        headingLabel.setFont(new Font("Times New Roman",Font.BOLD,25));
        headingLabel.setSize(300,50);
        headingLabel.setLocation(250,50);
        mainFrame.add(headingLabel);
        
        // Making a Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,600,600);
        mainPanel.setBackground(Color.decode("#bdfffd"));
        mainPanel.setLayout(null);

        JLabel itemLabel = new JLabel("IV:");
        itemLabel.setFont(new Font("Times New Roman", Font.PLAIN,25));
        itemLabel.setSize(300,50);
        itemLabel.setLocation(100,200);
        mainPanel.add(itemLabel);
        
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Times New Roman", Font.PLAIN,25));
        quantityLabel.setSize(300,50);
        quantityLabel.setLocation(100,300);
        mainPanel.add(quantityLabel);
        

        // DropDown for Items
        String items[] = {"Needles","Syringes","Cannulas","Alcohol Swabs"};
        JComboBox itemsBox = new JComboBox(items);
        itemsBox.setFont(new Font("Times New Roman",Font.PLAIN,25));
        itemsBox.setSelectedItem(pm.getNAME());
        itemsBox.setSize(300,50);
        itemsBox.setLocation(220,200);
        itemsBox.setVisible(true);
        mainPanel.add(itemsBox);

        // Text field for quantity
        JTextField quantityText = new JTextField();
        quantityText.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        quantityText.setText(String.valueOf((pm.getQUANTITY())));
        quantityText.setSize(300,50);
        quantityText.setLocation(220,300);
        quantityText.setVisible(true);
        mainPanel.add(quantityText);

        JButton BtnSave = new JButton("Save");
        BtnSave.setLocation(150,450);
        BtnSave.setSize(100,50);
        mainPanel.add(BtnSave);

        JButton BtnCancel = new JButton("Cancel");
        BtnCancel.setLocation(350,450);
        BtnCancel.setSize(100,50);
        mainPanel.add(BtnCancel);

        mainFrame.add(mainPanel);
        mainFrame.setResizable(false);
		mainFrame.setTitle("Edit Item");
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

        BtnSave.addActionListener(new ActionListener()
        {
            @Override
			public void actionPerformed(ActionEvent arg0) 
			{
        		FileCRUD fcr = new FileCRUD();
        		IVModel md = new IVModel();
        		md.setID(Id);
				md.setNAME(itemsBox.getSelectedItem().toString());
				md.setQUANTITY(Integer.parseInt(quantityText.getText()));
      		
        		try 
				{
        			fcr.EditRecordIV(md);
            		JOptionPane.showMessageDialog(null, "Data edited!", "Message ", JOptionPane.INFORMATION_MESSAGE);
					mainFrame.setVisible(false);
					IVTable.IVTableData(tableIV);
                    ManageStockFrame.mainFrame.toFront();
                    ManageStockFrame.mainFrame.setEnabled(true);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
        });

        BtnCancel.addActionListener(actionEvent ->
        {
            mainFrame.setVisible(false);
            ManageStockFrame.mainFrame.toFront();
            ManageStockFrame.mainFrame.setEnabled(true);
        });
        
        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setVisible(false);
                ManageStockFrame.mainFrame.toFront();
                ManageStockFrame.mainFrame.setEnabled(true);
            }
            
        });
        
    }
}
