package dev.adsa.model;

/**
 * Clase que representa a un estudiante con sus atributos y métodos relacionados.
 */
public class Student {

    /** Nombre del estudiante */
    private String name;
    /** Apellido del estudiante */
    private String surname;
    /** Teléfono del estudiante, utilizado como identificador único */
    private String phone;
    /** Edad del estudiante */
    private int age;
    /** Ciudad de residencia del estudiante */
    private City city;
    /** Ciclo formativo del estudiante */
    private Cycle cycle;

    /** Constructor de la clase Student */
    public Student() {}

    /** Constructor de la clase Student con parámetros */
    public Student(String name, String surname, String phone, int age, City city, Cycle cycle) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.age = age;
        this.city = city;
        this.cycle = cycle;
    }

    /** Devuelve el nombre del estudiante */
    public String getName() {
        return name;
    }

    /** Establece el nombre del estudiante */
    public void setName(String name) {
        this.name = name;
    }

    /** Devuelve el apellido del estudiante */
    public String getSurname() {
        return surname;
    }

    /** Establece el apellido del estudiante */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /** Devuelve el teléfono del estudiante */
    public String getPhone() {
        return phone;
    }

    /** Establece el teléfono del estudiante */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Devuelve la edad del estudiante */
    public int getAge() {
        return age;
    }

    /** Establece la edad del estudiante */
    public void setAge(int age) {
        this.age = age;
    }

    /** Devuelve la ciudad de residencia del estudiante */
    public City getCity() {
        return city;
    }

    /** Establece la ciudad de residencia del estudiante */
    public void setCity(City city) {
        this.city = city;
    }

    /** Devuelve el ciclo formativo del estudiante */
    public Cycle getCycle() {
        return cycle;
    }

    /** Establece el ciclo formativo del estudiante */
    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    
    /**
     * Comprueba si dos objetos son iguales. En este caso, dos objetos Student son iguales
     * si tienen el mismo teléfono.
     * 
     * @param o el objeto a comparar
     * @return true si el objeto es igual, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        boolean isSame;
        if (this == o)
            isSame = true;
        else if (o == null || getClass() != o.getClass())
            isSame = false;
        else {
            Student student = (Student) o;
            isSame = phone != null && phone.equals(student.phone);
        }
        return isSame;
    }

     /**
     * 
     * Este método devuelve un código hash que identifica a este objeto.
     * En este caso, el código hash se basa en el teléfono del estudiante.
     * Si el teléfono es nulo, se devuelve 0. De lo contrario, se devuelve el código
     * hash del teléfono.
     * 
     * @return el código hash del objeto
     */
    @Override
    public int hashCode() {
        return phone != null ? phone.hashCode() : 0;
    }
}
