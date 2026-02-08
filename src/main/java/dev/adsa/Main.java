package dev.adsa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Clase principal */
public class Main extends Application {

    
    /**
     * Punto de entrada principal de la aplicación.
     * Este método se encarga de llamar al método launch de la clase Application
     * con los argumentos pasados por parámetro.
     * 
     * @param args los argumentos pasados por parámetro
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Inicia la interfaz de usuario gráfica de la aplicación.
     * 
     * @param primaryStage la ventana principal de la aplicación
     * @throws Exception si se produce un error al cargar la interfaz de usuario
     */
    @Override
    public void start(Stage primaryStage) throws Exception {    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_menu_view.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Student Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.minWidthProperty().set(1280);
        primaryStage.minHeightProperty().set(720);
        primaryStage.show();
    }
}
