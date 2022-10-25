import java.io.IOException;

public class FileReadTest {
    public static void main(String[] args) throws IOException {
        ProductOperationService productOperationService = new ProductOperationService();
        productOperationService.readProductsFromDB();
        productOperationService.displayProductsAvailable();
    }
}
