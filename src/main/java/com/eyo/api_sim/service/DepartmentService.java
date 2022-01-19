package com.eyo.api_sim.service;

import com.eyo.api_sim.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getDepartmentList();

    Department getDepartmentById(Long departmentId);

    void deleteDepartmentById(Long departmentId);

    Department updateDepartment(Department department, Long departmentId);

    Department getDepartmentByName(String departmentName);
}
