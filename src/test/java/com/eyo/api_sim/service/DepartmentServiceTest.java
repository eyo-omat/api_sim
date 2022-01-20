package com.eyo.api_sim.service;

import com.eyo.api_sim.entity.Department;
import com.eyo.api_sim.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentId(1L)
                .departmentName("eyo")
                .departmentAddress("Lige")
                .departmentCode("ee-gg")
                .build();
        Mockito.when(departmentRepository.findDepartmentByDepartmentNameIgnoreCase("eyo")).thenReturn(department);
    }

    @Test
    public void whenValidDepartmentName_thenReturnDepartment() {
        String departmentName = "eyo";
        Department found = departmentService.getDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }
}