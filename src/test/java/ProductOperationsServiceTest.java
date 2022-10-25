public class ProductOperationsServiceTest {

    public static void main(String[] args) {
        ProductOperationService productOperationService = new ProductOperationService();
        Product p1 = new Product();
        p1.setProductID("1");
        p1.setProductSubCategory("Furniture");
        Product p2 = new Product();
        p2.setProductID("2");
        p2.setProductSubCategory("Laptop");
        Product p3 = new Product();
        p3.setProductID("3");
        p3.setProductSubCategory("Laptop");
        Product p4 = new Product();
        p4.setProductID("4");
        p4.setProductSubCategory("Laptop");
        Product p5 = new Product();
        p5.setProductName("Stradyvirus voilin");
        p5.setProductID("5");
        p5.setProductSubCategory("Voilins");
        productOperationService.productsArray = new Product[5];
        productOperationService.productsArray[0]=p1;
        productOperationService.productsArray[1]=p2;
        productOperationService.productsArray[2]=p3;
        productOperationService.productsArray[3]=p4;
        productOperationService.productsArray[4]=p5;
        BestBuyLinkedList<Product> searchResults = productOperationService.searchProductBySubCategory("Voilins");
        searchResults.show();


    }
}
