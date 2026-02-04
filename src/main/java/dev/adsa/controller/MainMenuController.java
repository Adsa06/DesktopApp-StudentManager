package dev.adsa.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
public class MainMenuController {

    @FXML
    private void showDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/DialogUpdateView.fxml")
            );
            DialogPane pane = loader.load();
            DialogUpdateController controller = loader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);

            controller.setData("Adsa");

            Optional<ButtonType> result = dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void closeApp() {
        Platform.exit();
        System.exit(0);
    }
}
