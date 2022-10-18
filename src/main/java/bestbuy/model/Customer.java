package bestbuy.model;

import com.bethecoder.ascii_table.ASCIITable;

public class Customer {
    private String customerID;
    private String customerFirstName;
    private String customerLastName;
    private  BestBuyStack customerCart = new BestBuyStack<Order>();

    private BestBuyQueue orderQueue = new BestBuyQueue<Order>();

    private String customerUserName;

    private String customerPassword;

    public Customer(String customerID, String customerFirstName, String customerLastName) {
        this.customerID = customerID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }

    public Customer(String customerID, String customerFirstName, String customerLastName,  String customerUserName, String customerPassword) {
        this.customerID = customerID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerUserName = customerUserName;
        this.customerPassword = customerPassword;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public  BestBuyStack getCustomerCart() {
        return customerCart;
    }



    public String getCustomerUserName() {
        return customerUserName;
    }


    public String getCustomerPassword() {
        return customerPassword;
    }



    public BestBuyQueue getOrderQueue() {
        return orderQueue;
    }
}
