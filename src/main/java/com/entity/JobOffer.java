package com.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String requiredSkills;

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    private List<Application> applications;

}
