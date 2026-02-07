package dev.adsa.service;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dev.adsa.model.Student;
import dev.adsa.utilities.MongoUtil;

public class StudentService {
    
    private MongoCollection<Document> collection;

    public StudentService() {
        MongoDatabase db = MongoUtil.getDatabase();
        collection = db.getCollection("students");
    }

    public void addStudent(Student student) {
        Document doc = new Document("nombre", student.getName())
            .append("apellidos", student.getSurname())
            .append("telefono", student.getPhone())
            .append("edad", student.getAge())
            .append("ciudad", student.getCity())
            .append("ciclo", student.getCycle());
        collection.insertOne(doc);
    }
}
