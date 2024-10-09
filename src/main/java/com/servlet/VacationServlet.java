package com.servlet;

import com.DAO.imp.VacationDAO;
import com.entity.Employee;
import com.entity.Vacation;
import com.enums.VacationStatus;

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
            // Additional routes (e.g., view a single vacation)
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
        String reason = request.getParameter("reason");
        Date startDate = Date.valueOf(request.getParameter("startDate"));  // Assuming the date is passed in "yyyy-MM-dd" format
        Date endDate = Date.valueOf(request.getParameter("endDate"));
        String justificationDocument = request.getParameter("justificationDocument");  // Assuming file path is passed

        // Retrieve the employee from session or request
        Employee employee = (Employee) request.getSession().getAttribute("loggedEmployee");  // Assuming you store employee in session

        // Create a new vacation request with pending status
        Vacation vacation = new Vacation(reason, startDate, endDate, justificationDocument, VacationStatus.PENDING, new Date(), employee);
        vacationDAO.saveVacation(vacation);

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
