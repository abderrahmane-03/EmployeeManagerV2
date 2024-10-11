package com.service;

import com.DAO.imp.VacationDAO;
import com.entity.Vacation;
import com.enums.VacationStatus;

public class VacationService {

    private VacationDAO vacationDAO = new VacationDAO();  // DAO layer

    private EmailService emailService = new EmailService();  // DAO layer

    public void updateVacationStatus(Vacation vacation, VacationStatus newStatus, String managerComments) {
        vacation.setStatus(newStatus);
        vacationDAO.updateVacation(vacation);

        // Send email notification
        String subject = "Vacation Request " + newStatus;
        String message = "Your vacation request has been " + newStatus.toString().toLowerCase() + ".\nComments: " + managerComments;

        emailService.sendEmail(vacation.getEmployee().getEmail(), subject, message);
    }

        public void saveVacation(Vacation vacation) {
            vacationDAO.saveVacation(vacation);
        }


        public Vacation getVacationById(int vacationId) {
            return vacationDAO.getVacationById(vacationId);
        }
    }
