package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InitialFrame extends UniversalFrame{

    private static JButton administrator;
    private static JButton waiter;
    private static JButton chef;

    public InitialFrame(String title) {
        super(title);

        contentPanel.setLayout(null);

        administrator = new JButton("Administrator");
        administrator.setBackground(background);
        administrator.setBounds(340, 225, 200, 75);
        administrator.setFont(writing);
        administrator.setVisible(true);
        contentPanel.add(administrator);

        waiter = new JButton("Waiter");
        waiter.setBackground(background);
        waiter.setBounds(340, 325, 200, 75);
        waiter.setFont(writing);
        waiter.setVisible(true);
        contentPanel.add(waiter);

        chef = new JButton("Chef");
        chef.setBackground(background);
        chef.setBounds(340, 425, 200, 75);
        chef.setFont(writing);
        chef.setVisible(true);
        contentPanel.add(chef);
    }

    public void setAdministratorActionListener(ActionListener actionListener){
        administrator.addActionListener(actionListener);
    }

    public void setWaiterActionListener(ActionListener actionListener){
        waiter.addActionListener(actionListener);
    }

    public void setChefActionListener(ActionListener actionListener){
        chef.addActionListener(actionListener);
    }

    public static JButton getAdministratorButton(){
        return administrator;
    }

    public static JButton getWaiterButton(){
        return waiter;
    }

    public static JButton getChefButton(){
        return chef;
    }


}
