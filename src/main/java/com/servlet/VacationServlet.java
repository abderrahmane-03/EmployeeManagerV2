package com.servlet;

import com.DAO.imp.VacationDAO;
import com.entity.Employee;
import com.entity.Vacation;
import com.enums.VacationStatus;
import com.service.EmailService;
import com.service.VacationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServlet;


@WebServlet("/vacations/*")
public class VacationServlet extends HttpServlet {

    private VacationService vacationService = new VacationService();  // Service layer
    private EmailService emailService = new EmailService();

    private VacationDAO vacationDAO;

    @Override
    public void init() {
        vacationDAO = new VacationDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            listVacations(request, response);  // List all leave requests for an employee
        } else {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action.equals("/request")) {
            createVacation(request, response);
        }
    }

    private void createVacation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // Get the reason and justification document from the form submission
            String reason = request.getParameter("reason");
            String justificationDocument = request.getParameter("justificationDocument");

            // Parse start and end dates from the form submission
            // Assuming the form sends dates in "yyyy-MM-dd" format
            java.sql.Date startDate = java.sql.Date.valueOf(request.getParameter("startDate"));
            java.sql.Date endDate = java.sql.Date.valueOf(request.getParameter("endDate"));

            // Get the logged employee from the session
            Employee employee = (Employee) request.getSession().getAttribute("loggedEmployee");

            if (employee == null) {
                // Handle case where employee is not found in session
                throw new ServletException("Employee not logged in");
            }

            // Create a new Vacation object
            Vacation vacation = new Vacation( startDate, endDate, reason,  VacationStatus.PENDING, new java.sql.Date(System.currentTimeMillis()),justificationDocument, employee);
            // Save the vacation using the DAO
            vacationDAO.saveVacation(vacation);

            // Notify the employee about the submission
            emailService.sendEmail(employee.getEmail(), "Vacation Request Submitted", "Your vacation request has been submitted for approval.");
            // Redirect to the vacations list
            response.sendRedirect(request.getContextPath() + "/vacations");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error creating vacation: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    private void updateVacationStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int vacationId = Integer.parseInt(request.getParameter("vacationId"));
        VacationStatus newStatus = VacationStatus.valueOf(request.getParameter("status"));
        String managerComments = request.getParameter("managerComments");

        Vacation vacation = vacationService.getVacationById(vacationId);
        vacationService.updateVacationStatus(vacation, newStatus, managerComments);

        // Notify the employee about the vacation status update
        String subject = "Your Vacation Request is " + newStatus;
        String message = "Your vacation request for " + vacation.getStartDate() + " to " + vacation.getEndDate() + " has been " + newStatus + ".\nManager Comments: " + managerComments;
        emailService.sendEmail(vacation.getEmployee().getEmail(), subject, message);

        response.sendRedirect(request.getContextPath() + "/vacations");
    }

    private void listVacations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the employee from session or request
        Employee employee = (Employee) request.getSession().getAttribute("loggedEmployee");
        List<Vacation> vacations = vacationDAO.getLeaveHistoryByEmployee(employee);
        request.setAttribute("vacations", vacations);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/vacationList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        // Clean up resources
    }
}
