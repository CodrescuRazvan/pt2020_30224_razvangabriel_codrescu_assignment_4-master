package controller;

import com.sun.tools.javac.Main;
import model.*;
import model.MenuItem;
import view.AddItemFrame;
import view.AdministratorFrame;
import view.EditItemFrame;
import view.FrameStack;
import Main.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdministratorController {

    protected AdministratorFrame frame;
    private String serializationFile;

    public AdministratorController(AdministratorFrame frame, String serializationFile){
        this.frame = frame;
        this.serializationFile = serializationFile;
        frame.setAddItemActionListener(new AddItemButtonActionListener());
        frame.setBackButtonActionListener(new BackButtonActionListener());
        frame.setEditItemActionListener(new EditItemButtonActionListener());
        frame.setDeleteItemActionListener(new DeleteItemButtonActionListener());
    }

    private class BackButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            FrameStack.getInstance().pop();
        }
    }

    private class AddItemButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new AddItemController(new AddItemFrame("Add New Item"), serializationFile);
        }
    }

    private class EditItemButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new EditItemController(new EditItemFrame("Edit Item"), serializationFile);
        }
    }

    private class DeleteItemButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int [] rows = AdministratorFrame.getTable().getSelectedRows();

            for(int row : rows){
                for(MenuItem menuItem : Start.restaurant.getMenuItem()){
                    if(menuItem.getName().equals(AdministratorFrame.getTable().getModel().getValueAt(row, 0))){
                        Start.restaurant.deleteMenuItem(menuItem);
                        break;
                    }
                }
            }
            FrameStack.getInstance().pop();
            new AdministratorController(new AdministratorFrame("Administrator"), serializationFile);
        }
    }
}
