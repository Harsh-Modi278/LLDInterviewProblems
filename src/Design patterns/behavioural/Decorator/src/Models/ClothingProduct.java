package Models;

public class ClothingProduct implements Product{
    public double getPrice() {
        return 200;
    }

    public ProductType getProductType() {
        return ProductType.Clothing;
    }
}
