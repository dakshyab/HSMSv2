package Frames;

import java.awt.*;
import javax.swing.*;

import Models.PPEModel;
import TableInit.PPETable;

import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PPEAdd {

    public static void GUI(JTable ppeTable){

        // Main Frame Initialization
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainFrame.setSize(600,600);

        // Labels
        JLabel headingLabel = new JLabel("Add New Item !");
        headingLabel.setFont(new Font("Times New Roman",Font.BOLD,25));
        headingLabel.setSize(300,50);
        headingLabel.setLocation(200,50);
        mainFrame.add(headingLabel);
        
        // Making a Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,600,600);
        mainPanel.setBackground(Color.decode("#bdfffd"));
        mainPanel.setLayout(null);

        JLabel itemLabel = new JLabel("Item:");
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
        String items[] = {"Gloves","Aprons","Gowns","Visors"};
        JComboBox itemBox = new JComboBox(items);
        itemBox.setFont(new Font("Times New Roman",Font.PLAIN,25));
        itemBox.setSize(300,50);
        itemBox.setLocation(220,200);
        itemBox.setVisible(true);
        mainPanel.add(itemBox);

        // Text field for quantity
        JTextField quantityText = new JTextField();
        quantityText.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        quantityText.setSize(300,50);
        quantityText.setLocation(220,300);
        quantityText.setVisible(true);
        mainPanel.add(quantityText);

        JButton BtnAdd = new JButton("Add");
        BtnAdd.setLocation(150,450);
        BtnAdd.setSize(100,50);
        mainPanel.add(BtnAdd);

        JButton BtnCancel = new JButton("Cancel");
        BtnCancel.setLocation(350,450);
        BtnCancel.setSize(100,50);
        mainPanel.add(BtnCancel);

        mainFrame.add(mainPanel);
        mainFrame.setResizable(false);
		mainFrame.setTitle("Add PPE");
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

        //Button events
        BtnAdd.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
                PPEModel md = new PPEModel();
                md.setNAME(itemBox.getSelectedItem().toString());
                md.setQUANTITY(Integer.parseInt(quantityText.getText()));
    
                CRUD.FileCRUD crd = new CRUD.FileCRUD();
                try 
                {
                    crd.AddData(md);
                    JOptionPane.showMessageDialog(null, "Data Saved!", "Message ", JOptionPane.INFORMATION_MESSAGE);
                    //ManageStockFrame.GUI();
                    PPETable.PPETableData(ppeTable);
                    mainFrame.setVisible(false);
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