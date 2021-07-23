package Models;

public class TransferModel {
    private int id;
	private String storeName;
	
	private String ppeType;
	private int ppeQuantity;
	
	private String dressingType;
	private int dressingQuantity;
	
	private String intravenousType;
	private int intravenousQuantity;
	
	public int getID() 
	{
		return id;
	}
	
	public void setID(int ID) 
	{
		this.id = ID;
	}
	
	public String getStoreName() 
	{
		return storeName;
	}
	
	public void setStoreName(String Name) 
	{
		this.storeName = Name;
	}

	public String getPPETYPE() 
	{
		return ppeType;
	}
	
	public void setPPETYPE(String PPETYPE) 
	{
		this.ppeType = PPETYPE;
	}
	
	public int getPPEQUANTITY() 
	{
		return ppeQuantity;
	}
	
	public void setPPEQUANTITY(int PPEQUANTITY) 
	{
		this.ppeQuantity = PPEQUANTITY;
	}

	
	public String getDRESSINGTYPE() 
	{
		return dressingType;
	}
	
	public void setDRESSINGTYPE(String DRESSINGTYPE) 
	{
		this.dressingType = DRESSINGTYPE;
	}
	
	public int getDRESSINGQUANTITY() 
	{
		return dressingQuantity;
	}
	
	public void setDRESSINGQUANTITY(int DRESSINGQUANTITY) 
	{
		this.dressingQuantity = DRESSINGQUANTITY;
	}

	public String getIVTYPE() 
	{
		return intravenousType;
	}
	
	public void setIVTYPE(String IVTYPE) 
	{
		this.intravenousType = IVTYPE;
	}
	
	public int getIVQUANTITY() 
	{
		return intravenousQuantity;
	}
	
	public void setIVQUANTITY(int IVQUANTITY) 
	{
		this.intravenousQuantity = IVQUANTITY;
	}


}
