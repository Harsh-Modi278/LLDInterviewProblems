package Decorator;

import Models.Product;
import Models.ProductType;

public class PercentageCouponDecorator extends CouponDecorator{
    double percentage;

    public PercentageCouponDecorator(Product product, double percentage) {
        super(product);
        this.percentage = percentage;
    }

    @Override
    public double getPrice() {
        double price = product.getPrice();
        return price - (price * this.percentage/100);
    }
}
