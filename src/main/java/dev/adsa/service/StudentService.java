package dev.adsa.service;

import java.util.List;

import dev.adsa.dao.StudentDao;
import dev.adsa.exceptions.InsufficientDataException;
import dev.adsa.model.City;
import dev.adsa.model.Cycle;
import dev.adsa.model.Student;

public class StudentService {

    private StudentDao studentDao;

    public StudentService() {
        studentDao = new StudentDao();
    }

    public Student validateAddStudent(String name, String surname, String phone, int age, City city, Cycle cycle) throws InsufficientDataException {
        if (name.isEmpty() || surname.isEmpty() || phone.isEmpty() || age <= 0 || city == null || cycle == null) {
            throw new InsufficientDataException("Todos los campos son obligatorios.");
        }

        Student student = new Student(name, surname, phone, age, city, cycle);
        studentDao.addStudent(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public void validateUpdateStudent(Student oldStudent, String name, String surname, int age, City city, Cycle cycle) throws InsufficientDataException {
        if (name.isEmpty() || surname.isEmpty() || age <= 0 || city == null || cycle == null) {
            throw new InsufficientDataException("Todos los campos son obligatorios.");
        }
        studentDao.updateStudent(name, surname, age, city, cycle, oldStudent);
    }

    public void deleteStudent(List<Student> studentsToDelete) {
        studentDao.deleteStudent(studentsToDelete);
    }

    public List<Student> findByCycle(Cycle cycle) {
        return studentDao.findByCycle(cycle);
    }
}
