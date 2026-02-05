package dev.adsa.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainMenuController {

    @FXML
    private BorderPane menuBorderPane;

    @FXML
    private AnchorPane overlay;

    @FXML
    public void initialize() {
    }

    @FXML
    private void showDialog() {
        overlay.setVisible(true);
        overlay.setDisable(false);
        menuBorderPane.setDisable(true);
    }

    @FXML
    private void closeDialog() {
        overlay.setVisible(false);
        menuBorderPane.setDisable(false);
        overlay.setDisable(true);
    }
    
    @FXML
    private void closeApp() {
        Platform.exit();
        System.exit(0);
    }
}
