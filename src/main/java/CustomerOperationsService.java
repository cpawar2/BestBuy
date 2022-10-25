import org.apache.xmlbeans.soap.SOAPArrayType;

//Service class to manager Customer related operations
public class CustomerOperationsService implements IService {
     Customer customer;
     static CustomerOperationsService customerOperationsService = new CustomerOperationsService();
     static OrderDAO orderDAO = new OrderDAO();

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
                    orderDAO.createOrder(order);
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
        public int validateCustomer(String customerUsername, String customerPassword){
            CustomerDAO customerDAO = new CustomerDAO();
            int customerID = customerDAO.validateCustomer(customerUsername, customerPassword);
            return customerID;
        }



        //delete order from DB
        public void deleteOrder(int orderNO){
            customer.getOrderQueue().dequeue();
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.deleteOrder(orderNO);
            System.out.println("---------------------------------ORDER DELETED SUCCESSFULLY----------------------------------");
        }

        //menu items related to customer
        public void  showCustomerMenu(){
            String productID;
            int quantity;
            int customerID = 0;
            while(customerID==0){
                System.out.println("ENTER USERNAME ");
                String username = MenuOperationsService.scanner.next();
                System.out.println("ENTER PASSWORD ");
                String password = MenuOperationsService.scanner.next();
                customerID = customerOperationsService.validateCustomer(username, password);
                if(customerID!=0){
                    customer = new Customer(username);
                }
            }
            while(true){
                System.out.println("1) ENTER 1 TO VIEW ALL PRODUCTS");
                System.out.println("2) ENTER 2 TO SEARCH PRODUCT BY NAME");
                System.out.println("3) ENTER 3 TO SEARCH PRODUCT BY SUB CATEGORY (eg: Laptop)");
                System.out.println("4) ENTER 4 TO BUY PRODUCT");
                System.out.println("5) ENTER 5 TO VIEW YOUR ORDERS");
                System.out.println("6) ENTER 6 TO DELETE ODER");
                System.out.println("7) ENTER 7 TO GO TO MAIN MENU");
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
                        customer.getOrderQueue().display();
                        break;
                    case 6:
                        System.out.println("Enter order number to delete");
                        int orderNO = MenuOperationsService.scanner.nextInt();
                        CustomerOperationsService.customerOperationsService.deleteOrder(orderNO);
                        break;
                    case 7:
                        MenuOperationsService.menuOperationsService.showMainMenu();
                        break;


                }
            }

        }


}
