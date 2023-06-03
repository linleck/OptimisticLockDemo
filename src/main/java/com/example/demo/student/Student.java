package com.example.demo.student;

import jakarta.persistence.*;

@Entity
@Table(name = "school")
public class Student {
    @Id
    private Integer id;
    private String name;
    private Integer age;

    @Version
    private Integer version;

    public Student() {
    }

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getVersion() {
        return version;
    }
}
