package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.annotations.DataPermission;
import com.example.common.reponse.BaseResult;
import com.example.model.entity.Student;
import com.example.service.IStudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2021-11-01
 */
@RestController
@RequestMapping("/student")
@Api(tags = "学生管理")
public class StudentController {
    @Autowired
    private IStudentService studentService;


    @ApiOperation(value = "保存学生",notes = "保存学生")
    @PostMapping("/saveStudent")
    public BaseResult saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);
        return BaseResult.success();
    }


    @ApiOperation(value = "根据id获取学生信息",notes = "根据id获取学生信息")
    @PostMapping("/getStudentById")
    public BaseResult<Student> getStudentById(@RequestParam("id") Integer id){
        Student student = studentService.getStudentById(id);
        return BaseResult.success(student);
    }

    @ApiOperation(value = "获取所有的学生",notes = "获取所有的学生")
    @PostMapping("/getAllStudent")
    @DataPermission
    public BaseResult<List<Student>> getAllStudent(){
        List<Student> list = studentService.list(new LambdaQueryWrapper<Student>().eq(Student::getIsDelete, 0));
        return BaseResult.success(list);
    }


}

