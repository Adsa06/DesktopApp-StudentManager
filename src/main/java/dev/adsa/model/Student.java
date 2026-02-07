package dev.adsa.model;

public class Student {

    private String name;
    private String surname;
    private String phone;
    private int age;
    private City city;
    private Cycle cycle;

    public Student() {}

    public Student(String name, String surname, String phone, int age, City city, Cycle cycle) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.age = age;
        this.city = city;
        this.cycle = cycle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
}
