package com.eyo.api_sim.controller;

import com.eyo.api_sim.entity.Department;
import com.eyo.api_sim.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department")
    public Department saveDepartment(@RequestBody Department department) {

        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> getDepartmentList() {
        return departmentService.getDepartmentList();
    }

    @GetMapping("/departments/{departmentId}")
    public Department getDepartmentByID(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @DeleteMapping("/department/{departmentId}")
    public String deleteDepartmentById(@PathVariable Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Successfully deleted Department";
    }

    @PutMapping("/department/{departmentId}")
    public Department updateDepartment(@RequestBody Department department,
                                       @PathVariable Long departmentId) {
        return departmentService.updateDepartment(department, departmentId);
    }

    @GetMapping("/department/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String departmentName) {
        return departmentService.getDepartmentByName(departmentName);
    }
}
