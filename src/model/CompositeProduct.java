package model;

import java.awt.*;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

    private ArrayList<MenuItem> compositeProducts;

    public CompositeProduct(String name, float price) {
        super(name, price);
    }

    @Override
    public float computePrice() {
        float price = 0;

        for (MenuItem menuItem : this.compositeProducts) {
            price += menuItem.getPrice();
        }

        return price;
    }

    public ArrayList<MenuItem> getCompositeProducts() {
        return compositeProducts;
    }

    public void setCompositeProducts(ArrayList<MenuItem> compositeProducts) {
        this.compositeProducts = compositeProducts;
    }


}
