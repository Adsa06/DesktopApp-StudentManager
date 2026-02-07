package dev.adsa.controller;

import java.util.function.Consumer;

import dev.adsa.model.City;
import dev.adsa.model.Cycle;
import dev.adsa.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class StudentItemController {

    @FXML
    private CheckBox checkBox;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSurname;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCycle;

    @FXML
    private Button btnUpdate;

    public void setStudentData(Student student) {
        lblName.setText(student.getName());
        lblSurname.setText(student.getSurname());
        lblPhone.setText(student.getPhone());
        lblAge.setText(String.valueOf(student.getAge()));
        lblCity.setText(student.getCity().toString());
        lblCycle.setText(student.getCycle().toString());
    }

    public void setShowDialog(Consumer<Student> showDialog) {
        btnUpdate.setOnAction(e -> {
            Student student = new Student(lblName.getText(), lblSurname.getText(), lblPhone.getText(), Integer.parseInt(lblAge.getText()), City.fromDescription(lblCity.getText()), Cycle.fromDescription(lblCycle.getText()));
            showDialog.accept(student);
        });
    }
}
