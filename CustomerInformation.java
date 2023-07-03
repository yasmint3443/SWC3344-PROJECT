import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class CustomerInformation
{
    private String custId;
    private String custIC;
    private double counterPaid;
    private double totalAmountPaid;
    private List<ItemInformation> itemsPurchased; // To store the list of all purchased items
    
    DecimalFormat df = new DecimalFormat("#00.00");

    // constructor with parameters
    public CustomerInformation(String custId, String custIC, double counterPaid)
    {
        this.custId = custId;
        this.custIC = custIC;
        this.counterPaid = counterPaid;
        this.itemsPurchased = new ArrayList<>();
    }

    // mutator for each attribute
    public void setCustId(String custId)
    {
        this.custId = custId;
    }

    public void setCustIC(String custIC)
    {
        this.custIC = custIC;
    }

    public void setCounterPaid(double counterPaid)
    {
        this.counterPaid = counterPaid;
    }

    public void setItemsPurchased(List<ItemInformation> itemsPurchased) 
    {
        this.itemsPurchased = itemsPurchased;
    }
    
    public void setTotalAmountPaid(double totalAmountPaid) 
    {
        this.totalAmountPaid = totalAmountPaid;
    }

    // accessor method for each attribute
    public String getCustId()
    {
        return custId;
    }

    public String getCustIc()
    {
        return custIC;
    }

    public double getCounterPaid()
    {
        return counterPaid;
    }

    public List<ItemInformation> getItemsPurchased() 
    {
        return itemsPurchased;
    }

    public void addItem(ItemInformation item) 
    {
        itemsPurchased.add(item);
    }

    public double getTotalAmountPaid() 
    {
        double totalAmountPaid = 0.0;

        // Calculate the total amount paid for each item
        for (ItemInformation item : itemsPurchased) 
        {
            totalAmountPaid += item.getItemPrice();
        }

        return totalAmountPaid;
    }
}