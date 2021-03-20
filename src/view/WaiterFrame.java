package view;

import model.MenuItem;
import model.Order;
import model.Restaurant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class WaiterFrame extends UniversalFrame{

    private static JButton createOrder;
    private static JButton computePrice;
    private static JButton generateBill;
    private static JButton backButton;

    private static JTable table;

    private static DefaultTableModel tableMenu;

    private static JScrollPane scrollPane;

    private static Font write;
    private static Color col;

    public WaiterFrame(String title) {
        super(title);

        contentPanel.setLayout(null);
        write = writing;
        col = background;

        createOrder = new JButton("Create Order");
        createOrder.setBackground(background);
        createOrder.setBounds(10, 60, 180, 50);
        createOrder.setFont(writing);
        createOrder.setVisible(true);
        contentPanel.add(createOrder);

        computePrice = new JButton("Compute Price");
        computePrice.setBackground(background);
        computePrice.setBounds(10, 130, 180, 50);
        computePrice.setFont(writing);
        computePrice.setVisible(true);
        contentPanel.add(computePrice);

        generateBill = new JButton("Generate Bill");
        generateBill.setBackground(background);
        generateBill.setBounds(10, 200, 180, 50);
        generateBill.setFont(writing);
        generateBill.setVisible(true);
        contentPanel.add(generateBill);

        backButton = new JButton("Back");
        backButton.setFont(writing);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setBackground(background);
        backButton.setBounds(10, 270, 180, 50);
        backButton.setVisible(true);
        contentPanel.add(backButton);

        scrollPane = new JScrollPane();
        contentPanel.add(scrollPane);

        displayTable();
    }

    public static void displayTable(){
        contentPanel.remove(scrollPane);
        tableMenu = new DefaultTableModel();

        tableMenu.setColumnIdentifiers(new Object[] {"Table", "Price", "Date"});

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

        for (Order order: Restaurant.getOrder().keySet()) {
            Object[] line = new Object[3];

            line[0] = order.getTable();
            line[2] = order.getDate();
            if(order.getPrice() == -1){
                line[1] = '-';
            }
            else{
                line[1] = order.getPrice();
            }
            tableMenu.addRow(line);
        }
    }

    public void setCreateOrderActionListener(ActionListener actionListener){
        createOrder.addActionListener(actionListener);
    }

    public void setComputePriceActionListener(ActionListener actionListener){
        computePrice.addActionListener(actionListener);
    }

    public void setGenerateBillActionListener(ActionListener actionListener){
        generateBill.addActionListener(actionListener);
    }

    public void setBackButtonActionListener(ActionListener actionListener){
        backButton.addActionListener(actionListener);
    }

    public static JButton getCreateOrder(){
        return createOrder;
    }

    public static JButton getComputePrice(){
        return computePrice;
    }

    public static JButton getGenerateBill(){
        return generateBill;
    }

    public static JButton getBackButton(){
        return backButton;
    }

    public static JScrollPane getScrollPane(){
        return scrollPane;
    }

    public static JTable getTable(){
        return table;
    }



}
