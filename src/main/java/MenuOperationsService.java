import java.util.Scanner;

public class MenuOperationsService implements IService {
    static Scanner scanner = new Scanner(System.in);
    static MenuOperationsService menuOperationsService = new MenuOperationsService();

    public  void showMainMenu() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        while(choice!=3){
            System.out.println("1) ENTER 1 FOR LOGGING AS CUSTOMER ");
            System.out.println("2) ENTER 2 FOR LOGGING AS ADMIN");
            System.out.println("3) ENTER 3 FOR EXITING OUT OF BEST BUY");
            choice=scanner.nextInt();
            switch (choice){
                case 1:
                        CustomerOperationsService.customerOperationsService.showCustomerMenu();
                        break;
                case 2:
                        AdminOperationsService.adminOperationsService.showMenu();
                        break;

            }
            }
        System.out.println("---------------------------------THANK YOU FOR USING BEST BUY, YOUR SESSION HAS ENDED--------------------------------------");
        System.exit(0);
        }


    public static void main(String[] args) {
        System.out.println("--------------------------------------WELCOME TO BEST BUY-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        menuOperationsService.showMainMenu();
    }
    }


