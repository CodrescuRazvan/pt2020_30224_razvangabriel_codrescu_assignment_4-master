package controller;

import view.ChefFrame;

public class ChefController {

    protected ChefFrame frame;
    private String serializationFile;

    public ChefController(ChefFrame frame, String serializationFile){
        this.frame = frame;
        this.serializationFile = serializationFile;
    }
}
