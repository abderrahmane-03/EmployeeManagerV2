package com.entity;

import com.enums.VacationStatus;

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

    Vacation(int id,Date startDate,Date endDate,String reason,VacationStatus status,Date submittedDate,String justificationDocument,Employee employee){
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.submittedDate = submittedDate;
        this.justificationDocument = justificationDocument;
        this.employee = employee;
    }

    public Vacation() {

    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public VacationStatus getStatus() {
        return status;
    }
    public void setStatus(VacationStatus status) {
        this.status = status;
    }
    public Date getSubmittedDate() {
        return submittedDate;
    }
    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }
    public String getJustificationDocument() {
        return justificationDocument;
    }
    public void setJustificationDocument(String justificationDocument) {
        this.justificationDocument = justificationDocument;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}