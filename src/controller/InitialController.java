package controller;

import view.AdministratorFrame;
import view.ChefFrame;
import view.InitialFrame;
import view.WaiterFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialController {

    protected JFrame frame;
    private String serializationFile;

    public InitialController(InitialFrame frame, String serializationFile){
        this.frame = frame;
        this.serializationFile = serializationFile;
        frame.setAdministratorActionListener(new AdministratorActionListener());
        frame.setWaiterActionListener(new WaiterActionListener());
        frame.setChefActionListener(new ChefActionListener());
    }

    private class AdministratorActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new AdministratorController(new AdministratorFrame("Administrator Frame"), serializationFile);
        }
    }

    private class WaiterActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new WaiterController(new WaiterFrame("Waiter Frame"), serializationFile);
        }
    }

    private class ChefActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new ChefController(new ChefFrame("Chef Frame"), serializationFile);
        }
    }
}
