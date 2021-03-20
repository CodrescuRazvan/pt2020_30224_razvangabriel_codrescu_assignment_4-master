package controller;

import Main.Start;
import model.BaseProduct;
import model.CompositeProduct;
import model.MenuItem;
import view.AddItemFrame;
import view.AdministratorFrame;
import view.FrameStack;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddItemController {

    private String serializationFile;
    protected AddItemFrame frame;

    public AddItemController(AddItemFrame frame, String serializationFile){
        this.frame = frame;
        this.serializationFile = serializationFile;
        frame.setBackButtonActionListener(new BackButtonActionListener());
        frame.setBaseProductChangeListener(new BaseProductChangeListener());
        frame.setCompositeProductChangeListener(new CompositeProductChangeListener());
        frame.setSubmitActionListener(new SubmitButtonActionListener());
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
            if(AddItemFrame.getBaseProduct().isSelected()){
                AddItemFrame.getNameProd().setVisible(true);
                AddItemFrame.getPrice().setVisible(true);
                AddItemFrame.getNameField().setVisible(true);
                AddItemFrame.getPriceField().setVisible(true);
                AddItemFrame.getScrollPane().setVisible(false);
                AddItemFrame.getCompositeProduct().setSelected(false);
            }
        }
    }

    public class CompositeProductChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            if(AddItemFrame.getCompositeProduct().isSelected()){
                AddItemFrame.getPriceField().setVisible(false);
                AddItemFrame.getPrice().setVisible(false);
                AddItemFrame.getNameProd().setVisible(true);
                AddItemFrame.getNameField().setVisible(true);
                AddItemFrame.displayTable();
                AddItemFrame.getBaseProduct().setSelected(false);
            }
        }
    }

    public class SubmitButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(AddItemFrame.getBaseProduct().isSelected()){
                BaseProduct baseProduct = new BaseProduct(AddItemFrame.getNameInput(),Float.valueOf(AddItemFrame.getPriceInput()));
                Start.restaurant.createMenuItem(baseProduct);
            }
            else if(AddItemFrame.getCompositeProduct().isSelected()){
                ArrayList<MenuItem> menuItems = Start.restaurant.getMenuItem();
                int[] rows = AddItemFrame.getTable().getSelectedRows();
                int price = 0;
                CompositeProduct compositeProduct = new CompositeProduct(AddItemFrame.getNameInput(), price);
                ArrayList<MenuItem> menuItemArrayList = new ArrayList<>();
                for(int row : rows){
                    for(MenuItem menuItem : menuItems){
                        if(menuItem.getName().equals(AddItemFrame.getTable().getModel().getValueAt(row, 0))){
                            if(menuItem instanceof BaseProduct){
                                menuItemArrayList.add(menuItem);
                                price += menuItem.getPrice();
                            }
                            else{
                                for(MenuItem menuItem1 : ((CompositeProduct) menuItem).getCompositeProducts()){
                                    menuItemArrayList.add(menuItem1);
                                    price += menuItem1.getPrice();
                                }
                            }
                        }
                    }
                }
                compositeProduct.setCompositeProducts(menuItemArrayList);
                compositeProduct.setPrice(price);
                Start.restaurant.createMenuItem(compositeProduct);
            }
            FrameStack.getInstance().pop();
            FrameStack.getInstance().pop();
            new AdministratorController(new AdministratorFrame("Administrator"), serializationFile);
        }
    }
}
