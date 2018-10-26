package com.liaosijun.controller;

import com.liaosijun.entity.Student;
import com.liaosijun.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        studentService.testFunction();
        return "name";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Student> findAll() {
        Long start = System.currentTimeMillis();
        List<Student> list = studentService.findAll();
        Long res = System.currentTimeMillis() - start;
        System.out.println("查询所有花费时间:"+res);
        return list;
    }
}
