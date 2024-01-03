package com.example.simple.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.simple.exception.ResourceNotFoundException;
import com.example.simple.model.Employee;
import com.example.simple.repository.EmployeeRepository;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")

public class EmployeeController {
	

	@Autowired
		private EmployeeRepository employeeRepository;
		
		
		//GET ALL EMPLOYEEZZ
		
		@GetMapping("/employees")
		public List<Employee> getallEmployees(){
			return employeeRepository.findAll();
			
		}
		//Create Employee in restApi
		
		@PostMapping("/employees")
		public Employee createemployee(@RequestBody Employee employee) {
			//TODO: process POST request
			
			return employeeRepository.save(employee);
		}
		
//		FIND BY EMPLOYEE ID
		
		@GetMapping("/employees/{id}")
		public ResponseEntity<Employee> getEmployeeid(@PathVariable Long id 	) {
			
			Employee emp=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Employee Was found "+id));
		
		return ResponseEntity.ok(emp);
		}
		
//		 UPDATE EMPLOYEE LIST
		
		@PutMapping("/employees/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
			Employee employee = employeeRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			employee.setEmailId(employeeDetails.getEmailId());
			
			Employee updatedEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updatedEmployee);
		}
		
		
//				DELETE EMPLOYEE 
		
		@DeleteMapping("employees/{id}")
		public ResponseEntity <Map<String , Boolean>>  deleteEmployee(@PathVariable Long id){
			
			Employee emp=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("404 Not Employee "+id));
			
			employeeRepository.delete(emp);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted ur Selected employee", Boolean.TRUE);
			
			return  ResponseEntity.ok(response);
					
			
		}
		
		
		
	}

