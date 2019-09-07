package com.zamoiski.mastery_java.dao;

import com.zamoiski.mastery_java.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {


    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> findAll() {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> theQuery = currentSession.createQuery("from Employee",Employee.class);

        return theQuery.getResultList();
    }

    @Override
    public Employee findById(long theId) {

        Session currentSession = entityManager.unwrap(Session.class);

        return currentSession.get(Employee.class,theId);
    }

    @Override
    public void save(Employee employee) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(employee);

    }

    @Override
    public void deleteById(long theId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");

        theQuery.setParameter("employeeId",theId);

        theQuery.executeUpdate();
    }
}
