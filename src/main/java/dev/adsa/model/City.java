package dev.adsa.model;

/**
 * Enumeración que representa las diferentes ciudades que se pueden
 * seleccionar en la aplicación. 
 */
public enum City {
    MADRID("Madrid"),
    BARCELONA("Barcelona"),
    VALENCIA("Valencia"),
    SEVILLA("Sevilla"),
    MALAGA("Malaga");

    /** Nombre de la ciudad */
    private String name;

    /** Constructor privado para inicializar el nombre de la ciudad */
    private City(String name) {
        this.name = name;
    }

    /** Devuelve el nombre de la ciudad */
    public String getName() {
        return name;
    }

    /** Devuelve una representación en cadena de la ciudad */
    @Override
    public String toString() {
        return name;
    }

    /**
    * Método estático que busca una ciudad por su descripción (nombre).
    *
    * @param desc La descripción de la ciudad a buscar.
    * @return La ciudad correspondiente a la descripción, o null si no se encuentra.
    */
    public static City fromDescription(String desc) {
        City foundCity = null;
        City[] cities = City.values();
        int i = 0;
        while (i < cities.length && foundCity == null) {
            if (cities[i].getName().equalsIgnoreCase(desc)) {
                foundCity = cities[i];
            }
            i++;
        }
        return foundCity;
    }
}
