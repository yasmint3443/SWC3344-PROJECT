import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class HypermarketSystem
{
    public static void main(String[] args)
    {
        DecimalFormat df = new DecimalFormat("#00.00");

        try
        {
            // Create Queue and Stack
            Queue counter1Queue = new Queue();
            Queue counter2Queue = new Queue();
            Queue counter3Queue = new Queue();
            Stack<CustomerInformation> completeStack = new Stack(); // Initialize completeStack

            // read from CustomerData.txt file
            BufferedReader in = new BufferedReader(new FileReader("CustomerData.txt"));

            // Declare the initialize linkedList
            LinkedList<CustomerInformation> customerList = new LinkedList<CustomerInformation>();

            // declare indata ( a line input file)
            String indata = null;

            // declare and create object
            CustomerInformation cust = null;

            int counter1Count = 0;
            int counter2Count = 0;
            int counter3Count = 0;

            while ((indata = in.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(indata, ";");
                String custId = st.nextToken();
                String custIC = st.nextToken();
                double counterPaid = Double.parseDouble(st.nextToken());

                // Create customer object
                cust = new CustomerInformation(custId, custIC, counterPaid);

                StringTokenizer s2 = new StringTokenizer(st.nextToken(), ":");
                // Initialize itemsPurchased ArrayList for the customer
                ArrayList<ItemInformation> itemsPurchased = new ArrayList<>();
                while(s2.hasMoreTokens())
                {
                    StringTokenizer s3 = new StringTokenizer(s2.nextToken(), ",");
                    int itemId = Integer.parseInt(s3.nextToken());
                    String itemName = s3.nextToken();
                    double itemPrice = Double.parseDouble(s3.nextToken());
                    String datePurchase = s3.nextToken();

                    // Create object
                    ItemInformation item = new ItemInformation(itemId, itemName, itemPrice, datePurchase);
                    // Add items to the customer's purchased items
                    cust.addItem(item);
                    // Add items to the customer's purchased items
                    itemsPurchased.add(item);

                    counterPaid += itemPrice;
                }

                // Set the purchased items for the customer
                cust.setItemsPurchased(itemsPurchased);

                // Check if customer has 5 items or less
                if (itemsPurchased.size() <= 5) 
                {
                    if (counter1Count < 5) 
                    {
                        counter1Queue.enqueue(cust);
                        counter1Count++;
                        cust.setCounterPaid(1); // Set counterPaid to 1 for counter 1
                    } 
                    else if (counter2Count < 5)
                    {
                        counter2Queue.enqueue(cust);
                        counter2Count++;
                        cust.setCounterPaid(2); // Set counterPaid to 2 for counter 2
                    }
                    else
                    {
                        counter1Count = 0;
                        counter2Count = 1;
                    }
                } 
                else //if (itemsPurchased.size() > 5)
                {
                    counter3Queue.enqueue(cust);
                    counter3Count++;
                    cust.setCounterPaid(3); // Set counterPaid to 3 for counter 3
                }

                // add into LinkedList
                customerList.add(cust);
            }
            in.close();

            // Process each counter queue
            int paymentRound = 1;
            while (!counter1Queue.empty() || !counter2Queue.empty() || !counter3Queue.empty()) {

                // Counter 1
                for (int i = 0; i < 5 && !counter1Queue.empty(); i++) {
                    CustomerInformation customer = (CustomerInformation) counter1Queue.dequeue();
                    completeStack.push(customer);
                }

                // Counter 2
                for (int i = 0; i < 5 && !counter2Queue.empty(); i++) {
                    CustomerInformation customer = (CustomerInformation) counter2Queue.dequeue();
                    completeStack.push(customer);
                }

                // Counter 3
                while (!counter3Queue.empty()) {
                    CustomerInformation customer = (CustomerInformation) counter3Queue.dequeue();
                    completeStack.push(customer);
                }

            }

            int menu = 0;
            do
            {
                menu = Integer.parseInt(JOptionPane.showInputDialog("Press 1 - Counter 1\nPress 2 - Counter 2\nPress 3 - Counter 3\nPress 4 - Display All Customers\nPress 5 - Exit"));

                if (menu == 1) // Customers who paid at counter 1 || who has less than 5 items
                {
                    System.out.println("Customers paid at Counter 1:");
                    boolean found = false;

                    while (!completeStack.empty()) {
                        CustomerInformation customer = completeStack.pop();
                        if (customer.getCounterPaid() == 1) {
                            found = true;
                            System.out.println("\n************** HYPERMARKET AUTOMATED SYSTEM **************\n");
                            System.out.println("Customer ID: " + customer.getCustId());
                            System.out.println("Customer IC: " + customer.getCustIc());
                            processCustomer(customer);
                            System.out.println("Counter Paid: " + customer.getCounterPaid());

                            List<ItemInformation> itemsPurchased = customer.getItemsPurchased();
                            int itemCount = itemsPurchased.size();
                            System.out.println("Items Purchased: " + itemCount);

                            for (ItemInformation item : itemsPurchased) {
                                System.out.println(item.getItemName() + " - RM" + item.getItemPrice());
                            }

                            System.out.println("Total Amount Paid: RM" + String.format("%.2f", customer.getTotalAmountPaid()));
                            System.out.println("Processing customer: " + customer.getCustId());
                            System.out.println("Payment processed successfully.");
                            System.out.println();
                        }
                    }   
                }
                else if (menu == 2) // Customers who paid at counter 2 || who has than 5 items
                {
                    System.out.println("Customers paid at Counter 2:");
                    boolean found = false;

                    while (!completeStack.empty()) {
                        CustomerInformation customer = completeStack.pop();
                        if (customer.getCounterPaid() == 2) {
                            found = true;
                            System.out.println("\n************** HYPERMARKET AUTOMATED SYSTEM **************\n");
                            System.out.println("Customer ID: " + customer.getCustId());
                            System.out.println("Customer IC: " + customer.getCustIc());
                            processCustomer(customer);
                            System.out.println("Counter Paid: " + customer.getCounterPaid());

                            List<ItemInformation> itemsPurchased = customer.getItemsPurchased();
                            int itemCount = itemsPurchased.size();
                            System.out.println("Items Purchased: " + itemCount);

                            for (ItemInformation item : itemsPurchased) {
                                System.out.println(item.getItemName() + " - RM" + item.getItemPrice());
                            }

                            System.out.println("Total Amount Paid: RM" + String.format("%.2f", customer.getTotalAmountPaid()));
                            System.out.println("Processing customer: " + customer.getCustId());
                            System.out.println("Payment processed successfully.");
                            System.out.println();
                        }
                    }   
                }
                else if (menu == 3) // Customers who paid at counter 3 || who has more than 5 items
                {
                    System.out.println("Customers paid at Counter 3:");
                    boolean found = false;

                    while (!completeStack.empty()) {
                        CustomerInformation customer = completeStack.pop();
                        if (customer.getCounterPaid() == 3) {
                            found = true;
                            System.out.println("\n************** HYPERMARKET AUTOMATED SYSTEM **************\n");
                            System.out.println("Customer ID: " + customer.getCustId());
                            System.out.println("Customer IC: " + customer.getCustIc());
                            processCustomer(customer);
                            System.out.println("Counter Paid: " + customer.getCounterPaid());

                            List<ItemInformation> itemsPurchased = customer.getItemsPurchased();
                            int itemCount = itemsPurchased.size();
                            System.out.println("Items Purchased: " + itemCount);

                            for (ItemInformation item : itemsPurchased) {
                                System.out.println(item.getItemName() + " - RM" + item.getItemPrice());
                            }

                            System.out.println("Total Amount Paid: RM" + String.format("%.2f", customer.getTotalAmountPaid()));
                            System.out.println("Processing customer: " + customer.getCustId());
                            System.out.println("Payment processed successfully.");
                            System.out.println();
                        }
                    }
                }
                else if (menu == 4) // // Display all customer information and amount purchased
                {
                    while (!completeStack.empty()) 
                    {
                        System.out.println("\n************** HYPERMARKET AUTOMATED SYSTEM **************\n");
                        CustomerInformation customer = completeStack.pop();
                        System.out.println("Customer ID: " + customer.getCustId());
                        System.out.println("Customer IC: " + customer.getCustIc());
                        processCustomer(customer);
                        System.out.println("Counter Paid: " + customer.getCounterPaid());

                        List<ItemInformation> itemsPurchased = customer.getItemsPurchased();
                        int itemCount = itemsPurchased.size();
                        System.out.println("Items Purchased:" + itemCount);

                        for (ItemInformation item : customer.getItemsPurchased()) {
                            System.out.println(item.getItemName() + " - RM" + item.getItemPrice());
                        }

                        System.out.println("Total Amount Paid: RM" + String.format("%.2f",customer.getTotalAmountPaid())); //set two decimal places
                        System.out.println("Processing customer: " + customer.getCustId());
                        System.out.println("Payment processed successfully.");
                        System.out.println();
                    }
                }
                else // Exit the program
                {
                    JOptionPane.showMessageDialog(null, "Exit Program");
                }
            } while (menu != 5);
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        }
    }

    public static void processCustomer(CustomerInformation cust) 
    {
        double totalAmountPaid = 0;

        for (ItemInformation item : cust.getItemsPurchased()) {
            totalAmountPaid += item.getItemPrice();
        }

        cust.setTotalAmountPaid(totalAmountPaid);
        cust.setCounterPaid(cust.getCounterPaid());
    }
}
 