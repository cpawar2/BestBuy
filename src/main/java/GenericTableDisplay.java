import com.bethecoder.ascii_table.ASCIITable;
//Class to display tables in CLI
public class GenericTableDisplay {

    public static void showProductsLinkedList(BestBuyLinkedList bestBuyLinkedList,int countOfNodes){
        String headers [] =  {"PRODUCT_ID","PRODUCT_NAME","PRODUCT_SUB_CATEGORY","PRODUCT_DESCRIPTION","PRODUCT_PRICE"};
        String[][] masterProductList = new String[countOfNodes][headers.length];
        int rowCount=0;

        BestBuyNode temp=bestBuyLinkedList.head;
        while(temp!=null){
            Product productToTraverse = (Product) temp.data;
            masterProductList[rowCount][0]=productToTraverse.getProductID();
            masterProductList[rowCount][1]=productToTraverse.getProductName();
            masterProductList[rowCount][2]=productToTraverse.getProductSubCategory();
            masterProductList[rowCount][3]=productToTraverse.getProductDescription();
            masterProductList[rowCount][4]=productToTraverse.getProductPrice()+" $";
            rowCount+=1;
            temp=temp.next;
        }
        ASCIITable.getInstance().printTable(headers, masterProductList);

    }
}
