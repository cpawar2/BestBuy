package bestbuy.model;


import java.util.Date;

public class Product {
    private String productID;
    private String productName;
    private String productCategory;

    private String productSubCategory;

    private String productDescription;

    private double productPrice;
    private String manufacturer;

    private int inventory;

    private double discountPercent;

    private Date   productAddedDate;

    private Date   productUpdatedDate;

    public static String headers [] ={"PRODUCT_ID","PRODUCT_NAME","PRODUCT_CATEGORY","PRODUCT_PRICE"};

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public Date getProductAddedDate() {
        return productAddedDate;
    }

    public void setProductAddedDate(Date productAddedDate) {
        this.productAddedDate = productAddedDate;
    }

    public Date getProductUpdatedDate() {
        return productUpdatedDate;
    }

    public void setProductUpdatedDate(Date productUpdatedDate) {
        this.productUpdatedDate = productUpdatedDate;
    }

    @Override
    public String toString() {
       return productID +" "+ productSubCategory;
    }
}
