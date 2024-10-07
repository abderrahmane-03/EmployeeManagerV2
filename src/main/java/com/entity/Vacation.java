package com.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date startDate;
    private Date endDate;

    private String reason;

    @Enumerated(EnumType.STRING)
    private VacationStatus status;  // Assuming enum type status

    private Date submittedDate;
    private String justificationDocument;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}