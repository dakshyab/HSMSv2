package Frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import CRUD.FileCRUD;
import Models.PPEModel;
import TableInit.PPETable;


public class EditFormPPE extends ManageStockFrame{
    public static void GUI(int Id) throws IOException{
    	
    	PPEModel pm = FileCRUD.PPEDataGet(Id);

        // Main Frame Initialization
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainFrame.setSize(600,600);

        // Labels
        JLabel headingLabel = new JLabel("Edit PPE !");
        headingLabel.setFont(new Font("Times New Roman",Font.BOLD,25));
        headingLabel.setSize(300,50);
        headingLabel.setLocation(250,50);
        mainFrame.add(headingLabel);
        
        // Making a Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0,0,600,600);
        mainPanel.setBackground(Color.decode("#bdfffd"));
        mainPanel.setLayout(null);

        JLabel itemLabel = new JLabel("PPE:");
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
        String ppe[] = {"Gloves","Aprons","Gowns","Visors"};
        JComboBox ppeBox = new JComboBox(ppe);
        ppeBox.setFont(new Font("Times New Roman",Font.PLAIN,25));
        ppeBox.setSelectedItem(pm.getNAME());
        ppeBox.setSize(300,50);
        ppeBox.setLocation(220,200);
        ppeBox.setVisible(true);
        mainPanel.add(ppeBox);

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
        		PPEModel md = new PPEModel();
        		md.setID(Id);
				md.setNAME(ppeBox.getSelectedItem().toString());
				md.setQUANTITY(Integer.parseInt(quantityText.getText()));
      		
        		try 
				{
        			fcr.EditRecordPPE(md);
            		JOptionPane.showMessageDialog(null, "Data edited!", "Message ", JOptionPane.INFORMATION_MESSAGE);
					mainFrame.setVisible(false);
					PPETable.PPETableData(tablePPE);
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
