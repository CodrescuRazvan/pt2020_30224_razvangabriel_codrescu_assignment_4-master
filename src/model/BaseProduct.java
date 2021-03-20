package model;

public class BaseProduct extends MenuItem{

    public BaseProduct(String menuItemName, float price) {
        super(menuItemName, price);
    }

    @Override
    public float computePrice() {
        return getPrice();
    }

}
