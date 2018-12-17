package com.example.multidatabase.multidatasource.service;

import com.example.multidatabase.multidatasource.entity.Student;

public interface IStudentService {

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void insertStudent(Student student);
}
