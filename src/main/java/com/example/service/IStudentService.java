package com.example.service;

import com.example.model.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-01
 */
public interface IStudentService extends IService<Student> {
    Student selectById(Integer id);

    void saveStudent(Student student);

    Student getStudentById(Integer id);
}
