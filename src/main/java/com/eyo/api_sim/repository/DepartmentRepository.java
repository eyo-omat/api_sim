package com.eyo.api_sim.repository;

import com.eyo.api_sim.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findDepartmentByDepartmentName(String departmentName);
    Department findDepartmentByDepartmentNameIgnoreCase(String departmentName);
}
