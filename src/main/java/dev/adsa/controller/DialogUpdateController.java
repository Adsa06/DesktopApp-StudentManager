package dev.adsa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DialogUpdateController {

    @FXML
    private Label lblTitleName;
    
    @FXML
    public void initialize() {
        lblTitleName.setText("Modifica los datos de Generico");
    }

    public void setData(String name) {
        lblTitleName.setText("Modifica los datos de " + name);
    }

}
