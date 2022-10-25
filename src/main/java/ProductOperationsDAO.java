import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProductOperationsDAO {

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


    public static void main(String[] args) {
        ProductOperationsDAO productOperationsDAO = new ProductOperationsDAO();
        try {
            Product[] products = productOperationsDAO.listProductsFromDB();
            System.out.println( productOperationsDAO.listProductsFromDB() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
