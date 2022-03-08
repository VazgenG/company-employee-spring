package com.example.companyemployeespring.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private double salary;
    private String position;
    private String picUrl;


    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;


}
