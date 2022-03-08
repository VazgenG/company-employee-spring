package com.example.companyemployeespring.repository;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByCompany(Company company);
}
