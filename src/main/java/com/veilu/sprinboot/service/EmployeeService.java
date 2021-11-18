package com.veilu.sprinboot.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veilu.sprinboot.entity.Employee;
import com.veilu.sprinboot.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
		
	}
	
	public List<Employee> findAllEmployees() {
		return (List<Employee>)this.employeeRepository.findAll();
		
	}
	
	public Employee findEmployeeById(long empId) {
		return this.employeeRepository.findById(empId).get();
	}

	public void deleteEmpById(long empId) {
		 this.employeeRepository.deleteById(empId);
		
	}
}
