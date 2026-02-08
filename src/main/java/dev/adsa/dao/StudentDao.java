package dev.adsa.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;

import dev.adsa.model.City;
import dev.adsa.model.Cycle;
import dev.adsa.model.Student;
import dev.adsa.utilities.MongoUtil;

/** Dao de estudiantes */
public class StudentDao {
        
    /** Colección de estudiantes */
    private MongoCollection<Document> collection;

    /** 
     * Constructor de la clase 
     * 
     * Inicializa la conexión con MongoDB y la colección de estudiantes.
     * También crea un índice único en el campo "phone" para evitar duplicados.
     */
    public StudentDao() {
        MongoDatabase db = MongoUtil.getDatabase();
        collection = db.getCollection("students");
        // Crear índice único en el campo "phone" para evitar duplicados
        IndexOptions options = new IndexOptions().unique(true);
        // El 1 representa un índice ascendente
        collection.createIndex(new Document("phone", 1), options);
    }

    /**
     * Añade un estudiante en la base de datos.
     * 
     * @param student estudiante a agregar
     */
    public void addStudent(Student student) {
        Document doc = new Document("name", student.getName())
                .append("surname", student.getSurname())
                .append("phone", student.getPhone())
                .append("age", student.getAge())
                .append("city", student.getCity())
                .append("cycle", student.getCycle());
        collection.insertOne(doc);
    }

    /**
     * Devuelve una lista con todos los estudiantes en la base de datos.
     * 
     * @return lista de estudiantes
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        for (Document doc : collection.find()) {
            Student student = new Student();
            student.setName(doc.getString("name"));
            student.setSurname(doc.getString("surname"));
            student.setPhone(doc.getString("phone"));
            student.setAge(doc.getInteger("age"));
            student.setCity(City.valueOf(doc.getString("city")));
            student.setCycle(Cycle.valueOf(doc.getString("cycle")));
            students.add(student);
        }

        return students;
    }

    
    /**
     * Actualiza un estudiante en la base de datos.
     * 
     * @param name            nombre del estudiante
     * @param surname        apellidos del estudiante
     * @param age           edad del estudiante
     * @param city          ciudad del estudiante
     * @param cycle         ciclo formativo del estudiante
     * @param oldStudent    estudiante a actualizar
     */
    public void updateStudent(String name, String surname, int age, City city, Cycle cycle, Student oldStudent) {
        Document filter = new Document("phone", oldStudent.getPhone());
        Document update = new Document("$set", new Document("name", name)
                .append("surname", surname)
                .append("age", age)
                .append("city", city)
                .append("cycle", cycle));
        collection.updateOne(filter, update);
    }

    /**
     * Elimina una lista de estudiantes pasada como parámetro.
     * 
     * @param studentsToDelete lista de estudiantes a eliminar
     */
    public void deleteStudent(List<Student> studentsToDelete) {
        // Crear un filtro que contenga todos los telefonos de la lista
        List<String> phones = studentsToDelete.stream()
            .map(Student::getPhone)
            .collect(Collectors.toList());

        // Crear el filtro para eliminar los documentos que tengan un phone en la lista
        Bson filter = Filters.in("phone", phones);

        // Ejecutar deleteMany
        collection.deleteMany(filter);
    }

    /**
     * Busca todos los estudiantes que pertenecen al ciclo formativo pasado por parámetro.
     * 
     * @param cycle ciclo formativo a buscar
     * @return lista de estudiantes que pertenecen al ciclo formativo
     */
    public List<Student> findByCycle(Cycle cycle) {
        List<Student> students = new ArrayList<>();

        for (Document doc : collection.find(new Document("cycle", cycle))) {
            Student student = new Student();
            student.setName(doc.getString("name"));
            student.setSurname(doc.getString("surname"));
            student.setPhone(doc.getString("phone"));
            student.setAge(doc.getInteger("age"));
            student.setCity(City.valueOf(doc.getString("city")));
            student.setCycle(Cycle.valueOf(doc.getString("cycle")));
            students.add(student);
        }

        return students;
    }
}
