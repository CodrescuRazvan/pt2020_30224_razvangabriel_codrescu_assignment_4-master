package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.MenuItem;

public class ChefFrame extends UniversalFrame implements Observer {

    private static ArrayList<MenuItem> chefs;

    private static JScrollPane scrollPane;

    private static DefaultTableModel menuTable;

    private static JTable table;

    private static Font write;
    private static Color col;

    public ChefFrame(String title) {
        super(title);

        contentPanel.setLayout(null);
        write = writing;
        col = background;
        chefs = null;

    }

    @Override
    public void update(Observable observable, Object o) {
        chefs = (ArrayList<MenuItem>) o;
    }

    public static void displayTable() {
        menuTable = new DefaultTableModel();

        menuTable.setColumnIdentifiers(new Object[]{"Name", "Price"});

        table = new JTable(menuTable);
        table.getTableHeader().setBackground(col);
        table.getTableHeader().setFont(write);
        table.setFont(write);
        table.setBackground(col);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 60, 500, 600);
        scrollPane.setVisible(true);
        contentPanel.add(scrollPane);

        if (chefs != null) {
            int i = 0;
            for (MenuItem menuItem : chefs) {
                Object[] line = new Object[2];

                line[0] = menuItem.getName();
                line[1] = menuItem.getPrice();

                menuTable.addRow(line);
            }
        }
    }
}
