package com.spring.and.mockito.Service;

import com.spring.and.mockito.Employee;
import com.spring.and.mockito.Exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentServiceImpl implements  DepartmentService {

    private final ServiceEmployee serviceEmployee;

    public DepartmentServiceImpl(ServiceEmployee serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }


    @Override
    public Employee findEmployeeWithMaxSalary(Integer departmentId) {
        return serviceEmployee
                .findAll()
                .stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee findEmployeeWithMinSalary(Integer departmentId) {
        return serviceEmployee
                .findAll()
                .stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Map<Integer, List<Employee>> findEmployeesByDepartmentSortedByNameSurname() {
        return serviceEmployee
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Employee::getLastName).thenComparing(Employee::getFirstName))
                .collect(groupingBy(Employee::getDepartmentId));
    }

    @Override
    public Collection<Employee> findEmployeesByDepartmentSortedByNameSurname(Integer departmentId) {
        return serviceEmployee
                .findAll()
                .stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(toList());
    }



    @Override
    public List<String> useFlatMapTest() {
        return List.of();
    }
}
