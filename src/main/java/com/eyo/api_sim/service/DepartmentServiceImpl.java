package com.eyo.api_sim.service;

import com.eyo.api_sim.entity.Department;
import com.eyo.api_sim.exception.DepartmentNotFoundException;
import com.eyo.api_sim.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()) {
            throw new DepartmentNotFoundException("Department with ID " + departmentId + " not available");
        }
        return departmentOptional.get();
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

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.findDepartmentByDepartmentNameIgnoreCase(departmentName);
    }

}
