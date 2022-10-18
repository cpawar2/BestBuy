package bestbuy.model;

import java.util.HashMap;

public class Order {

    public static  int orderNumber=0;

    public int orderNO;
    public String productID;
    public String productName;
    public String category;
    public int quantity;
    public double price;

    public String status;

    public static final String ORDER_DELIVERED = "ORDER_DELIVERED";

    public static final String ORDER_DISPATCHED  = "ORDER_DISPATCHED";

    public static final String ORDER_IN_STORE_HOUSE = "ORDER_IN_STORE_HOUSE";
    public static final String ORDER_PLACED = "ORDER_PLACED";

    public static final HashMap<Integer,String> orderStatusMap = new HashMap<>();
   static {
       orderStatusMap.put(1,ORDER_PLACED);
       orderStatusMap.put(2,ORDER_IN_STORE_HOUSE);
       orderStatusMap.put(3,ORDER_DISPATCHED);
       orderStatusMap.put(4,ORDER_DELIVERED);
   }

    public static String [] headers = {"ORDER_NO","PRODUCT_ID","PRODUCT_NAME","CATEGORY","QUANTITY","PRICE","ORDER_STATUS"};


    public static int generateOrderNumber() {
        orderNumber+=1;
        return orderNumber;
    }

}
