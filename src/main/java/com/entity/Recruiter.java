package com.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recruiter extends User {

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<JobOffer> jobOffers;

    // Getters and Setters
}
