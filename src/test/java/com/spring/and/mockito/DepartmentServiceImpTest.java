package com.spring.and.mockito;

import com.spring.and.mockito.Exception.EmployeeNotFoundException;
import com.spring.and.mockito.Service.DepartmentServiceImpl;
import com.spring.and.mockito.Service.ServiceEmployee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.spring.and.mockito.EmployeeTestConstants.*;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImpTest {

    @Mock
    private ServiceEmployee serviceEmployee;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    public void shouldFindEmployeeWithMaxSalaryByDepartmentId() {
        when(serviceEmployee.findAll()).thenReturn(EMPLOYEES);
        assertEquals(MAX_SALARY_EMPLOYEE, out.findEmployeeWithMaxSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldTrowEmployeeNotFoundExceptionWhenMaxSalaryInEmptyEmployeesList() {
        when(serviceEmployee.findAll()).thenReturn(emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployeeWithMaxSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldTrowEmployeeNotFoundExceptionWhenFindMaxSalaryInEmptyDepartment() {
        when(serviceEmployee.findAll()).thenReturn(EMPLOYEES);

        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployeeWithMaxSalary(BAD_DEPARTMENT_ID));
    }

    @Test
    public void shouldFindEmployeeWithMinSalaryByDepartmentId() {
        when(serviceEmployee.findAll()).thenReturn(EMPLOYEES);

        assertEquals(MIN_SALARY_EMPLOYEE, out.findEmployeeWithMinSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindMinSalaryInEmployeesList() {
        when(serviceEmployee.findAll()).thenReturn(emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployeeWithMinSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldTrowEmployeeNotFoundExceptionWhenFindMinSalaryInEmptyDepartment() {
        when(serviceEmployee.findAll()).thenReturn(EMPLOYEES);

        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployeeWithMinSalary(BAD_DEPARTMENT_ID));
    }

    @Test
    public void shouldReturnAlLEmployeeByDepartmentsWhenEmployeesExist() {
        when(serviceEmployee.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(DEPARTMENT_MAP, out.findEmployeesByDepartmentSortedByNameSurname());
    }

    @Test
    public void shouldReturnEmptyMapWhenEmployeesDontExist() {
        when(serviceEmployee.findAll()).thenReturn(emptyList());

        assertEquals(emptyMap(), out.findEmployeesByDepartmentSortedByNameSurname());
    }

    @Test
    public void shouldReturnEmployeesByDepartmentWhenDepartmentIsCorrectAndEmployeesExistThere() {
        when(serviceEmployee.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);

        assertEquals(singletonList(MIN_SALARY_EMPLOYEE), out.findEmployeesByDepartmentSortedByNameSurname(DEPARTMENT_ID));
        assertEquals(singletonList(DIFFERENT_DEPARTMENT_EMPLOYEE), out.findEmployeesByDepartmentSortedByNameSurname(BAD_DEPARTMENT_ID));
    }

    @Test
    public void shouldReturnEmptyListWhenEmployeesDontFoundInDepartment() {
        when(serviceEmployee.findAll()).thenReturn(EMPLOYEES);

        assertEquals(emptyList(), out.findEmployeesByDepartmentSortedByNameSurname(BAD_DEPARTMENT_ID));
    }


}
