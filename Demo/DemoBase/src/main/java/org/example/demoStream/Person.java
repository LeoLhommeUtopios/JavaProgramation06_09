package org.example.demoStream;

public class Person {
    private String name;
    private int age;
    private String telephone;

    public Person(String name, int age, String telephone) {
        this.name = name;
        this.age = age;
        this.telephone = telephone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
