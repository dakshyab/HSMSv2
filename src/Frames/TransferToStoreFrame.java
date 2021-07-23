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
import javax.swing.JTextField;

import CRUD.*;
import Models.TransferModel;
import TableInit.DRESSINGTable;
import TableInit.IVTable;
import TableInit.PPETable;

public class TransferToStoreFrame {

    public static void GUI(){
		JFrame mainFrame = new JFrame();
		JButton Save = new JButton("Save");
		JButton Cancel = new JButton("Cancel");
		mainFrame.toFront();

		mainFrame.setTitle("Send Order");
		mainFrame.setBounds(300,90,500,400);
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		mainFrame.getContentPane().setBackground(Color.decode("#bdfffd"));
			
		JLabel title = new JLabel("SEND STOCK"); 
        title.setFont(new Font("Arial", Font.PLAIN, 25)); 
        title.setSize(300, 30); 
        title.setLocation(150, 30); 
        mainFrame.add(title); 

		JLabel hospitalStore = new JLabel("HOSPITAL STORE"); 
		hospitalStore.setFont(new Font("Arial", Font.PLAIN, 17)); 
		hospitalStore.setSize(200, 20); 
		hospitalStore.setLocation(80, 100); 
		mainFrame.add(hospitalStore); 
	
		String hospitalStoreItem[] = {"Store 1","Store 2","Store 3","Store 4"};
		JComboBox hospitalStoreItems = new JComboBox(hospitalStoreItem);
		
		hospitalStoreItems.setFont(new Font("Arial", Font.PLAIN, 17)); 
		hospitalStoreItems.setSize(150, 20); 
		hospitalStoreItems.setLocation(270, 100); 
		mainFrame.add(hospitalStoreItems); 
	
		JLabel ppeName = new JLabel("PPE"); 
		ppeName.setFont(new Font("Arial", Font.PLAIN, 17)); 
		ppeName.setSize(200, 20); 
		ppeName.setLocation(80, 150); 
		mainFrame.add(ppeName); 
	
		String ppeItem[] = {"Gloves","Aprons","Gowns","Visors"};
		JComboBox ppeItems = new JComboBox(ppeItem);
		
		ppeItems.setFont(new Font("Arial", Font.PLAIN, 17)); 
		ppeItems.setSize(150, 20); 
		ppeItems.setLocation(270, 150); 
		mainFrame.add(ppeItems); 
		
		JLabel ppequantity = new JLabel("Quantity"); 
		ppequantity.setFont(new Font("Arial", Font.PLAIN, 17)); 
		ppequantity.setSize(200, 20); 
		ppequantity.setLocation(80, 200); 
		mainFrame.add(ppequantity); 
	
		JTextField txtFieldPPEQty = new JTextField(); 
		txtFieldPPEQty.setFont(new Font("Arial", Font.PLAIN, 17)); 
		txtFieldPPEQty.setSize(150, 20); 
		txtFieldPPEQty.setLocation(270, 200); 
		mainFrame.add (txtFieldPPEQty); 
	

		JLabel dressingName = new JLabel("DRESSINGS"); 
		dressingName.setFont(new Font("Arial", Font.PLAIN, 17)); 
		dressingName.setSize(200, 20); 
		dressingName.setLocation(80, 250); 
		mainFrame.add(dressingName); 
	
		String dressingItem[] = {"Plasters","Bandages","Specialist Dressings","Tape"};
		JComboBox dressingItems = new JComboBox(dressingItem);
		
		dressingItems.setFont(new Font("Arial", Font.PLAIN, 17)); 
		dressingItems.setSize(150, 20); 
		dressingItems.setLocation(270, 250); 
		mainFrame.add(dressingItems); 
		
		JLabel dressingquantity = new JLabel("Quantity"); 
		dressingquantity.setFont(new Font("Arial", Font.PLAIN, 17)); 
		dressingquantity.setSize(200, 20); 
		dressingquantity.setLocation(80, 300); 
		mainFrame.add(dressingquantity); 
	
		JTextField txtFieldDressingQty = new JTextField(); 
		txtFieldDressingQty.setFont(new Font("Arial", Font.PLAIN, 17)); 
		txtFieldDressingQty.setSize(150, 20); 
		txtFieldDressingQty.setLocation(270, 300); 
		mainFrame.add (txtFieldDressingQty); 

		JLabel ivName = new JLabel("IV Supply"); 
		ivName.setFont(new Font("Arial", Font.PLAIN, 17)); 
		ivName.setSize(200, 20); 
		ivName.setLocation(80, 350); 
		mainFrame.add(ivName); 
	
		String ivItem[] = {"Needles","Syringes","Cannulas","Pre-Injection Swabs"};
		JComboBox ivItems = new JComboBox(ivItem);
		
		ivItems.setFont(new Font("Arial", Font.PLAIN, 17)); 
		ivItems.setSize(150, 20); 
		ivItems.setLocation(270, 350); 
		mainFrame.add(ivItems); 
		
		JLabel ivquantity = new JLabel("Quantity"); 
		ivquantity.setFont(new Font("Arial", Font.PLAIN, 17)); 
		ivquantity.setSize(200, 20); 
		ivquantity.setLocation(80, 400); 
		mainFrame.add(ivquantity); 
	
		JTextField txtFieldivQty = new JTextField(); 
		txtFieldivQty.setFont(new Font("Arial", Font.PLAIN, 17)); 
		txtFieldivQty.setSize(150, 20); 
		txtFieldivQty.setLocation(270, 400); 
		mainFrame.add(txtFieldivQty); 
		
		Save.setFont(new Font("Arial", Font.PLAIN, 15)); 
		Save.setSize(100, 40); 
		Save.setLocation(120, 480);  
		mainFrame.add(Save); 

		
		Save.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				TransferModel md = new TransferModel();
				md.setStoreName(hospitalStoreItems.getSelectedItem().toString());
				md.setIVTYPE(ivItems.getSelectedItem().toString());
				md.setPPETYPE(ppeItems.getSelectedItem().toString());
				md.setDRESSINGTYPE(dressingItems.getSelectedItem().toString());
				
				if(txtFieldPPEQty.getText().length() == 0) 
				{
					md.setPPEQUANTITY(0);
				}
				else 
				{
					md.setPPEQUANTITY(Integer.parseInt(txtFieldPPEQty.getText()));
				}
				
				if(txtFieldDressingQty.getText().length() == 0) 
				{
					md.setDRESSINGQUANTITY(0);
				}
				else 
				{
					md.setDRESSINGQUANTITY(Integer.parseInt(txtFieldDressingQty.getText()));
				}
				
				if(txtFieldivQty.getText().length() == 0) 
				{
					md.setIVQUANTITY(0);
				}
				else 
				{
					md.setIVQUANTITY(Integer.parseInt(txtFieldivQty.getText()));
				}

				FileCRUD obj = new FileCRUD(); 
				try 
				{
					if(txtFieldPPEQty.getText().length() == 0 && txtFieldDressingQty.getText().length() == 0 && txtFieldivQty.getText().length() == 0) 
					{
						JOptionPane.showMessageDialog(null, "Quantity field empty!", "Message ", JOptionPane.INFORMATION_MESSAGE);
					}
					else 
					{
						boolean chkStat = obj.AddStoreOrderData(md,hospitalStoreItem);
						
						if(chkStat == false) 
						{
							JOptionPane.showMessageDialog(null, "Not Enough in Stock!", "Message ", JOptionPane.INFORMATION_MESSAGE);
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Saved Successfully!", "Message ", JOptionPane.INFORMATION_MESSAGE);
							mainFrame.setVisible(false);
							
							MainFrame.mainGUI();
						}
					}
					
					
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		Cancel.setFont(new Font("Arial", Font.PLAIN, 15)); 
		Cancel.setSize(100, 40); 
		Cancel.setLocation(280, 480); 
		mainFrame.add(Cancel);
	
		Cancel.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				mainFrame.setVisible(false);
				ManageStockFrame.mainFrame.setVisible(false);
				MainFrame.mainGUI();
			}
		});
		
		mainFrame.setSize(500,600);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);


		mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setVisible(false);
                ManageStockFrame.mainFrame.setVisible(false);
				MainFrame.mainGUI();
            }
            
        });
	}
}
