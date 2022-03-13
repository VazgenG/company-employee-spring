package com.example.companyemployeespring.controller;


import com.example.companyemployeespring.dto.CreateEmployeeRequest;
import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.entity.EmployeeImage;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeImageRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import com.example.companyemployeespring.service.CompanyService;
import com.example.companyemployeespring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class EmployeeContraller {

//    @Value("${company-employee-spring.upload.path}")
//    private String imagePath;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//    @Autowired
//    private CompanyRepository companyRepository;
//    @Autowired
//    private EmployeeImageRepository employeeImageRepository;
//
//
//    @GetMapping("/employees")
//    public String employeesPage(ModelMap map) {
//
//        List<Employee> employees = employeeRepository.findAll();
//        map.addAttribute("employees", employees);
//        return "employees";
//    }
//
//    @GetMapping("/employees/byCompany/{id}")
//    public String employeesByCompanyPage(ModelMap map, @PathVariable("id") int id) {
//        Company company = companyRepository.getById(id);
//        List<Employee> employees = employeeRepository.findAllByCompany(company);
//        map.addAttribute("employees", employees);
//        return "employees";
//    }
//
//    @GetMapping("/employees/{id}")
//    public String singleEmployee(@PathVariable  int id, ModelMap map) {
//        Employee employee = employeeRepository.findById(id).orElseThrow(RuntimeException::new);
//        map.addAttribute("employee", employee);
//        return "singleEmployee";
//    }
//    @GetMapping("/employees/add")
//    public String addEmployeePage(ModelMap map){
//        map.addAttribute("companies", companyRepository.findAll());
//        return "saveEmployee";
//    }
//
//    @PostMapping("/addEmployee")
//    public String addEmployee(@ModelAttribute Employee employee,
//                              @RequestParam("pictures") MultipartFile[] uploadedFiles) throws IOException {
//        employeeRepository.save(employee);
//        if (uploadedFiles.length != 0) {
//            for (MultipartFile uploadedFile : uploadedFiles) {
//
//                String fileName = System.currentTimeMillis() + "_" + uploadedFile.getOriginalFilename();
//                File newFile = new File(imagePath + fileName);
//                uploadedFile.transferTo(newFile);
//                EmployeeImage employeeImage = EmployeeImage.builder()
//                        .name(fileName)
//                        .employee(employee)
//                        .build();
//                employeeImageRepository.save(employeeImage);
//            }
//        }
//        return "redirect:/employees";
//    }
private final EmployeeService employeeService;
    private final CompanyService companyService;


    @GetMapping("/employees")
    public String employeesPage(ModelMap map) {
        List<Employee> employees = employeeService.findAll();
        map.addAttribute("employees", employees);

        return "employees";
    }

    @GetMapping("/employees/byCompany/{id}")
    public String employeesByCompanyPage(ModelMap map, @PathVariable("id") int id) {
        Company company = companyService.findById(id);
        List<Employee> employees = employeeService.findAllByCompany(company);
        map.addAttribute("employees", employees);

        return "employees";
    }

    @GetMapping("/employees/add")
    public String addEmployeePage(ModelMap map) {
        map.addAttribute("companies", companyService.findAll());
        return "saveEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute CreateEmployeeRequest createEmployeeRequest,
                          @RequestParam("pictures") MultipartFile[] uploadedFiles) throws IOException {
        employeeService.addEmployeeFromEmployeeRequest(createEmployeeRequest, uploadedFiles);
        return "redirect:/employees";
    }



    @GetMapping("/employees/{id}")
    public String singleEmployee(@PathVariable int id, ModelMap map) {
        map.addAttribute("employee", employeeService.findById(id));
        return "singleEmployee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteById(id);
        return "redirect:/";
    }
}
