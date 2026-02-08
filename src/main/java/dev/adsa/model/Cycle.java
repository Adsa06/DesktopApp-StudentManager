package dev.adsa.model;

/**
 * Enumeración que representa los ciclos formativos disponibles.
 */
public enum Cycle {
    DAM("Desarrollo de Aplicaciones Multiplataforma"),
    DAW("Desarrollo de Aplicaciones Web"),
    ASIR("Administración de Sistemas Informáticos en Red"),
    SMR("Sistemas Microinformáticos y Redes"),
    ASIX("Administración de Sistemas Informáticos en Red"),
    ADE("Administración de Empresas"),;

    /** Nombre del ciclo formativo */
    private String name;

    /** Constructor privado para inicializar el nombre del ciclo formativo */
    Cycle(String name) {
        this.name = name;
    }

    /** Devuelve el nombre del ciclo formativo */
    public String getName() {
        return name;
    }

    /** Devuelve una representación en cadena del ciclo formativo */
    @Override
    public String toString() {
        return name;
    }

    /** 
     * Método estático que busca un ciclo formativo por su descripción (nombre).
     *
     * @param desc La descripción del ciclo formativo a buscar.
     * @return El ciclo formativo correspondiente a la descripción, o null si no se encuentra.
     */
    public static Cycle fromDescription(String desc) {
        Cycle foundCycle = null;
        Cycle[] cycles = Cycle.values();
        int i = 0;
        while (i < cycles.length && foundCycle == null) {
            if (cycles[i].getName().equalsIgnoreCase(desc)) {
                foundCycle = cycles[i];
            }
            i++;
        }
        return foundCycle;
    }
}
