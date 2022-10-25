import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//DAO class to manager Product db operation
public class ProductOperationsDAO {


    //list the number of products in product table
    int getNumberOfProducts() throws SQLException {
        int countOfProducts;
        Connection connection;
        ResultSet resultSet = null;
        connection = ConnectionHandler.getConnection();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(Queries.LIST_PRODUCTS_COUNT_QUERY);
            resultSet.next();
            countOfProducts = resultSet.getInt("count(*)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            resultSet.close();
            connection.close();
        }
        return countOfProducts;
    }


    //list all products from the database
    public Product[] listProductsFromDB() throws SQLException {
        int numberOfProducts;
        try {
            numberOfProducts = getNumberOfProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Product[] products = new Product[numberOfProducts];
        int productsArrIdx = 0;
        Connection connection;
        connection = ConnectionHandler.getConnection();
        ResultSet resultSet=null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(Queries.LIST_ALL_PRODUCTS);
            while (resultSet.next()){
                Product product = new Product();
                product.setProductID(resultSet.getString(Product.columns[0]));
                product.setProductName(resultSet.getString(Product.columns[1]));
                product.setProductCategory(resultSet.getString(Product.columns[2]));
                product.setProductSubCategory(resultSet.getString(Product.columns[3]));
                product.setProductDescription(resultSet.getString(Product.columns[4]));
                product.setProductPrice(resultSet.getDouble(Product.columns[5]));
                product.setManufacturer(resultSet.getString(Product.columns[6]));
                product.setInventory(resultSet.getInt(Product.columns[7]));
                product.setDiscountPercent(resultSet.getDouble(Product.columns[8]));
                product.setProductAddedDate(resultSet.getDate(Product.columns[9]));
                product.setProductUpdatedDate(resultSet.getDate(Product.columns[10]));
                products[productsArrIdx++]=product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            resultSet.close();
            connection.close();
        }

        return products;

    }



    //Update the inventory for the product in database
    void updateInventory(String productID, int quantity){
        try(Connection connection = ConnectionHandler.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Queries.UPDATE_PRODUCT_INVENTORY)
        ){
            preparedStatement.setInt(1,quantity);
            preparedStatement.setString(2,productID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
