package dev.adsa.controller;

import java.util.function.Consumer;

import dev.adsa.model.City;
import dev.adsa.model.Cycle;
import dev.adsa.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/** Controlador del item de estudiante */
public class StudentItemController {

    /** Etiqueta para mostrar el nombre */
    @FXML
    private Label lblName;

    /** Etiqueta para mostrar el apellido */
    @FXML
    private Label lblSurname;

    /** Etiqueta para mostrar el teléfono */
    @FXML
    private Label lblPhone;

    /** Etiqueta para mostrar la edad */
    @FXML
    private Label lblAge;

    /** Etiqueta para mostrar la ciudad */
    @FXML
    private Label lblCity;

    /** Etiqueta para mostrar el ciclo */
    @FXML
    private Label lblCycle;

    /** Boton para actualizar el estudiante */
    @FXML
    private Button btnUpdate;

    /** CheckBox para marcar el estudiante */
    @FXML
    private CheckBox checkBox;

    /**
     * Establece la información del estudiante en el item.
     * 
     * @param student estudiante a establecer
     */
    public void setStudentData(Student student) {
        lblName.setText(student.getName());
        lblSurname.setText(student.getSurname());
        lblPhone.setText(student.getPhone());
        lblAge.setText(String.valueOf(student.getAge()));
        lblCity.setText(student.getCity().toString());
        lblCycle.setText(student.getCycle().toString());
    }


    /**
     * Establece dos funcionesos para interactuar con el item del estudiante.
     * La primera función se encarga de mostrar un diálogo para editar el estudiante seleccionado.
     * La segunda función se encarga de eliminar el estudiante seleccionado.
     * 
     * @param showDialog función que se encarga de mostrar el diálogo para editar el estudiante
     * @param setStudentToDelete función que se encarga de eliminar el estudiante
    */
    public void setFunction(Consumer<Student> showDialog, Consumer<Student> setStudentToDelete) {
        btnUpdate.setOnAction(e -> {
            Student student = new Student(lblName.getText(), lblSurname.getText(), lblPhone.getText(), Integer.parseInt(lblAge.getText()), City.fromDescription(lblCity.getText()), Cycle.fromDescription(lblCycle.getText()));
            showDialog.accept(student);
        });

        checkBox.setOnAction(e -> {
            Student student = new Student(lblName.getText(), lblSurname.getText(), lblPhone.getText(), Integer.parseInt(lblAge.getText()), City.fromDescription(lblCity.getText()), Cycle.fromDescription(lblCycle.getText()));
            setStudentToDelete.accept(student);
        });
    }
}
