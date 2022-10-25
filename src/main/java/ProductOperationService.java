import com.bethecoder.ascii_table.ASCIITable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;

//Service class to implement business logic related to Product operations
public class ProductOperationService {
    public static String productHeaders [] ={"PRODUCT_ID","PRODUCT_NAME","PRODUCT_CATEGORY","PRODUCT_DESCRIPTION","PRODUCT_PRICE"};

    public static ProductOperationService productOperationService = new ProductOperationService();
    public static ProductOperationsDAO productOperationsDAO = new ProductOperationsDAO();
    public static Product[] productsArray;
    static {
        try {
            readProductsFromDB();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void readProductsFromDB() throws IOException {
        try {
            productsArray = productOperationsDAO.listProductsFromDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Arrays.sort(productsArray, Comparator.comparing(Product::getProductSubCategory));
    }

    public void displayProductsAvailable(){
            String[][] masterProductList = new String[productsArray.length][productHeaders.length];
            int rowCount=0;
            for(Product productToTraverse : productsArray){
                masterProductList[rowCount][0]=productToTraverse.getProductID();
                masterProductList[rowCount][1]=productToTraverse.getProductName();
                masterProductList[rowCount][2]=productToTraverse.getProductCategory();
                masterProductList[rowCount][3]=productToTraverse.getProductDescription();
                masterProductList[rowCount][4]=productToTraverse.getProductPrice()+" $";
                rowCount+=1;
            }
        ASCIITable.getInstance().printTable(productHeaders, masterProductList);

    }

    public void displayProductInventoryData(){
       String headers [] = {"PRODUCT_ID","PRODUCT_NAME","PRODUCT_CATEGORY","PRODUCT_INVENTORY","PRODUCT_PRICE"};
        String[][] masterProductList = new String[productsArray.length][headers.length];
        int rowCount=0;
        for(Product productToTraverse : productsArray){
            masterProductList[rowCount][0]=productToTraverse.getProductID();
            masterProductList[rowCount][1]=productToTraverse.getProductName();
            masterProductList[rowCount][2]=productToTraverse.getProductCategory();
            masterProductList[rowCount][3]=productToTraverse.getInventory()+"";
            masterProductList[rowCount][4]=productToTraverse.getProductPrice()+" $";
            rowCount+=1;
        }
        ASCIITable.getInstance().printTable(headers, masterProductList);

    }

    public void updateProductInventory(String productID,int quantity){
        ProductOperationsDAO productOperationsDAO = new ProductOperationsDAO();
        productOperationsDAO.updateInventory(productID,quantity);
        boolean productInventoryUpdated=false;
        for(Product productToSearch: productsArray){
            if(productToSearch.getProductID().equals(productID.trim())) {
                productToSearch.setInventory(quantity);
                productInventoryUpdated = true;
            }
        }
        if(productInventoryUpdated){
            System.out.println("----------------------------------------------------------------SUCCESSFULLY UPDATED INVENTORY FOR PRODUCT-------------------------------------------------------------------------");
        }else{
            System.out.println("-----------------------------------------------------------------COULD NOT FIND PRODUCT WITH GIVEN ID------------------------------------------------------------------------------");
        }
    }
    public BestBuyLinkedList<Product> searchByProductName(String searchCriteria){
        BestBuyLinkedList<Product> searchResultsBasedOnName = new BestBuyLinkedList<>();
        int searchResultsCount=0;
        for(Product productToSearch: productsArray){
            if(productToSearch.getProductName()!=null && productToSearch.getProductName().toLowerCase().contains(searchCriteria.toLowerCase())) {
                searchResultsCount+=1;
                searchResultsBasedOnName.insert(productToSearch);
            }
        }
        if(searchResultsCount>0){
            GenericTableDisplay.showProductsLinkedList(searchResultsBasedOnName,searchResultsCount);
        }else{
            System.out.println("--------------------------------------------NO PRODUCTS FOUND FOR GIVEN SEARCH QUERY----------------------------------------------------");
        }

        return searchResultsBasedOnName;
    }

    public BestBuyLinkedList<Product> searchProductBySubCategory(String productSearchCriteria){
        int indexOfSearch = binarySearchProduct(productSearchCriteria);
        int countOfSearchResults=0;
        BestBuyLinkedList<Product> searchResultsForProducts = new BestBuyLinkedList<>();
        if(indexOfSearch!=-1){

            //if it is the last index , search backwards for multiple records
            if(indexOfSearch==(productsArray.length-1)){
                countOfSearchResults+=1;
                searchResultsForProducts.insert(productsArray[indexOfSearch]);
                indexOfSearch=-1;
                while(indexOfSearch>=0 && productsArray[indexOfSearch].getProductSubCategory().equalsIgnoreCase(productSearchCriteria)){
                   searchResultsForProducts.insert(productsArray[indexOfSearch]);
                   indexOfSearch-=1;
                   countOfSearchResults+=1;
                }
         }else{
                countOfSearchResults+=1;
                searchResultsForProducts.insert(productsArray[indexOfSearch]);
                int positiveTraverseIndex = indexOfSearch+1;
                int negativeTraverseIndex = indexOfSearch-1;
                while(negativeTraverseIndex>=0 && productsArray[negativeTraverseIndex].getProductSubCategory().equalsIgnoreCase(productSearchCriteria)){
                    countOfSearchResults+=1;
                    searchResultsForProducts.insert(productsArray[negativeTraverseIndex]);
                     negativeTraverseIndex-=1;
                }
                while(positiveTraverseIndex<productsArray.length && productsArray[positiveTraverseIndex].getProductSubCategory().equalsIgnoreCase(productSearchCriteria)){
                    countOfSearchResults+=1;
                    searchResultsForProducts.insert(productsArray[positiveTraverseIndex]);
                    positiveTraverseIndex+=1;
                }
         }
        }
        if(countOfSearchResults>0) {
            GenericTableDisplay.showProductsLinkedList(searchResultsForProducts, countOfSearchResults);
        }else{
            System.out.println("--------------------------------------------NO PRODUCTS FOUND FOR GIVEN SEARCH QUERY----------------------------------------------------");
        }
        return searchResultsForProducts;
    }

    private int   binarySearchProduct(String productSearchCriteria){
        int l = 0, r = productsArray.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            int res = productSearchCriteria.trim().compareTo(productsArray[m].getProductSubCategory().trim());

            // Check if x is present at mid
            if (res == 0)
                return m;

            // If x greater, ignore left half
            if (res > 0)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        return -1;
    }

}
