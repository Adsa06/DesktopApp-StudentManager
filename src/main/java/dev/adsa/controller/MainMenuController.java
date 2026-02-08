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

/** Controlador de la pantalla principal */
public class MainMenuController {

    /** Servicio de estudiantes */
    private StudentService studentService;

    /** Panel principal del programa */
    @FXML
    private BorderPane bpMainMenu;

    /** Formulario de edición de estudiantes */
    @FXML
    private AnchorPane apUpdateForm;

    /** Campo del nombre de la creacion de estudiante */
    @FXML
    private TextField inputName;

    /** Campo del apellido de la creacion de estudiante */
    @FXML
    private TextField inputSurname;

    /** Campo de la edad de la creacion de estudiante */
    @FXML
    private TextField inputAge;

    /** Campo del teléfono de la creacion de estudiante */
    @FXML
    private TextField inputPhone;

    /** Desplegable de la ciudad de la creacion de estudiante */
    @FXML
    private ChoiceBox<City> cbCity;

    /** Desplegable del ciclo formativo de la creacion de estudiante */
    @FXML
    private ChoiceBox<Cycle> cbCycle;

    /** Campos del nombre de la actualizacion de un estudiante */
    @FXML
    private TextField formName;

    /** Campos del apellido de la actualización de un estudiante */
    @FXML
    private TextField formSurname;

    /** Campos de la edad de la actualización de un estudiante */
    @FXML
    private TextField formAge;

    /** Campos de la ciudad de la actualización de un estudiante */
    @FXML
    private ChoiceBox<City> formCity;

    /** Campos del ciclo formativo de la actualización de un estudiante */
    @FXML
    private ChoiceBox<Cycle> formCycle;
    
    /** Etiqueta de error de la creacion de un estudiante */
    @FXML
    private Label lblAddStudentError;

    /** Etiqueta de error de la actualización de un estudiante */
    @FXML
    private Label lblUpdateStudentError;

    /** ListView de estudiantes */
    @FXML
    private ListView<Student> studentListView;

    /** Estudiante seleccionado para actualización */
    private Student selectedStudent;

    /** Estudiantes seleccionados para eliminación */
    private List<Student> studentsToDelete = new ArrayList<>();

    /** ChoiceBox para filtrar estudiantes por ciclo formativo */
    @FXML
    private ChoiceBox<Cycle> cbFilter;

    /**
     * Inicializa el controlador de la pantalla principal.
     * Establece los valores de los ChoiceBox y formatea para que solo se
     * puedan introducir números en los campos de edad.
     * Carga la lista de estudiantes en el ListView.
     */
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

    /**
     * Crea un nuevo estudiante con la información introducida en los campos de texto y
     * los desplegables de la interfaz de usuario.
     * Lanza una excepción InsufficientDataException si alguno de los campos no se ha
     * proporcionado.
     * Lanza una excepción Exception si se produce un error al crear el estudiante.
     * Si se produce un error al crear el estudiante, se muestra un mensaje de error en la
     * interfaz de usuario.
     */
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

    /**
     * Carga la lista de estudiantes pasada en el ListView.
     * Establece una celda de lista para cada item de la lista.
     * 
     * @param studentsList lista de estudiantes a cargar
     */
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
                        controller.setFunction(showDialog, setStudentToDelete);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /** 
     * Función para marcar o desmarcar un estudiante para eliminar. Si el estudiante ya está marcado, se desmarca. Si no está marcado, se marca.
     */
    Consumer<Student> setStudentToDelete = (student) -> {
        if (studentsToDelete.contains(student))
            studentsToDelete.remove(student);
        else
            studentsToDelete.add(student);
    };

    /**
     * Muestra un diálogo para editar el estudiante seleccionado.
     * Al hacer clic en el botón de actualizar en el item del estudiante, se establece el estudiante seleccionado
     * en el formulario de edición y se muestra el formulario. El menu principal se deshabilita para evitar que el usuario
     * interactúe con el mientras el formulario de edición está abierto.
     */
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

    /**
     * Cierra el formulario de edición de estudiante y vuelve a la pantalla
     * principal.
     */
    @FXML
    private void closeDialog() {
        apUpdateForm.setVisible(false);
        bpMainMenu.setDisable(false);
        apUpdateForm.setDisable(true);
    }

    /**
     * Actualiza un estudiante en la base de datos.
     * 
     * @throws InsufficientDataException si alguno de los campos no se ha proporcionado
     * @throws Exception si ocurre cualquier otro error
     */
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

    /**
     * Elimina la lista de estudiantes pasada como parámetro.
     * Después de eliminar los estudiantes, carga la lista de estudiantes
     * con todos los estudiantes en la base de datos.
     */
    @FXML
    private void deleteStudent() {
        System.out.println("Alumnos a eliminar: " + studentsToDelete);
        studentService.deleteStudent(studentsToDelete);
        studentsToDelete.clear(); // Clear the list after deletion
        loadStudents(studentService.getAllStudents());
    }
    
    /**
     * Cierra la aplicación.
     * 
     * Cierra la conexión con MongoDB y
     * sale de la aplicación.
     */
    @FXML
    private void closeApp() {
        MongoUtil.cerrar();
        Platform.exit();
        System.exit(0);
    }

    /**
     * Aplica el filtro de búsqueda por ciclo formativo y carga la lista
     * de estudiantes que se ajustan a ese ciclo formativo.
     */
    @FXML
    private void applyFilter() {
        Cycle selectedCycle = cbFilter.getValue();
        if (selectedCycle != null) {
            List<Student> filteredStudents = studentService.findByCycle(selectedCycle);
            loadStudents(filteredStudents);
        }
    }
    
    
    /**
     * Elimina el filtro de búsqueda y carga la lista de estudiantes completa.
     */
    @FXML
    private void eliminateFilter() {
        loadStudents(studentService.getAllStudents());
    }
    
}
