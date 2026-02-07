package dev.adsa.utilities;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Clase de utilidad que centraliza la conexión con MongoDB.
 */
public class MongoUtil {

    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "student_manager";

    private static MongoClient client;

    public static MongoDatabase getDatabase() {
        if (client == null) {
            client = MongoClients.create(URI);
        }
        return client.getDatabase(DB_NAME);
    }

    public static void cerrar() {
        if (client != null) {
            client.close();
        }
    }
}
