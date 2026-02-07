package dev.adsa.model;

public enum City {
    MADRID("Madrid"),
    BARCELONA("Barcelona"),
    VALENCIA("Valencia"),
    SEVILLA("Sevilla"),
    MALAGA("Malaga");

    private String name;

    private City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

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
