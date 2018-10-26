package com.liaosijun.service;

import com.liaosijun.entity.Student;

import java.util.List;

/**
 * 业务层接口
 * */
public interface StudentService {
    /**
     * 测试方法
     * */
    void testFunction();

    /**
     * 查询所有学生的方法
     * */
    List<Student> findAll();
}
