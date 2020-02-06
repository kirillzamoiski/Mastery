package com.zamoiski;

import com.zamoiski.dao.DepartmentDAO;
import com.zamoiski.error.NotFoundException;
import com.zamoiski.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDAO departmentDAO;

    @Autowired
    public DepartmentServiceImpl(DepartmentDAO departmentDAO){
        this.departmentDAO=departmentDAO;
    }

    @Override
    public List<Department> findAll() {
        return departmentDAO.findAll();
    }

    @Override
    public Department findById(Long theId) {
        Department department=departmentDAO.findById(theId);

        if(department==null){
            throw new NotFoundException("Department is not found - "+ theId);
        }

        return department;
    }

    @Override
    public void save(Department department) {
        departmentDAO.save(department);
    }

    @Override
    public void deleteById(Long theId) {
        if (departmentDAO.findById(theId)==null){
            throw new NotFoundException("Employee is not found - "+ theId);
        }
        departmentDAO.deleteById(theId);
    }
}
