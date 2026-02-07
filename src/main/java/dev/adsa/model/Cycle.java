package dev.adsa.model;

public enum Cycle {
    DAM("Desarrollo de Aplicaciones Multiplataforma"),
    DAW("Desarrollo de Aplicaciones Web"),
    ASIR("Administración de Sistemas Informáticos en Red"),
    SMR("Sistemas Microinformáticos y Redes"),
    ASIX("Administración de Sistemas Informáticos en Red"),
    ADE("Administración de Empresas"),;

    private String name;

    Cycle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
