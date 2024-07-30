
package model;

public class Person {
    private int id;
    private String name;
    private String email;
    private int age;
    private String rol;

    public Person(int id, String name, String email, int age, String rol) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
}