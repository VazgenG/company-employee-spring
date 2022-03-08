package com.example.companyemployeespring.controller;


import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeContraller {

    @Value("${company-employee-spring.upload.path}")
    private String imagePath;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;


    @GetMapping("/employees")
    public String employeesPage(ModelMap map) {

        List<Employee> employees = employeeRepository.findAll();
        map.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees/byCompany/{id}")
    public String employeesByCompanyPage(ModelMap map, @PathVariable("id") int id) {
        Company company = companyRepository.getById(id);
        List<Employee> employees = employeeRepository.findAllByCompany(company);
        map.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees/add")
    public String addEmployeePage(ModelMap map){
        map.addAttribute("companies", companyRepository.findAll());
        return "saveEmployee";
    }

    @PostMapping("/emploees/add")
    public String addEmployee(@ModelAttribute Employee employee,
                          @RequestParam("picture")MultipartFile uploadedFile) throws IOException {
        if(!uploadedFile.isEmpty()){
            String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
            File newFile = new File(imagePath + fileName);
            uploadedFile.transferTo(newFile);
            employee.setPicUrl(fileName);
        }
        employeeRepository.save(employee);
        return "redirect:/employees";
    }
}
