package com.eyo.api_sim.service;

import com.eyo.api_sim.entity.Department;
import com.eyo.api_sim.exception.DepartmentNotFoundException;
import com.eyo.api_sim.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Tech Street")
                .departmentCode("IT-8899")
                .build();
        Department department2 = Department.builder()
                .departmentId(2L)
                .departmentName("LAW")
                .departmentAddress("Legal Street")
                .departmentCode("LG-221")
                .build();

        Mockito.when(departmentRepository.findAll()).thenReturn(List.of(department, department2));
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        Mockito.when(departmentRepository.findDepartmentByDepartmentNameIgnoreCase("eyo")).thenReturn(department);
    }

    @Test
    public void whenFetch_theReturnAllDepartments() {
        List<Department> departmentList = departmentService.getDepartmentList();

        assertTrue(departmentList.size() > 0);
    }

    @Test
    public void whenValidDepartmentName_thenReturnDepartment() {
        String departmentName = "eyo";
        Department found = departmentService.getDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }

    @Test
    public void whenInvalidOrEmptyDepartmentName_thenReturnNull() {
        String departmentName = "eyos";
        Department notFound = departmentService.getDepartmentByName(departmentName);
        assertNull(notFound);
    }

    @Test
    public void whenValidId_theReturnDepartment() throws DepartmentNotFoundException {
        Long id = 1L;
        Department iDFound = departmentService.getDepartmentById(id);

        assertEquals(iDFound.getDepartmentId(),id);
    }

    @Test
    public void whenInvalidId_thenThrowDepartmentNotFoundException() {
        Long id =2L;

        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getDepartmentById(id), "DepartmentNotFoundException");
    }

    @Test
    public void whenValidDepartmentId_thenReturnUpdatedDepartment() {
        Long departmentId = 1L;
        Department newDepartment = Department.builder()
                .departmentName("ENG")
                .build();

        department.setDepartmentName("ENG");

        Mockito.when(departmentRepository.save(any())).thenReturn(department);
        Department updatedDepartment = departmentService.updateDepartment(newDepartment, departmentId);

        assertEquals(updatedDepartment.getDepartmentName(), newDepartment.getDepartmentName());
    }
}