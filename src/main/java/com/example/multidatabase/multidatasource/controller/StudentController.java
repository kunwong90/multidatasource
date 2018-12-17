package com.example.multidatabase.multidatasource.controller;

import com.example.multidatabase.multidatasource.entity.Student;
import com.example.multidatabase.multidatasource.service.IStudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping
@RestController
public class StudentController {

    @Resource
    private IStudentService studentService;

    @GetMapping(value = "/getStudentById/{id}")
    public Student getStudentById(@PathVariable("id") Integer id) {
        return studentService.getStudentById(id);
    }


    @PostMapping(value = "/insertStudent")
    public void insertStudent(Student student) {
        studentService.insertStudent(student);
    }


    @PutMapping(value = "/updateStudent")
    public void updateStudent(Student student) {
        studentService.updateStudent(student);
    }
}
