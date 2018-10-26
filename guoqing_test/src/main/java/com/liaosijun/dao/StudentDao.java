package com.liaosijun.dao;

import com.liaosijun.entity.Student;

import java.util.List;

public interface StudentDao {
    /**
     * 查询所有学生的方法
     * */
    List<Student> findAll();
}
