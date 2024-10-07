package com.DAO.imp;

import com.DAO.inf.EmployeeDaoInterface;
import com.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDAO implements EmployeeDaoInterface {

    private SessionFactory sessionFactory;


    public EmployeeDAO() {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            System.out.println("SessionFactory created successfully.");
        } catch (Exception e) {
            System.out.println("Failed to create SessionFactory.");
            e.printStackTrace();
        }
    }

    @Override

    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        List<Employee> employees = null;
        try {
            employees = session.createQuery("from Employee", Employee.class).list();
            if (employees != null && !employees.isEmpty()) {
                System.out.println("Employees fetched: " + employees.size());
            } else {
                System.out.println("No employees found in the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }


    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

   @Override
    public void deleteEmployee(int employeeId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                session.delete(employee);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = null;

        try (Session session = sessionFactory.openSession();) {

            session.beginTransaction();

            employee = session.get(Employee.class, id);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employee;  // Return the employee or null if not found
    }

    public List<Employee> filter(String filterOption) {
        Session session = sessionFactory.openSession();
        List<Employee> employees = null;
        try {
            employees = session.createQuery("FROM Employee WHERE department = :filterOption", Employee.class)
                    .setParameter("filterOption", filterOption)  // filter by department or whatever criteria you have
                    .list();
        } finally {
            session.close();
        }
        return employees;
    }


    public List<Employee> search(String searchTerm) {
        Session session = sessionFactory.openSession();
        List<Employee> employees = null;
        try {
            employees = session.createQuery("FROM Employee WHERE name LIKE :searchTerm", Employee.class)
                    .setParameter("searchTerm", "%" + searchTerm + "%")  // search for matching names
                    .list();
        } finally {
            session.close();
        }
        return employees;
    }


    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
