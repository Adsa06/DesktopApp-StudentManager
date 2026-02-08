package dev.adsa.service;

import java.util.List;

import dev.adsa.dao.StudentDao;
import dev.adsa.exceptions.InsufficientDataException;
import dev.adsa.model.City;
import dev.adsa.model.Cycle;
import dev.adsa.model.Student;

/** Servicio de estudiantes */
public class StudentService {

    /** DAO de estudiantes */
    private StudentDao studentDao;

    /** Constructor */
    public StudentService() {
        studentDao = new StudentDao();
    }

    /**
     * Valida los campos de un estudiante antes de agregar.
     * Si alguno de los campos no se ha proporcionado, lanza una excepción
     * InsufficientDataException.
     * 
     * @param name            nombre del estudiante
     * @param surname        apellidos del estudiante
     * @param phone          teléfono del estudiante
     * @param age           edad del estudiante
     * @param city          ciudad del estudiante
     * @param cycle         ciclo formativo del estudiante
     * @return el estudiante que se ha agregado
     * @throws InsufficientDataException si alguno de los campos no se ha proporcionado
     */
    public Student validateAddStudent(String name, String surname, String phone, int age, City city, Cycle cycle) throws InsufficientDataException {
        if (name.isEmpty() || surname.isEmpty() || phone.isEmpty() || age <= 0 || city == null || cycle == null) {
            throw new InsufficientDataException("Todos los campos son obligatorios.");
        }

        Student student = new Student(name, surname, phone, age, city, cycle);
        studentDao.addStudent(student);
        return student;
    }


    /** 
     * Devuelve una lista con todos los estudiantes en la base de datos.
     * @return lista de estudiantes
     */
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    /**
     * Valida los campos de un estudiante antes de actualizar.
     * Si alguno de los campos no se ha proporcionado, lanza una excepción
     * InsufficientDataException.
     * 
     * @param oldStudent        estudiante a actualizar
     * @param name            nombre del estudiante
     * @param surname        apellidos del estudiante
     * @param age           edad del estudiante
     * @param city          ciudad del estudiante
     * @param cycle         ciclo formativo del estudiante
     * @throws InsufficientDataException si alguno de los campos no se ha proporcionado
     */
    public void validateUpdateStudent(Student oldStudent, String name, String surname, int age, City city, Cycle cycle) throws InsufficientDataException {
        if (name.isEmpty() || surname.isEmpty() || age <= 0 || city == null || cycle == null) {
            throw new InsufficientDataException("Todos los campos son obligatorios.");
        }
        studentDao.updateStudent(name, surname, age, city, cycle, oldStudent);
    }

    /**
     * Elimina la lista de estudiantes pasada como parámetro.
     * 
     * @param studentsToDelete lista de estudiantes a eliminar
     */
    public void deleteStudent(List<Student> studentsToDelete) {
        studentDao.deleteStudent(studentsToDelete);
    }


    /**
     * Busca todos los estudiantes que pertenecen al ciclo formativo pasado por parámetro.
     * 
     * @param cycle ciclo formativo a buscar
     * @return lista de estudiantes que pertenecen al ciclo formativo
    */
    public List<Student> findByCycle(Cycle cycle) {
        return studentDao.findByCycle(cycle);
    }
}
