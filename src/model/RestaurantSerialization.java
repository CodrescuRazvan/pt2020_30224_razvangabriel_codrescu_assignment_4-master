package model;

import java.io.*;
import java.util.ArrayList;

public class RestaurantSerialization {
    public ArrayList<MenuItem> read(String serFile) {
        ArrayList<MenuItem> menuItem = null;

        try {
            FileInputStream file = new FileInputStream(serFile);
            ObjectInputStream inputStream = new ObjectInputStream(file);
            menuItem = (ArrayList<MenuItem>) inputStream.readObject();
            inputStream.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return menuItem;
    }

    public void write(ArrayList<MenuItem> menuItems, String serFile) {
        try {
            FileOutputStream file = new FileOutputStream(serFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(file);
            outputStream.writeObject(menuItems);
            outputStream.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
