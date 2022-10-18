package bestbuy.service;

import bestbuy.model.Customer;
import bestbuy.model.Order;
import bestbuy.model.Product;


public class CustomerOperationsService implements IService {
     Customer customer;
     static CustomerOperationsService customerOperationsService = new CustomerOperationsService();

    public void viewCart(){
        customer.getCustomerCart().display();
    }

    public void viewOrders() {
        customer.getOrderQueue().display();
    }
    public void orderProduct(String productID,int quantity){
        boolean orderPlaced = false;
        for(Product product:ProductOperationService.productsArray){
            if(product.getProductID().trim().equals(productID)){
                if(product.getInventory()>quantity) {
                    product.setInventory(product.getInventory() - quantity);
                    Order order = new Order();
                    order.orderNO = order.generateOrderNumber();
                    order.productID = productID;
                    order.productName = product.getProductName();
                    order.category = product.getProductCategory();
                    order.quantity  = quantity;
                    order.price  = quantity * product.getProductPrice();
                    order.status = Order.ORDER_PLACED;
                    customer.getOrderQueue().enqueue(order);
                    orderPlaced = true;
                }
            }
        }
        if(orderPlaced){
            System.out.println("YOUR ORDER FOR  "+productID +" IS PLACED ");
        }else{
            System.out.println("FAILED TO PLACE ORDER  , PLEASE TRY WITH CHANGING THE QUANTITY");
        }
    }

    public void addProductToCart(String productID,int quantity){
        boolean productAdded = false;
        for(Product product:ProductOperationService.productsArray){
            if(product.getProductID().trim().equals(productID)){
                if(product.getInventory()>quantity) {
                    product.setInventory(product.getInventory() - quantity);
                    Order order = new Order();
                    order.productID = productID;
                    order.productName = product.getProductName();
                    order.category = product.getProductCategory();
                    order.quantity  = quantity;
                    order.price  = quantity * product.getProductPrice();
                    customer.getCustomerCart().push(order);
                    productAdded = true;
                }
            }
        }
        if(productAdded){
            System.out.println("ADDED PRODUCT WITH ID "+productID +" TO YOUR CART!");
        }else{
            System.out.println("FAILED TO ADD PRODUCT TO YOUR CART , PLEASE TRY WITH CHANGING THE QUANTITY");
        }

    }
        public boolean validateCustomer(String customerUsername,String customerPassword){
            Customer[] listOfCustomers = getListOfCustomers();
            for(Customer customer : listOfCustomers){
                if(customer.getCustomerUserName().equals(customerUsername)){
                    if(customer.getCustomerPassword().equals(customerPassword)){
                        this.customer = customer;
                        return true;
                    }
                }
            }
            return false;
        }

        public Customer findCustomer(String customerID){
            Customer[] listOfCustomers = getListOfCustomers();
            for(Customer customer : listOfCustomers){
                if(customer.getCustomerID().equals(customerID)){

                   return customer;
                }
            }
            return null;
        }

        public Customer[] getListOfCustomers(){
            Customer customersArray[] =  {new Customer("BB#101","MIKE","WATSON","mwatson","harvard"),
                    new Customer("BB#102","HARVEY","SPECTER","hspecter","levels")};
            return customersArray;
        }

        public void  showCustomerMenu(){
            String productID;
            int quantity;
            boolean validateCustomer = false;
            while(validateCustomer==false){
                System.out.println("ENTER USERNAME ");
                String username = MenuOperationsService.scanner.next();
                System.out.println("ENTER PASSWORD ");
                String password = MenuOperationsService.scanner.next();
                validateCustomer = customerOperationsService.validateCustomer(username, password);
            }
            while(true){
                System.out.println("1) ENTER 1 TO VIEW ALL PRODUCTS");
                System.out.println("2) ENTER 2 TO SEARCH PRODUCT BY NAME");
                System.out.println("3) ENTER 3 TO SEARCH PRODUCT BY SUB CATEGORY (eg: Laptop)");
                System.out.println("4) ENTER 4 TO BUY PRODUCT");
                System.out.println("5) ENTER 5 TO ADD PRODUCT TO CART");
                System.out.println("6) ENTER 6 TO VIEW YOUR ORDERS");
                System.out.println("7) ENTER 7 TO VIEW YOUR CART");
                System.out.println("8) ENTER 8 TO GO TO MAIN MENU");
                int choice = MenuOperationsService.scanner.nextInt();
                switch (choice){
                    case 1:
                        ProductOperationService.productOperationService.displayProductsAvailable();
                        break;
                    case 2:
                        System.out.println("Enter product name to search");
                        String productName = MenuOperationsService.scanner.next();
                        ProductOperationService.productOperationService.searchByProductName(productName);
                        break;
                    case 3:
                        System.out.println("Enter product sub category to search");
                        String subCategory = MenuOperationsService.scanner.next();
                        ProductOperationService.productOperationService.searchProductBySubCategory(subCategory.toUpperCase());
                        break;
                    case 4:
                        System.out.println("Enter product ID ");
                        productID = MenuOperationsService.scanner.next();
                        System.out.println("Enter quantity ");
                        quantity = MenuOperationsService.scanner.nextInt();
                        CustomerOperationsService.customerOperationsService.orderProduct(productID,quantity);
                        break;
                    case 5:
                        System.out.println("Enter product ID ");
                        productID = MenuOperationsService.scanner.next();
                        System.out.println("Enter quantity ");
                        quantity = MenuOperationsService.scanner.nextInt();
                        CustomerOperationsService.customerOperationsService.addProductToCart(productID,quantity);
                        break;
                    case 6:
                        customer.getOrderQueue().display();
                        break;
                    case 7:
                        CustomerOperationsService.customerOperationsService.viewCart();
                        break;
                    case 8:
                        MenuOperationsService.menuOperationsService.showMainMenu();
                        break;


                }
            }

        }

}
