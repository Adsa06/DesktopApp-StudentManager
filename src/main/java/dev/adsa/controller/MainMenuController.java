package dev.adsa.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainMenuController {

    @FXML
    private BorderPane menuBorderPane;

    @FXML
    private void showDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/DialogUpdateView.fxml")
            );
            DialogPane pane = loader.load();

            DialogUpdateController controller = loader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();

            dialog.setDialogPane(pane);

            // OWNER + MODALIDAD CORRECTA
            Stage owner = (Stage) menuBorderPane.getScene().getWindow();
            dialog.initOwner(owner);
            dialog.initModality(Modality.APPLICATION_MODAL);


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
