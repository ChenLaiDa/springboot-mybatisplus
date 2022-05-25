package com.example.controller;


import com.example.common.reponse.BaseResult;
import com.example.model.entity.Student;
import com.example.service.IStudentService;
import com.sun.xml.internal.txw2.output.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

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


}

