package com.service;

import com.DAO.imp.EmployeeDAO;
import com.DAO.imp.VacationDAO;
import com.entity.Vacation;
import com.enums.VacationStatus;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

public class VacationService {

    private VacationDAO vacationDAO = new VacationDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void updateVacationStatus(Vacation vacation, VacationStatus newStatus) throws Exception {

        LocalDate startDate = convertToLocalDate(vacation.getStartDate());
        LocalDate endDate = convertToLocalDate(vacation.getEndDate());


        long vacationDaysRequested = ChronoUnit.DAYS.between(startDate, endDate);


        int availableVacationDays = vacation.getEmployee().getVacation();
        if (vacationDaysRequested > availableVacationDays) {
            throw new Exception("The employee does not have enough vacation days.");
        }

        vacation.setStatus(newStatus);
        System.out.println("Updating vacation with ID: " + vacation.getId() + " to status: " + newStatus);

        vacationDAO.updateVacation(vacation);

        vacation.getEmployee().setVacation(availableVacationDays - (int) vacationDaysRequested);
        employeeDAO.updateEmployee(vacation.getEmployee());
    }
    private LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
        public Vacation getVacationById(int vacationId) {
            return vacationDAO.getVacationById(vacationId);
        }
    }
