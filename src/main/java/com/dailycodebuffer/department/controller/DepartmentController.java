package com.dailycodebuffer.department.controller;

import com.dailycodebuffer.department.entity.Department;
import com.dailycodebuffer.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {

        return departmentService.saveDepartment(department);

    }

    @GetMapping("{id}")
    public Department findDemaprtmentById(@PathVariable("id") Long id) {

        return departmentService.findDepartmentbyId(id);
    }

    @GetMapping("welcome/{name}/{place}/")
    public String findDemaprtmentById(@PathVariable("name") String name,@PathVariable("place") String place) {
    //public String findDemaprtmentById() {
        //return "Hello " + name + "! Thanks for visiting the page!!";
        return "Hello !!!"+name+", Thanks for visiting the page! "+place+" is a nice place!";
    }
}
