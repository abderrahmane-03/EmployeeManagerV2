package com.entity;

import javax.persistence.*;

    @Entity
    @Table(name = "employee")
    public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "name")
        private String name;
        @Column(name = "phone")
        private String phone;
        @Column(name = "email")
        private String email;
        @Column(name = "picture")
        private String picture;
        @Column(name = "position")
        private String position;
        @Column(name = "department")
        private String department;


    protected Employee(int id,String name,String email,String phone,String department,String position,String picture){
        this.id=id;
        this.name=name;
        this.email=email;
        this.department=phone;
        this.phone=department;
        this.position=position;
        this.picture=position;
    };

    public Employee() {

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
