import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//DAO class to manager db operations for customer
public class CustomerDAO {

    //validate a customer by checking credentials in database
    int validateCustomer(String userName, String password){
        ResultSet resultSet=null;
        try(Connection connection = ConnectionHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.VALIDATE_CUSTOMER);
        ){
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("CUSTOMER_ID");
            }else{
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
