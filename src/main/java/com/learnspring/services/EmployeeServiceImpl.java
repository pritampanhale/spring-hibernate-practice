package com.learnspring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnspring.dao.EmployeeDaoImpl;
import com.learnspring.model.Employee;
 
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl {
 
    @Autowired
    private EmployeeDaoImpl dao;
     
    public void saveEmployee() {
    	Employee e = new Employee();
    	//e.setId(1);
    	e.setName("Pritam");
        dao.saveEmployee(e);
    }
 
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }
 
    public void deleteEmployeeBySsn(String ssn) {
        dao.deleteEmployeeBySsn(ssn);
    }
 
    public Employee findBySsn(String ssn) {
        return dao.findBySsn(ssn);
    }
 
    public void updateEmployee(Employee employee){
        dao.updateEmployee(employee);
    }
}