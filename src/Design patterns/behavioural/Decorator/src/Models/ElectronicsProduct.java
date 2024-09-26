package Models;

public class ElectronicsProduct implements Product {
    public double getPrice() {
        return 100;
    }

    public ProductType getProductType() {
        return ProductType.Electronics;
    }
}
