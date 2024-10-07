package com.entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)  // For inheritance in JPA
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;
    private Date dateOfBirth;

    @Column(unique = true)
    private String socialSecurityNumber;

    private String address;
    private String jobTitle;
    private Date hireDate;
    private Double salary;
    private Integer numberOfChildren;

    // Getters and Setters
}
