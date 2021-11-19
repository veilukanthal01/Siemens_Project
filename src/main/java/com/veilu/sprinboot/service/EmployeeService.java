package com.veilu.sprinboot.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veilu.sprinboot.entity.Employee;
import com.veilu.sprinboot.exception.EmployeeAlredayExistsException;
import com.veilu.sprinboot.exception.EmployeeNotFoundException;
import com.veilu.sprinboot.exception.OrganizationAlredayExistsException;
import com.veilu.sprinboot.exception.OrganizationNotFoundException;
import com.veilu.sprinboot.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee) throws EmployeeAlredayExistsException{
		List<Employee> empList = (List<Employee>) this.employeeRepository.findAll();
		if(empList.stream().anyMatch(e-> e.getFirstName().equals(employee.getFirstName()) && e.getLastName().equals(employee.getLastName()) 
				&& e.getDesignation().equals(employee.getDesignation()))) {
			 throw new EmployeeAlredayExistsException("Employee alreday exists");
		}
		return this.employeeRepository.save(employee);
		
	}
	
	public List<Employee> findAllEmployees() {
		return (List<Employee>)this.employeeRepository.findAll();
		
	}
	
	public Employee findEmployeeById(long empId) throws EmployeeNotFoundException {
		if(this.employeeRepository.findById(empId).isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found with the Id : "+ empId);
		}
		return this.employeeRepository.findById(empId).get();
	}

	public void deleteEmpById(long empId) {
		 this.employeeRepository.deleteById(empId);
		
	}
}
