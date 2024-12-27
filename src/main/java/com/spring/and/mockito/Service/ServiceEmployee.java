package com.spring.and.mockito.Service;

import com.spring.and.mockito.Employee;

import java.util.Collection;

public interface ServiceEmployee {
    Employee add(String firstName, String lastName, Integer salary, Integer departmentId);
    Employee remove(String firstName, String lastName, Integer salary, Integer departmentId);
    Employee find(String firstName, String lastName, Integer salary, Integer departmentId);

    Collection<Employee> findAll();

}