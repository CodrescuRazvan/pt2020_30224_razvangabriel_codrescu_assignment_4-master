package controller;

import Main.Start;
import model.MenuItem;
import model.Order;
import view.FrameStack;
import view.OrderFrame;
import view.WaiterFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewOrderController {

    protected OrderFrame frame;
    private String serializationFile;

    public NewOrderController(OrderFrame frame, String serializationFile) {
        this.frame = frame;
        this.serializationFile = serializationFile;
        frame.setSubmitActionListener(new NewOrderActionListener());
    }

    public class NewOrderActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList<MenuItem> menuItems = Start.restaurant.getMenuItem();
            ArrayList<MenuItem> orderedItems = new ArrayList<>();
            int[] rows = OrderFrame.getTable().getSelectedRows();
            int table = Integer.parseInt(OrderFrame.getOrdererTableInput());
            for(int row : rows){
                for(MenuItem menuItem : menuItems){
                    if(menuItem.getName().equals(OrderFrame.getTable().getModel().getValueAt(row, 0))){
                        orderedItems.add(menuItem);
                    }
                }
            }
            Start.restaurant.createOrder(new Order(table), orderedItems);
            FrameStack.getInstance().pop();
            FrameStack.getInstance().pop();
            new WaiterController(new WaiterFrame("Waiter"), serializationFile);
        }
    }
}
