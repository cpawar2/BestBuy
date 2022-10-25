//A single class to manage all queries reducing coupling with other DAO classes
public class Queries {
    public static final String LIST_PRODUCTS_COUNT_QUERY = "SELECT COUNT(*) FROM PRODUCT";
    public static final String LIST_ALL_PRODUCTS = "SELECT * FROM PRODUCT";


    //ORDERS
    public static final String LIST_ALL_ORDERS  = "SELECT * FROM ORDERS";
    public static final String INSERT_NEW_ORDER = "INSERT INTO ORDERS (ORDER_NO,PRODUCT_ID,PRODUCT_NAME,CATEGORY,QUANTITY,PRICE,STATUS) VALUES(?,?,?,?,?,?,?)";
    public static final String UPDATE_ORDER_STATUS = "UPDATE ORDERS SET STATUS = ? WHERE ORDER_NO = ?";
    public static final String DELETE_ORDER = "DELETE FROM ORDERS WHERE ORDER_NO = ?";

    //CUSTOMERS
    public static final String VALIDATE_CUSTOMER = "SELECT * FROM CUSTOMER WHERE USER_NAME = ? AND PASSWORD = ?";


    //USERS
    public static final String VALIDATE_USER = "SELECT * FROM USERS  WHERE USER_NAME = ? AND PASSWORD = ? AND ROLE = ?";


    //PRODUCT
    public static final String UPDATE_PRODUCT_INVENTORY = "UPDATE PRODUCT SET INVENTORY = ? WHERE PRODUCT_ID = ? ";
}
