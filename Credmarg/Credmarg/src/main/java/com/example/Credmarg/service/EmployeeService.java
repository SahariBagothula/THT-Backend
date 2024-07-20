package com.example.Credmarg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Credmarg.model.Employee;
import com.example.Credmarg.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).get();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void updateEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(employee.getName());
            existingEmployee.setDesignation(employee.getDesignation());
            existingEmployee.setCtc(employee.getCtc());
            existingEmployee.setEmail(employee.getEmail());
            employeeRepository.save(existingEmployee);
        } else {
            throw new RuntimeException("Employee not found with id: " + employee.getId());
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
