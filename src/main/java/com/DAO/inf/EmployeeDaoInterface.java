package com.DAO.inf;
import com.entity.Employee;
import java.util.List;
public interface EmployeeDaoInterface {
    public List<Employee> getAllEmployees();
    public void saveEmployee(Employee employee);
    public void deleteEmployee(int employeeId);
    public void updateEmployee(Employee employee);
    public Employee getEmployeeById(int id);
    public List<Employee> filter(String filterOption);
    public List<Employee> search(String searchTerm);
    public void close() ;
}

