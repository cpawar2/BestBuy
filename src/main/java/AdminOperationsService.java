//Service class to handle Admin related operations
public class AdminOperationsService implements IService {
    public static AdminOperationsService adminOperationsService = new AdminOperationsService();

    //Display menu items related to admin
    public void showMenu(){
        boolean validateAdmin = false;
        while(validateAdmin==false){
            System.out.println("ENTER USERNAME ");
            String username = MenuOperationsService.scanner.next();
            System.out.println("ENTER PASSWORD ");
            String password = MenuOperationsService.scanner.next();
            validateAdmin = adminOperationsService.validateAdmin(username, password,"ADMIN");
        }
        while(true){
            System.out.println("1) ENTER 1 TO VIEW ALL ORDERS");
            System.out.println("2) ENTER 2 TO UPDATE ORDER STATUS");
            System.out.println("3) ENTER 3 TO VIEW ALL PRODUCT INVENTORY DATA");
            System.out.println("4) ENTER 4 TO UPDATE A PRODUCTS INVENTORY");
            System.out.println("5) ENTER 5 TO RETURN TO MAIN MENU");
            int choice = MenuOperationsService.scanner.nextInt();
            switch (choice){
                case 1:
                    OrderOperations.orderOperations.showOrderQueue();
                    break;
                case 2:
                    System.out.println("Enter order no to update");
                    int orderNO  = MenuOperationsService.scanner.nextInt();
                    System.out.println("Enter 1 to set status as  ORDER_PLACED");
                    System.out.println("Enter 2 to set status as ORDER_IN_STORE_HOUSE");
                    System.out.println("Enter 3 to set status as ORDER_DISPATCHED");
                    System.out.println("Enter 4 to set status as ORDER_DELIVERED");
                    int orderStatus = MenuOperationsService.scanner.nextInt();
                    OrderOperations.orderOperations.updateOrder(orderNO,orderStatus);
                    break;
                case 3:
                    ProductOperationService.productOperationService.displayProductInventoryData();
                    break;
                case 4:
                    System.out.println("Enter product ID for which inventory is to be updated");
                    String productID = MenuOperationsService.scanner.next();
                    System.out.println("Enter quantity to be updated to ");
                    int quantity = MenuOperationsService.scanner.nextInt();
                    ProductOperationService.productOperationService.updateProductInventory(productID,quantity);
                    break;
                case 5:
                    MenuOperationsService.menuOperationsService.showMainMenu();
                    break;
            }
        }
    }



    //Validate admin credentials from DB
    public boolean validateAdmin(String userName,String password,String role){
        UserDAO userDAO = new UserDAO();
        int userID = userDAO.validateUser(userName, password, role);
        if(userID!=0){
            return true;
        }
        return false;
    }
}
