package com.example.multidatabase.multidatasource.mapper;

import com.example.multidatabase.multidatasource.entity.Student;

public interface StudentMapper {

    Student getStudentById(Integer id);

    int insert(Student student);

    int deleteStudent(Integer id);

    int updateStudent(Student student);
}
