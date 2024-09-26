import Decorator.PercentageCouponDecorator;
import Decorator.TypeCouponDecorator;
import Models.ClothingProduct;
import Models.ElectronicsProduct;
import Models.Product;

public class Main {
    private static double getProductPriceAfterDiscount(Product product) {
        Product productAfterDiscount = new PercentageCouponDecorator(new TypeCouponDecorator(product, 20),10);
        return productAfterDiscount.getPrice();
    }
    public static void main(String[] args) {
        Product product1 = new ElectronicsProduct();
        Product product2 = new ClothingProduct();

        double discountedPrice1 = getProductPriceAfterDiscount(product1);
        double discountedPrice2 = getProductPriceAfterDiscount(product2);

        System.out.println(discountedPrice1);
        System.out.println(discountedPrice2);
    }
}