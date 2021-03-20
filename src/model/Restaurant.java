package model;

import java.util.Observable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Restaurant extends Observable implements IRestaurantProcessing {
    private static ArrayList<MenuItem> menuItems;
    private static HashMap<Order, ArrayList<MenuItem>> orders;
    private RestaurantSerialization rs = new RestaurantSerialization();
    private String serFile;

    public Restaurant(String serFile) {
        orders = new HashMap<>();

        menuItems = rs.read(serFile);
        this.serFile = serFile;

        if (menuItems == null) {
            menuItems = new ArrayList<>();
        }
    }


    public static ArrayList<MenuItem> getMenuItem() {
        return menuItems;
    }

    public static HashMap<Order, ArrayList<MenuItem>> getOrder() {
        return orders;
    }

    /**
     * Adaugarea de produse in meniu
     *
     * @param menuItem - produsul care va fi adaugat
     * @pre - menuItem != null
     * @pre - !menuItems.contains(menuItem)
     * @post -  size() == size()@pre + 1
     */
    @Override
    public void createMenuItem(MenuItem menuItem) {
        assert menuItem != null;
        assert !menuItems.contains(menuItem);
        int size = menuItems.size();

        menuItems.add(menuItem);
        rs.write(menuItems, serFile);

        assert menuItems.size() == size + 1;
        assert invariantCheck();
    }

    /**
     * Aici se va realiza stergerea unui produs dar si a tuturor produselor care contin
     * produsul respectiv
     *
     * @param menuItem - produsul care trebuie sters
     * @pre - menuItem != null
     * @pre - menuItems.contains(menuItem)
     * @post -  size() == size()@pre - 1
     */
    @Override
    public void deleteMenuItem(MenuItem menuItem) {
        assert menuItem != null;
        assert menuItems.contains(menuItem);
        int size = menuItems.size();

        ArrayList<MenuItem> deleteMenu = new ArrayList<>();
        for (MenuItem menu : menuItems) {
            if (menu.getName().equals(menuItem.getName())) {
                deleteMenu.add(menuItem);
            }
            if (menu instanceof CompositeProduct) {
                for (MenuItem menuItem1 : ((CompositeProduct) menu).getCompositeProducts()) {
                    if (menuItem1.getName().equals(menuItem.getName())) {
                        deleteMenu.add(menu);
                    }
                }
            }
        }

        if (deleteMenu.size() > 0) {
            for (MenuItem deleteMenus : deleteMenu) {
                menuItems.remove(deleteMenus);
            }
        }

        rs.write(menuItems, serFile);

        assert menuItems.size() == size - 1;
        assert invariantCheck();
    }

    /**
     * Aici se realizeaza editarea informatiilor despre un produs
     *
     * @param menuItem  - produsul vechi
     * @param menuItem2 - produsul nou
     * @pre - menuItem != null
     * @pre - menuItems.contains(menuItem)
     * @post -  size() == size()@pre
     */
    @Override
    public void updateMenuItem(MenuItem menuItem, MenuItem menuItem2) {
        assert menuItem != null;
        assert menuItems.contains(menuItem);
        int size = menuItems.size();

        MenuItem updateMenu = null;
        for (MenuItem menu : menuItems) {
            if (menu.getName().equals(menuItem.getName())) {
                updateMenu = menu;
            }
        }

        if (updateMenu instanceof BaseProduct) {
            updateMenu.setName(menuItem2.getName());
            updateMenu.setPrice(menuItem2.getPrice());
        } else {
            updateMenu.setName(menuItem2.getName());
            ((CompositeProduct) updateMenu).setCompositeProducts(((CompositeProduct) menuItem2).getCompositeProducts());
            updateMenu.setPrice(updateMenu.computePrice());
        }

        for (MenuItem menu : menuItems) {
            menu.setPrice(menu.computePrice());
        }

        rs.write(menuItems, serFile);

        assert menuItems.size() == size;
        assert invariantCheck();
    }

    /**
     * Aici se va realiza crearea unei noi comenzi. Se vor pune comenzile si Cheful va fi
     * notificat ca a fost facuta o comanda.
     *
     * @param order    - comanda
     * @param menuItem - produsele care vor fi comandate
     */
    @Override
    public void createOrder(Order order, ArrayList<MenuItem> menuItem) {
        orders.put(order, menuItem);
        setChanged();
        notifyObservers(menuItem);
    }

    /**
     * Calcularea pretului
     *
     * @param order - pozitia la care se afla comanda in HasMap
     */
    @Override
    public void computePrice(Order order) {

        for (Order order1 : orders.keySet()) {
            if (order1.equals(order)) {
                float price = 0;
                for (MenuItem menuItem : orders.get(order1)) {
                    price += menuItem.getPrice();
                }
                order1.setPrice(price);
            }
        }
    }

    /**
     * Aici se realizeaza generarea bonului pentru o comanda
     *
     * @param order - pozitia la care se afla comanda in HasMap
     *              se va scrie intr-un fisier de tipul .txt cu numele bill si se vor afisa fiecare produs
     *              impreuna cu pretul sau si totalul
     */
    @Override
    public void generateBill(Order order) {
        for (Order order1 : orders.keySet()) {
            if (order1.equals(order)) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("bill" + order1.getTable() + ".txt", false));
                    bw.write("Products : Price");
                    bw.newLine();

                    for (MenuItem menuItem : orders.get(order1)) {
                        bw.write(menuItem.getName() + " : " + menuItem.getPrice());
                        bw.newLine();
                    }

                    bw.write("Total: " + order1.getPrice());
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean invariantCheck() {
        return menuItems.contains(null);
    }
}
