package model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int table;
    private Date date = new Date();
    private float price = -1;

    public Order(int table) {
        this.table = table;
    }

    public int getTable() {
        return table;
    }

    public Date getDate() {
        return date;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order)) {
            return false;
        }

        Order order = (Order) o;

        if (this.table != order.table) {
            return false;
        }

        if (this.date != order.date) {
            return false;
        }

        return true;
    }
}
