package Decorator;

import Models.Product;
import Models.ProductType;

public class CouponDecorator implements Product {
    Product product;

    public CouponDecorator(Product product) {
        this.product = product;
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

    @Override
    public ProductType getProductType() {
        return product.getProductType();
    }
}
