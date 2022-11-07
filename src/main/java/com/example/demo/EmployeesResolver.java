package com.example.demo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class EmployeesResolver implements DataFetcher<List<Employee>> {
    @Override
    public List<Employee> get(DataFetchingEnvironment environment) {
        Employee emp1 = new Employee();
        emp1.setName("Employee One");
        emp1.setYearsOfService(6);

        Employee emp2 = new Employee();
        emp2.setName("Employee Two");
        emp2.setYearsOfService(19);

        return asList(emp1, emp2);
    }
}
