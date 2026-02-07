package dev.adsa.controller;

import dev.adsa.model.City;
import dev.adsa.model.Cycle;
import dev.adsa.model.Student;
import dev.adsa.service.StudentService;
import dev.adsa.utilities.MongoUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
public class MainMenuController {

    private StudentService studentService;

    @FXML
    private BorderPane menuBorderPane;

    @FXML
    private AnchorPane overlay;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSurname;

    @FXML
    private TextField inputAge;

    @FXML
    private TextField inputPhone;

    @FXML
    private ChoiceBox<City> cbCity;

    @FXML
    private ChoiceBox<Cycle> cbCycle;

    @FXML
    public void initialize() {
        studentService = new StudentService();
        // Establecer los valores de los ChoiceBox
        cbCity.getItems().setAll(City.values());
        cbCycle.getItems().setAll(Cycle.values());

        // Formato para que solo se puedan introducir números en el campo de edad
        inputAge.setTextFormatter(new TextFormatter<>(change -> {
            return change.getControlNewText().matches("\\d*") ? change : null;
        }));
    }

    @FXML
    private void createStudent() {
        String name = inputName.getText();
        String surname = inputSurname.getText();
        int age = Integer.parseInt(inputAge.getText());
        String phone = inputPhone.getText();
        City city = cbCity.getValue();
        Cycle cycle = cbCycle.getValue();
        
        Student student = new Student(name, surname, phone, age, city, cycle);
        studentService.addStudent(student);
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
        MongoUtil.cerrar();
        Platform.exit();
        System.exit(0);
    }
}
