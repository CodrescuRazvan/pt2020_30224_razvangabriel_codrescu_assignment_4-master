package view;

import model.MenuItem;
import model.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderFrame extends UniversalFrame{

    private static JScrollPane scrollPane;

    private static JTable table;
    private static DefaultTableModel tableMenu;

    private static JLabel ordererTable;
    private static JTextField ordererTableField;

    private static JButton submit;

    private static Color col;
    private static Font write;

    public OrderFrame(String title) {
        super(title);

        contentPanel.setLayout(null);
        col = background;
        write = writing;

        ordererTable = new JLabel("Table");
        ordererTable.setFont(writing);
        ordererTable.setBackground(back);
        ordererTable.setHorizontalAlignment(SwingConstants.CENTER);
        ordererTable.setBounds(10, 80, 180, 50);
        contentPanel.add(ordererTable);

        ordererTableField = new JTextField();
        ordererTableField.setFont(writing);
        ordererTableField.setBounds(200, 80, 180, 50);
        contentPanel.add(ordererTableField);

        submit = new JButton("Submit");
        submit.setFont(writing);
        submit.setBackground(background);
        submit.setBounds(10, 700, 180, 50);
        submit.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(submit);

        scrollPane = new JScrollPane();
        displayTable();
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
        scrollPane.setBounds(10, 270, 300, 300);
        scrollPane.setVisible(true);
        contentPanel.add(scrollPane);

        for (MenuItem menuItem : Restaurant.getMenuItem()) {
            Object[] line = new Object[2];

            line[0] = menuItem.getName();
            line[1] = menuItem.getPrice();

            tableMenu.addRow(line);
        }
    }

    public void setSubmitActionListener(ActionListener actionListener){
        submit.addActionListener(actionListener);
    }

    public static JTable getTable() {
        return table;
    }

    public static JScrollPane getScrollPane() {
        return scrollPane;
    }

    public static JLabel getOrdererTable() {
        return ordererTable;
    }

    public static String getOrdererTableInput(){
        return ordererTableField.getText();
    }

    public static JTextField getOrdererTableField() {
        return ordererTableField;
    }

    public static JButton getSubmit() {
        return submit;
    }
}
