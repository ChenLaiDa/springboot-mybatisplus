package com.example.mapper;

import com.example.model.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-11-01
 */
public interface StudentMapper extends BaseMapper<Student> {

    void addStudent(@Param("student") Student student);

    Student getStudent(@Param("id") Integer id);

    List<Student> getStudentList(@Param("student")Student student);

    void bathInsert(@Param("studentList") List<Student> studentList);

    void bathUpdate(@Param("updateStudentList") List<Student> studentList);

    List<Student> queryByIds(@Param("list") List<Integer> ids);



}
