package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public void deleteById(int id) {
        companyRepository.deleteById(id);
    }

    public Company findById(int id) {
        return companyRepository.getById(id);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
