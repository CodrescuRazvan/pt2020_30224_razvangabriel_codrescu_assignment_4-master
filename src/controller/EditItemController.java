package controller;

import Main.Start;
import model.BaseProduct;
import model.CompositeProduct;
import view.AdministratorFrame;
import view.EditItemFrame;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.MenuItem;
import view.FrameStack;

public class EditItemController {

    protected EditItemFrame frame;
    public String serializationFile;

    public EditItemController(EditItemFrame frame, String serializationFile){
        this.frame = frame;
        this.serializationFile = serializationFile;
        frame.setBaseProductChangeListener(new BaseProductChangeListener());
        frame.setCompositeProductChangeListener(new CompositeProductChangeListener());
        frame.setSubmitActionListener(new SubmitActionListener());
        frame.setBackButtonActionListener(new BackButtonActionListener());
    }

    public class BackButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            FrameStack.getInstance().pop();
        }
    }

    public class BaseProductChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if(EditItemFrame.getBaseProduct().isSelected()){
                EditItemFrame.getPrice().setVisible(true);
                EditItemFrame.getNameProd().setVisible(true);
                EditItemFrame.getPriceField().setVisible(true);
                EditItemFrame.getNameField().setVisible(true);
                EditItemFrame.getScrollPane().setVisible(false);
                EditItemFrame.displayTable();
                EditItemFrame.getCompositeProduct().setSelected(false);
            }
        }
    }

    public class CompositeProductChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if(EditItemFrame.getCompositeProduct().isSelected()){
                EditItemFrame.displayTable();
                EditItemFrame.getNameProd().setVisible(true);
                EditItemFrame.getNameField().setVisible(true);
                EditItemFrame.getPriceField().setVisible(false);
                EditItemFrame.getPrice().setVisible(false);
                EditItemFrame.getBaseProduct().setSelected(false);
            }
        }
    }

    public class SubmitActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ArrayList<MenuItem> menuItems = Start.restaurant.getMenuItem();
            if(EditItemFrame.getBaseProduct().isSelected()){
                MenuItem oldItem = menuItems.get(EditItemFrame.getTable().getSelectedRow());
                MenuItem newItem = new BaseProduct(" ", 0);
                newItem.setName(EditItemFrame.getNameInput());
                newItem.setPrice(Float.parseFloat(EditItemFrame.getPriceInput()));
                Start.restaurant.updateMenuItem(oldItem, newItem);
            }
            else if(EditItemFrame.getCompositeProduct().isSelected()){
                String name = EditItemFrame.getNameInput();
                CompositeProduct compositeProduct = new CompositeProduct(name, 0);
                int[] rows = EditItemFrame.getTable().getSelectedRows();
                ArrayList<MenuItem> menuItemArrayList = new ArrayList<>();
                for(int row : rows){
                    for(MenuItem menuItem : menuItems){
                        if(menuItem.getName().equals(EditItemFrame.getTable().getModel().getValueAt(row, 0))){
                            menuItemArrayList.add(menuItem);
                        }
                    }
                }
                compositeProduct.setCompositeProducts(menuItemArrayList);
                compositeProduct.setPrice(compositeProduct.computePrice());

                Start.restaurant.updateMenuItem(menuItems.get(EditItemFrame.getTable().getSelectedRow()), compositeProduct);
            }
            FrameStack.getInstance().pop();
            FrameStack.getInstance().pop();
            new AdministratorController(new AdministratorFrame("Administrator"), serializationFile);
        }
    }
}
