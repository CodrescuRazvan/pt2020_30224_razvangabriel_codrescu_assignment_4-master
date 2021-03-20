package view;

import model.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import model.MenuItem;

public class AdministratorFrame extends UniversalFrame{

    private static JButton addItem;
    private static JButton editItem;
    private static JButton deleteItem;
    private static JButton backButton;
    //private static JButton submitAddButton;
    //private static JButton submitEditButton;
    //private static JButton submitDelButton;

    //private static JCheckBox baseProduct;
    //private static JCheckBox compositeProduct;

    //private static JLabel name;
    //private static JLabel price;

    //private static JTextField nameField;
    //private static JTextField priceField;

    private static DefaultTableModel tableMenu;

    private static JTable table;

    private static JScrollPane scrollPane;

    private static Font write;
    private static Color col;

    private JPanel panelRight;

    public AdministratorFrame(String title){
        super(title);

        this.write = writing;
        this.col  = background;

        contentPanel.setLayout(null);

        addItem = new JButton("Add New Item");
        addItem.setFont(writing);
        addItem.setHorizontalAlignment(SwingConstants.CENTER);
        addItem.setBackground(background);
        addItem.setBounds(10, 60, 180, 50);
        addItem.setVisible(true);
        contentPanel.add(addItem);

        editItem = new JButton("Edit Existing Item");
        editItem.setFont(writing);
        editItem.setHorizontalAlignment(SwingConstants.CENTER);
        editItem.setBackground(background);
        editItem.setBounds(10, 130, 180, 50);
        editItem.setVisible(true);
        contentPanel.add(editItem);

        deleteItem = new JButton("Delete Item");
        deleteItem.setFont(writing);
        deleteItem.setHorizontalAlignment(SwingConstants.CENTER);
        deleteItem.setBackground(background);
        deleteItem.setBounds(10, 200, 180, 50);
        deleteItem.setVisible(true);
        contentPanel.add(deleteItem);

        backButton = new JButton("Back");
        backButton.setFont(writing);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setBackground(background);
        backButton.setBounds(10, 270, 180, 50);
        backButton.setVisible(true);
        contentPanel.add(backButton);

        displayTable();
    }

    public static void displayTable(){
        tableMenu = new DefaultTableModel();

        tableMenu.setColumnIdentifiers(new Object[] {"Name", "Price"});

        table = new JTable(tableMenu);
        table.setVisible(true);
        table.getTableHeader().setBackground(col);
        table.getTableHeader().setFont(write);
        table.setFont(write);
        table.setBackground(col);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 60, 500, 600);
        scrollPane.setVisible(true);
        contentPanel.add(scrollPane);

        for (MenuItem menuItem : Restaurant.getMenuItem()) {
            Object[] line = new Object[2];

            line[0] = menuItem.getName();
            line[1] = menuItem.getPrice();

            tableMenu.addRow(line);
        }
    }

    public void setBackButtonActionListener(ActionListener actionListener){
        backButton.addActionListener(actionListener);
    }

    public void setAddItemActionListener(ActionListener actionListener){
        addItem.addActionListener(actionListener);
    }

    public void setEditItemActionListener(ActionListener actionListener){
        editItem.addActionListener(actionListener);
    }

    public void setDeleteItemActionListener(ActionListener actionListener){
        deleteItem.addActionListener(actionListener);
    }

    public static JTable getTable(){
        return table;
    }

    public static JScrollPane getScrollPane(){
        return scrollPane;
    }

    public static JButton getBackButton(){
        return backButton;
    }

    public static JButton getAddItem(){
        return addItem;
    }

    public static JButton getEditItem(){
        return editItem;
    }

    public static JButton getDeleteItem(){
        return deleteItem;
    }

}
