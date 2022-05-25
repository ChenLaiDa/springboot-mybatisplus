package com.example;

import com.example.mapper.StudentMapper;
import com.example.model.entity.Student;
import com.example.service.IStudentService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {
    @Autowired
    private IStudentService studentService;

    @Test
    void test() {
        Student student = studentService.selectById(1);
        System.out.println(student);
    }

    @Test
    public void test1() throws IOException {
        // 读取mybatis的配置文件
        InputStream in = Resources.getResourceAsStream("config/mybatisconfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 动态代理模式获取mapper实例
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.getStudent(1);
        System.out.println("test1==============>"+student.getName());
    }


}
