package com.example.service.impl;

import com.example.model.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-01
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {


    @Override
    public Student selectById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void saveStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        this.baseMapper.insert(student);
        System.out.println(student.getId());
    }

    @Override
    public Student getStudentById(Integer id) {
        Student student = this.baseMapper.selectById(id);
        return student;
    }
}
