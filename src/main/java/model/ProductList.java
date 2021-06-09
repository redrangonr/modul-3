package model;

public class ProductList {
    private int id;
    private String ProductName;
    private float Price;
   private int Quantity;
   private String Color ;
   private String Category;

    public ProductList(int id, String productName, float price, int quantity, String color, String category) {
        this.id = id;
        ProductName = productName;
        Price = price;
        Quantity = quantity;
        Color = color;
        Category = category;
    }
    public ProductList(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
