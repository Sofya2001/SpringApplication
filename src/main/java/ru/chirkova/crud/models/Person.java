package ru.chirkova.crud.models;


import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be emty")
    @Size(min = 2,max=30,message = "Name should be between 2 and 30 charaters")
    private String name;

    @Min(value = 0,message = "Age should be greater than 0")
    @Max(value = 110,message = "Age should be under than 110" )
    private int age;
    @NotEmpty(message = "Email should not be emty")
    @Email(message = "Email should be valid")
    private String email;


    public Person() {
    }

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
