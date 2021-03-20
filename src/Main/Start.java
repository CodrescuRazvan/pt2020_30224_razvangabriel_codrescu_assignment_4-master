package Main;

import controller.InitialController;
import model.Restaurant;
import view.InitialFrame;

public class Start {

    public static Restaurant restaurant;

    public static void main(String[] args) {
        restaurant = new Restaurant(args[0]);
        new InitialController(new InitialFrame("Choosing Frame"), args[0]);
    }
}
