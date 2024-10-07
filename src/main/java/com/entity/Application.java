package com.entity;

import javax.persistence.*;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String resume;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;  // Assuming enum type status

    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;

}