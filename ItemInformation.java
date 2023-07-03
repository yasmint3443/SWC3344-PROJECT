public class ItemInformation
{
    private int itemId;
    private String itemName;
    private double itemPrice;
    private String datePurchase;
    
    // constructor with parameters
    public ItemInformation (int itemId, String itemName, double itemPrice, String datePurchase)
    {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.datePurchase = datePurchase;
    }
    
    // mutator for each attribute
    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }
    
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    
    public void setItemPrice(double itemPrice)
    {
        this.itemPrice = itemPrice;
    }
    
    public void setDatePurchase(String datePurchase)
    {
        this.datePurchase = datePurchase;
    }
    
    // accessor for each attribute
    public int getItemId()
    {
        return itemId;
    }
    
    public String getItemName()
    {
        return itemName;
    }
    
    public double getItemPrice()
    {
        return itemPrice;
    }
    
    public String getDatePurchase()
    {
        return datePurchase;
    }

    public String toString()
    {
        return "Item ID: " + itemId + "\n" +
                "Item Name: " + itemName + "\n" +
                "Item Price: RM " + itemPrice + "\n" +
                "Date of Purchase: " + datePurchase + "\n";
    }
    }
