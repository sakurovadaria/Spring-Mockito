package com.spring.and.mockito.Controller;

import com.spring.and.mockito.Employee;
import com.spring.and.mockito.Service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}/salary/max")
    public Employee findEmployeeWithMaxSalaryByDepartmentId(@PathVariable Integer departmentId) {
        return departmentService.findEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/{departmentId}/salary/min")
    public Employee findEmployeeWithMinSalaryByDepartmentId(@PathVariable Integer departmentId) {
        return departmentService.findEmployeeWithMinSalary(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findEmployees() {
        return departmentService.findEmployeesByDepartmentSortedByNameSurname();
    }

    @GetMapping(path = "/{departmentId}/employees")
    public Collection<Employee> findEmployees(@PathVariable Integer departmentId) {
        return departmentService.findEmployeesByDepartmentSortedByNameSurname(departmentId);
    }

}
