package Decorator;

import Models.Product;
import Models.ProductType;

import java.util.ArrayList;
import java.util.List;

public class TypeCouponDecorator extends CouponDecorator{
    double percentage;
    static List<ProductType> eligibleProductTypes = new ArrayList<>();
    static {
        eligibleProductTypes.add(ProductType.Clothing);
        eligibleProductTypes.add(ProductType.Mobile);
    }

    public TypeCouponDecorator(Product product, double percentage) {
        super(product);
        this.percentage = percentage;
    }

    @Override
    public double getPrice() {
        double price = product.getPrice();
        if (eligibleProductTypes.contains(product.getProductType())) {
            return price - (price * this.percentage/100);
        }

        return price;
    }
}
