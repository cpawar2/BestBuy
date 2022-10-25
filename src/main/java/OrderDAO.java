import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

//This class is responsible for taking care of all database opeartions for order
public class OrderDAO {
    static int orderNO =0;

   int getUniqueOrderNumber(){
      return   orderNO+=1;
    }


    LinkedList<Order> listOrders() {
        LinkedList<Order> orderLinkedList = new LinkedList<>();
        //we are using try with resources, we connect to database first and then have the sql query executed from where we get orders
        try (Connection connection = ConnectionHandler.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Queries.LIST_ALL_ORDERS)
            ) {
            while (resultSet.next()){
                Order order = new Order();
                order.orderNO = resultSet.getInt(Order.orderColumns[0]);
                order.productID = resultSet.getString(Order.orderColumns[1]);
                order.productName  = resultSet.getString(Order.orderColumns[2]);
                order.category = resultSet.getString(Order.orderColumns[3]);
                order.quantity = resultSet.getInt(Order.orderColumns[4]);
                order.price = resultSet.getDouble(Order.orderColumns[5]);
                order.status = resultSet.getString(Order.orderColumns[6]);
                orderLinkedList.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderLinkedList;

    }
    void createOrder(Order order){

       //connect to database and using prepared statement add a order
        try(Connection connection= ConnectionHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_NEW_ORDER)){
            preparedStatement.setInt(1,getUniqueOrderNumber());
            preparedStatement.setString(2,order.productID);
            preparedStatement.setString(3,order.productName);
            preparedStatement.setString(4,order.category);
            preparedStatement.setInt(5,order.quantity);
            preparedStatement.setDouble(6,order.price);
            preparedStatement.setString(7,order.status);
            int row = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    //updating an order status in database
    void updateOrderStatus(int orderNumber,String orderStatus){
        try(Connection connection= ConnectionHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.UPDATE_ORDER_STATUS)){
            preparedStatement.setString(1,orderStatus);
            preparedStatement.setInt(2,orderNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //delete the order in database

    boolean deleteOrder(int orderNO){
        try(Connection connection = ConnectionHandler.getConnection();){
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.DELETE_ORDER);
            preparedStatement.setInt(1,orderNO);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }






}
