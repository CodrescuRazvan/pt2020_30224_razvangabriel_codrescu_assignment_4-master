package view;

import model.MenuItem;
import model.Restaurant;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditItemFrame extends UniversalFrame{

    private static JButton submit;
    private static JButton backButton;

    private static JCheckBox baseProduct;
    private static JCheckBox compositeProduct;

    private static JScrollPane scrollPane;

    private static DefaultTableModel tableMenu;
    private static JTable table;

    private static JLabel name;
    private static JLabel price;

    private static JTextField nameField;
    private static JTextField priceField;

    private static Color col;
    private static Font write;

    public EditItemFrame(String title) {
        super(title);

        contentPanel.setLayout(null);

        col = background;
        write = writing;

        baseProduct = new JCheckBox("Base Product");
        baseProduct.setFont(writing);
        baseProduct.setBackground(back);
        baseProduct.setBounds(10, 10, 180, 50);
        baseProduct.setVisible(true);
        contentPanel.add(baseProduct);

        compositeProduct = new JCheckBox("Composite Product");
        compositeProduct.setFont(writing);
        compositeProduct.setBackground(back);
        compositeProduct.setBounds(10, 80, 250, 50);
        compositeProduct.setVisible(true);
        contentPanel.add(compositeProduct);

        name = new JLabel("Name ");
        name.setFont(writing);
        name.setBackground(background);
        name.setBounds(10, 150, 180, 30);
        name.setVisible(false);
        contentPanel.add(name);

        nameField = new JTextField();
        nameField.setFont(writing);
        nameField.setBounds(80, 150, 180, 30);
        nameField.setVisible(false);
        contentPanel.add(nameField);

        price = new JLabel("Price ");
        price.setFont(writing);
        price.setBounds(10, 220, 180, 30);
        price.setVisible(false);
        contentPanel.add(price);

        priceField = new JTextField();
        priceField.setFont(writing);
        priceField.setBounds(80, 220, 180, 30);
        priceField.setVisible(false);
        contentPanel.add(priceField);

        submit = new JButton("Submit");
        submit.setBounds(10, 700, 180, 50);
        submit.setFont(writing);
        submit.setBackground(background);
        submit.setVisible(true);
        contentPanel.add(submit);

        backButton = new JButton("Back");
        backButton.setBounds(650, 700, 180, 50);
        backButton.setFont(writing);
        backButton.setBackground(background);
        backButton.setVisible(true);
        contentPanel.add(backButton);

        scrollPane = new JScrollPane();
        contentPanel.add(scrollPane);
    }

    public static void displayTable(){
        contentPanel.remove(scrollPane);
        tableMenu = new DefaultTableModel();

        tableMenu.setColumnIdentifiers(new Object[] {"Name", "Price"});

        table = new JTable(tableMenu);
        table.setFont(write);
        table.setBackground(col);
        table.getTableHeader().setBackground(col);
        table.getTableHeader().setFont(write);
        table.setVisible(true);

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

    public void setBaseProductChangeListener(ChangeListener changeListener){
        baseProduct.addChangeListener(changeListener);
    }

    public void setCompositeProductChangeListener(ChangeListener changeListener){
        compositeProduct.addChangeListener(changeListener);
    }

    public void setSubmitActionListener(ActionListener actionListener){
        submit.addActionListener(actionListener);
    }

    public void setBackButtonActionListener(ActionListener actionListener){
        backButton.addActionListener(actionListener);
    }

    public static JButton getSubmit() {
        return submit;
    }

    public static JCheckBox getBaseProduct() {
        return baseProduct;
    }

    public static JCheckBox getCompositeProduct() {
        return compositeProduct;
    }

    public static JScrollPane getScrollPane() {
        return scrollPane;
    }

    public static DefaultTableModel getTableMenu() {
        return tableMenu;
    }

    public static JTable getTable() {
        return table;
    }

    public static JLabel getNameProd() {
        return name;
    }

    public static JLabel getPrice() {
        return price;
    }

    public static JTextField getNameField() {
        return nameField;
    }

    public static JTextField getPriceField() {
        return priceField;
    }

    public static String getNameInput(){
        return nameField.getText();
    }

    public static String getPriceInput(){
        return priceField.getText();
    }
}
