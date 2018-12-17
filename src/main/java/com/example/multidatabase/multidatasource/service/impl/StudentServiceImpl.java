package com.example.multidatabase.multidatasource.service.impl;

import com.example.multidatabase.multidatasource.entity.Student;
import com.example.multidatabase.multidatasource.mapper.StudentMapper;
import com.example.multidatabase.multidatasource.service.IStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Service
public class StudentServiceImpl implements IStudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertStudent(Student student) {
        studentMapper.insert(student);
        studentMapper.getStudentById(1);
        studentMapper.deleteStudent(1);
    }
}
