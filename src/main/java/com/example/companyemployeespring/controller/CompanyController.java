package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") int id) {

        companyRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/addCompany")
    public String addCompanyPage() {

        return "saveCompany";
    }

    @PostMapping("/addCompany")
    public String addCompany(@ModelAttribute Company company){
        companyRepository.save(company);
        return "redirect:/";
    }

    @GetMapping("/editCompany/{id}")
    public String editCompanyPage(ModelMap map,
                               @PathVariable("id") int id) {

        Optional<Company> companyById = companyRepository.findById(id);
        if(companyById.isPresent()) {
            map.addAttribute("company", companyById.get());
            return "saveCompany";
        }else {
            return "redirect:/";
        }
    }
}





