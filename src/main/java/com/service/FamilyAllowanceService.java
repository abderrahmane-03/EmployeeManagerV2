package com.service;

import com.entity.Employee;

public class FamilyAllowanceService {

    public double calculateAllowance(Employee employee) {
        double salary = employee.getSalary();
        int numberOfChildren = employee.getNumberOfChildren();
        double totalAllowance = 0;

        if (numberOfChildren > 0) {
            int firstBatch = Math.min(3, numberOfChildren); // First 3 children
            int remainingChildren = Math.max(0, numberOfChildren - 3); // Remaining children

            if (salary < 6000) {
                totalAllowance += firstBatch * 300;
                totalAllowance += Math.min(remainingChildren, 3) * 150;
            } else if (salary > 8000) {
                totalAllowance += firstBatch * 200;
                totalAllowance += Math.min(remainingChildren, 3) * 110;
            }
        }

        return totalAllowance;
    }
}
