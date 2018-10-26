package com.liaosijun.service.impl;

import com.liaosijun.annotation.CacheSelect;
import com.liaosijun.dao.StudentDao;
import com.liaosijun.entity.Student;
import com.liaosijun.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层实现类
 * */
@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    /**
     * 测试方法
     * */
    @Override
    public void testFunction() {
        System.out.println("业务层：执行了...");
    }

    /**
     * 查询所有的方法
     * */
    @Override
    @CacheSelect
    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
