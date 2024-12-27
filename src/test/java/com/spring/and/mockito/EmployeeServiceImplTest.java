package com.spring.and.mockito;

import com.spring.and.mockito.Exception.EmployeeAlreadyAddedException;
import com.spring.and.mockito.Exception.EmployeeNotFoundException;
import com.spring.and.mockito.Service.ServiceEmployee;
import com.spring.and.mockito.Service.ServiceEmployeeImpl;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static com.spring.and.mockito.EmployeeTestConstants.*;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {

    private final ServiceEmployee out = new ServiceEmployeeImpl();

    @Test
    public void shouldAddEmployeeWhenTheyDontExist() {
        //setup
        Employee expected = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(0, out.findAll().size());
        assertFalse(out.findAll().contains(expected));
        //run
        Employee actual = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        //assert
        assertEquals(expected, actual);
        assertEquals(1, out.findAll().size());
        assertTrue(out.findAll().contains(expected));
    }

    @Test
    public void shouldThrowEmployeeExistExceptionWhenTheyExist() {
        Employee existed = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertTrue(out.findAll().contains(existed));
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID));
    }

    @Test
    public void shouldFindEmployeeWhenTheyExist() {
        Employee existed = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(existed, out.find(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindEmployeeWhichDoesntExist() {
        assertEquals(0, out.findAll().size());
        assertThrows(EmployeeNotFoundException.class, () -> out.find(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID));
    }

    @Test
    public void shouldRemoveEmployeeWhenTheyExist() {
        Employee expected = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(1, out.findAll().size());
        assertTrue(out.findAll().contains(expected));
        //run
        Employee actual = out.remove(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        //assert
        assertEquals(expected, actual);
        assertTrue(out.findAll().isEmpty());
        assertFalse(out.findAll().contains(expected));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenRemoveEmployeeWhichDoesntExist() {
        assertTrue(out.findAll().isEmpty());
        assertThrows(EmployeeNotFoundException.class, () -> out.remove(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID));
    }

    @Test
    public void shouldReturnEmptyCollectionWhenEmployeeDontExist() {
        assertIterableEquals(emptyList(), out.findAll());
    }

    @Test
    public void shouldReturnListOfEmployeeWhenTheyExist() {
        Employee employee1 = out.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        Employee employee2 = out.add(FIRST_NAME2, LAST_NAME2, MIN_SALARY, DEPARTMENT_ID);
        Collection<Employee> expected = List.of(employee1, employee2);

        Collection<Employee> actual = out.findAll();

        assertIterableEquals(expected, actual);
    }
}
