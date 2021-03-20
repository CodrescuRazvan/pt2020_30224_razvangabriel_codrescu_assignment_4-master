package model;

import java.util.ArrayList;

public interface IRestaurantProcessing {

        void createMenuItem(MenuItem menuItemModel);
        void deleteMenuItem(MenuItem menuItemModel);
        void updateMenuItem(MenuItem menuItem, MenuItem menuItem2);

        void createOrder(Order orderModel, ArrayList<MenuItem> menuItemModels);
        void computePrice(Order orderModel);
        void generateBill(Order orderModel);
}
