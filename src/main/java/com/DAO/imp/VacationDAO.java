package com.DAO.imp;

import com.DAO.inf.VacationDaoInterface;
import com.entity.Employee;
import com.entity.Vacation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

public class VacationDAO implements VacationDaoInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveVacation(Vacation vacation) {
        entityManager.persist(vacation);
    }

    @Transactional
    public void deleteVacation(int vacationId) {
        Vacation vacation = entityManager.find(Vacation.class, vacationId);
        if (vacation != null) {
            entityManager.remove(vacation);
        }
    }

    @Transactional
    public void updateVacation(Vacation vacation) {
        entityManager.merge(vacation);
    }

    public Vacation getVacationById(int id) {
        return entityManager.find(Vacation.class, id);
    }

    public List<Vacation> getLeaveHistoryByEmployee(Employee employee) {
        return entityManager.createQuery("SELECT lr FROM Vacation lr WHERE lr.employee = :employee", Vacation.class)
                .setParameter("employee", employee)
                .getResultList();
    }

    public List<Vacation> getAllVacations() {
        return entityManager.createQuery("SELECT v FROM Vacation v", Vacation.class).getResultList();
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}

