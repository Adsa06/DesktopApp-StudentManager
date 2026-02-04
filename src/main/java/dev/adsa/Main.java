package dev.adsa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Student Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.minWidthProperty().set(1280);
        primaryStage.minHeightProperty().set(720);
        primaryStage.show();
    }
}
