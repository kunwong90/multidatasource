package com.example.multidatabase.multidatasource;


import com.example.multidatabase.multidatasource.entity.Student;
import com.example.multidatabase.multidatasource.mapper.StudentMapper;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {


    @Resource
    private StudentMapper studentMapper;

    @org.junit.Test
    public void getStudentById() {
        Student student = studentMapper.getStudentById(1);
        System.out.println(student);
        studentMapper.updateStudent(student);
    }

    @org.junit.Test
    public void updateStudent() {
        Student student = new Student();
        student.setId(1);
        student.setName("tom1");
        studentMapper.updateStudent(student);

        studentMapper.getStudentById(1);

        studentMapper.updateStudent(student);


        studentMapper.getStudentById(1);

        studentMapper.updateStudent(student);
    }


    @org.junit.Test
    public void insert() {
        Student student = new Student();
        student.setName("tom");
        studentMapper.insert(student);
    }
}
