package com.eyo.api_sim.service;

import com.eyo.api_sim.entity.Department;
import com.eyo.api_sim.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Department department, Long departmentId) {
        Department dept = departmentRepository.findById(departmentId).get();
        if (Objects.nonNull(department.getDepartmentName())
                && department.getDepartmentName().length() > 0
                && !department.getDepartmentName().equalsIgnoreCase(dept.getDepartmentName())) {
            dept.setDepartmentName(department.getDepartmentName());
        }

        if (Objects.nonNull(department.getDepartmentAddress())
                && department.getDepartmentAddress().length() > 0
                && !department.getDepartmentAddress().equalsIgnoreCase(dept.getDepartmentAddress())) {
            dept.setDepartmentAddress(department.getDepartmentAddress());
        }

        if (Objects.nonNull(department.getDepartmentCode())
                && department.getDepartmentCode().length() > 0
                && !department.getDepartmentCode().equalsIgnoreCase(dept.getDepartmentCode())) {
            dept.setDepartmentCode(department.getDepartmentCode());
        }
        return departmentRepository.save(dept);
    }

}
