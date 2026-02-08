package dev.adsa.utilities;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Clase de utilidad que centraliza la conexión con MongoDB.
 */
public class MongoUtil {

    /** URI de conexión con MongoDB */
    private static final String URI = "mongodb://localhost:27017";
    /** Nombre de la base de datos */
    private static final String DB_NAME = "student_manager";

    /** Cliente de MongoDB */
    private static MongoClient client;

    /** Devuelve la conexión con MongoDB */
    public static MongoDatabase getDatabase() {
        if (client == null) {
            client = MongoClients.create(URI);
        }
        return client.getDatabase(DB_NAME);
    }

    /** Cierra la conexión con MongoDB */
    public static void cerrar() {
        if (client != null) {
            client.close();
        }
    }
}
