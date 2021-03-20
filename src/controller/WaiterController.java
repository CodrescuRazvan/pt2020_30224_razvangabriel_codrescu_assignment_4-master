package controller;

import Main.Start;
import model.Order;
import view.FrameStack;
import view.OrderFrame;
import view.WaiterFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.MenuItem;

public class WaiterController {

    protected WaiterFrame frame;
    private String serializationFile;

    public WaiterController(WaiterFrame frame, String serializationFile){
        this.frame = frame;
        this.serializationFile = serializationFile;
        frame.setCreateOrderActionListener(new NewOrderActionListener());
        frame.setBackButtonActionListener(new BackButtonActionListener());
        frame.setComputePriceActionListener(new ComputePriceActionListener());
        frame.setGenerateBillActionListener(new GenerateBillActionListener());
    }

    private class BackButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            FrameStack.getInstance().pop();
        }
    }

    private class NewOrderActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new NewOrderController(new OrderFrame("New Order"), serializationFile);
        }
    }

    private class ComputePriceActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int[] rows = WaiterFrame.getTable().getSelectedRows();
            for(int row : rows){
                for(Order order : Start.restaurant.getOrder().keySet()){
                    if(order.getTable() == Integer.parseInt(String.valueOf(WaiterFrame.getTable().getModel().getValueAt(row, 0)))){
                        Start.restaurant.computePrice(order);
                    }
                }
            }
            FrameStack.getInstance().pop();
            new WaiterController(new WaiterFrame("Waiter"), serializationFile);
        }
    }

    public class GenerateBillActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int[] rows = WaiterFrame.getTable().getSelectedRows();
            for(int row : rows){
                for(Order order : Start.restaurant.getOrder().keySet()){
                    if(order.getTable() == Integer.parseInt(String.valueOf(WaiterFrame.getTable().getModel().getValueAt(row, 0)))){
                        Start.restaurant.generateBill(order);
                    }
                }
            }
        }
    }
}
