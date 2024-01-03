package com.example.simple.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.simple.model.Employee;



@Repository
	public interface EmployeeRepository extends JpaRepository<Employee, Long>{

		
		
	}

