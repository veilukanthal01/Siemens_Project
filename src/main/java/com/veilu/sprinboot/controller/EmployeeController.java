package com.veilu.sprinboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veilu.sprinboot.entity.Employee;
import com.veilu.sprinboot.entity.Organization;
import com.veilu.sprinboot.exception.EmployeeAlredayExistsException;
import com.veilu.sprinboot.exception.EmployeeNotFoundException;
import com.veilu.sprinboot.exception.OrganizationAlredayExistsException;
import com.veilu.sprinboot.exception.OrganizationNotFoundException;
import com.veilu.sprinboot.service.EmployeeService;
import com.veilu.sprinboot.service.OrganizationService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> findAllEmployees(){
		return this.employeeService.findAllEmployees();
		
	}
	
	@DeleteMapping("/{employeeId}")
	public String  deleteOrgById(@PathVariable long employeeId){
		this.employeeService.deleteEmpById(employeeId);
		return "success";
		
	}
	
	@PostMapping
	public ResponseEntity createEmployee(@RequestBody Employee Employee) {
		try {
			 return new ResponseEntity<Employee>(this.employeeService.saveEmployee(Employee), HttpStatus.OK);
		}
		catch (EmployeeAlredayExistsException ee){
			return new ResponseEntity<>(ee.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity getOrgById(@PathVariable long empId){
		try {
			 return new ResponseEntity<Employee>(this.employeeService.findEmployeeById(empId), HttpStatus.OK);
		}
		catch(EmployeeNotFoundException eex) {
			return new ResponseEntity<>(eex.getMessage(), HttpStatus.NOT_FOUND);
		}
			
		
		}

}
