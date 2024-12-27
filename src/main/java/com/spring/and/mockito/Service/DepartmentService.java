package com.spring.and.mockito.Service;

import com.spring.and.mockito.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findEmployeeWithMaxSalary(Integer departmentId);

    Employee findEmployeeWithMinSalary(Integer departmentId);

    Map<Integer, List<Employee>> findEmployeesByDepartmentSortedByNameSurname();

    Collection<Employee> findEmployeesByDepartmentSortedByNameSurname(Integer departmentId);

    List<String> useFlatMapTest();
}
