package com.entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String name;

    @Column(unique = true, nullable = false)
    protected String email;

    protected String phone;
    protected Date DOB;

    @Column(unique = true)
    protected String SSN;

    protected String department;
    protected String position;
    protected Date hireDate;
    protected String picture;
    protected Double salary;
    protected Integer numberOfChildren;


}
