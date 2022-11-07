package com.example.demo;

public class Employee {
    public String getName() {
        return name;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setYearsOfService(int yearsOfService) {
        this.yearsOfService = yearsOfService;
    }

    private int yearsOfService;


}
