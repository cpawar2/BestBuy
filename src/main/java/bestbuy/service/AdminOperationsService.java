package bestbuy.service;

import bestbuy.util.OrderOperations;

public class AdminOperationsService implements IService {
    public static AdminOperationsService adminOperationsService = new AdminOperationsService();
    public static  final String ADMIN_USER_NAME = "admin";
    public static final String ADMIN_PASSWORD = "admin";
    public void updateOrderStatus(String OrderID){
        System.out.println("Enter Order Status");

    }

    public void showMenu(){
        boolean validateAdmin = false;
        while(validateAdmin==false){
            System.out.println("ENTER USERNAME ");
            String username = MenuOperationsService.scanner.next();
            System.out.println("ENTER PASSWORD ");
            String password = MenuOperationsService.scanner.next();
            validateAdmin = adminOperationsService.validateAdmin(username, password);
        }
        while(true){
            System.out.println("1) ENTER 1 TO VIEW ALL ORDERS");
            System.out.println("2) ENTER 2 TO UPDATE ORDER STATUS");
            System.out.println("3) ENTER 3 TO GO BACK TO MAIN MENU");
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
                    MenuOperationsService.menuOperationsService.showMainMenu();
                    break;
            }
        }
    }



    public boolean validateAdmin(String userName,String password){
        if(userName.equals(ADMIN_USER_NAME)&&password.equals(ADMIN_PASSWORD)){
            return true;
        }
        return false;
    }
}
