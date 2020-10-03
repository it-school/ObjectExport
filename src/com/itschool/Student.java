package com.itschool;

import java.io.Serializable;

public class Student implements Serializable {
    String name;
    int age;

    @Override
    public String toString() {
        return name + ": " + age + " y.o.";
    }

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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
}