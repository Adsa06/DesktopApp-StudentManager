package dev.adsa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import dev.adsa.exceptions.InsufficientDataException;
import dev.adsa.model.City;
import dev.adsa.model.Cycle;
import dev.adsa.model.Student;
import dev.adsa.service.StudentService;
import dev.adsa.utilities.MongoUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
public class MainMenuController {

    private StudentService studentService;

    @FXML
    private BorderPane bpMainMenu;

    @FXML
    private AnchorPane apUpdateForm;

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
    private TextField formName;

    @FXML
    private TextField formSurname;

    @FXML
    private TextField formAge;

    @FXML
    private ChoiceBox<City> formCity;

    @FXML
    private ChoiceBox<Cycle> formCycle;
    
    @FXML
    private Label lblAddStudentError;

    @FXML
    private Label lblUpdateStudentError;

    @FXML
    private ListView<Student> studentListView;

    private Student selectedStudent;

    private List<Student> studentsToDelete = new ArrayList<>();

    @FXML
    private ChoiceBox<Cycle> cbFilter;

    @FXML
    public void initialize() {
        studentService = new StudentService();
        // Establecer los valores de los ChoiceBox
        cbCity.getItems().setAll(City.values());
        cbCycle.getItems().setAll(Cycle.values());

        // Establecer los valores de los ChoiceBox en el formulario de actualización
        formCity.getItems().setAll(City.values());
        formCycle.getItems().setAll(Cycle.values());

        cbFilter.getItems().setAll(Cycle.values());

        // Formato para que solo se puedan introducir números en el campo de edad
        inputAge.setTextFormatter(new TextFormatter<>(change -> {
            return change.getControlNewText().matches("\\d*") ? change : null;
        }));
        formAge.setTextFormatter(new TextFormatter<>(change -> {
            return change.getControlNewText().matches("\\d*") ? change : null;
        }));

        loadStudents(studentService.getAllStudents());
    }

    @FXML
    private void createStudent() {
        String name = inputName.getText();
        String surname = inputSurname.getText();
        int age = inputAge.getText().isEmpty() ? 0 : Integer.parseInt(inputAge.getText());
        String phone = inputPhone.getText();
        City city = cbCity.getValue();
        Cycle cycle = cbCycle.getValue();
        
        try {
            Student student = studentService.validateAddStudent(name, surname, phone, age, city, cycle);

            // Limpiar los campos después de crear el alumno
            inputName.clear();
            inputSurname.clear();
            inputAge.clear();
            inputPhone.clear();
            cbCity.setValue(null);
            cbCycle.setValue(null);
            lblAddStudentError.setVisible(false);
            studentListView.getItems().add(student);
        } catch (InsufficientDataException ide) {            
            lblAddStudentError.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al crear el alumno: " + e.getMessage());
        }
    }

    private void loadStudents(List<Student> studentsList) {
        studentListView.getItems().clear();
        studentListView.getItems().addAll(studentsList);
        studentListView.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/student_item_view.fxml"));
                        setGraphic(loader.load());
                        StudentItemController controller = loader.getController();
                        controller.setStudentData(student);
                        controller.setShowDialog(showDialog);
                        controller.setSelected((s) -> {
                            if(studentsToDelete.contains(s))
                                studentsToDelete.remove(s);
                            else
                                studentsToDelete.add(s);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    Consumer<Student> showDialog = (student) -> {

        selectedStudent = student;

        formName.setText(student.getName());
        formSurname.setText(student.getSurname());
        formAge.setText(String.valueOf(student.getAge()));
        formCity.setValue(student.getCity());
        formCycle.setValue(student.getCycle());

        apUpdateForm.setVisible(true);
        apUpdateForm.setDisable(false);
        bpMainMenu.setDisable(true);
    };

    @FXML
    private void closeDialog() {
        apUpdateForm.setVisible(false);
        bpMainMenu.setDisable(false);
        apUpdateForm.setDisable(true);
    }

    @FXML
    private void updateStudent() {
        String name = formName.getText();
        String surname = formSurname.getText();
        int age = formAge.getText().isEmpty() ? 0 : Integer.parseInt(formAge.getText());
        City city = formCity.getValue();
        Cycle cycle = formCycle.getValue();
        try {
            studentService.validateUpdateStudent(
                selectedStudent, 
                name, 
                surname,
                age, 
                city, 
                cycle
            );
        
            apUpdateForm.setVisible(false);
            bpMainMenu.setDisable(false);
            apUpdateForm.setDisable(true);
            lblUpdateStudentError.setVisible(false);

        } catch (InsufficientDataException ide) {
            lblUpdateStudentError.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al actualizar el alumno: " + e.getMessage());
        }
        loadStudents(studentService.getAllStudents());
    }

    @FXML
    private void deleteStudent() {
        System.out.println("Alumnos a eliminar: " + studentsToDelete);
        studentService.deleteStudent(studentsToDelete);
        studentsToDelete.clear(); // Clear the list after deletion
        loadStudents(studentService.getAllStudents());
    }
    
    @FXML
    private void closeApp() {
        MongoUtil.cerrar();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void applyFilter() {
        System.out.println("------------------------------------");
        Cycle selectedCycle = cbFilter.getValue();
        if (selectedCycle != null) {
            List<Student> filteredStudents = studentService.findByCycle(selectedCycle);
            for (Student s : filteredStudents) {
                System.out.println("Filtered student: " + s.getName() + " " + s.getSurname() + " - Cycle: " + s.getCycle());
            }
            loadStudents(filteredStudents);
        }
    }
    
    @FXML
    private void eliminateFilter() {
        loadStudents(studentService.getAllStudents());
    }
    
}
