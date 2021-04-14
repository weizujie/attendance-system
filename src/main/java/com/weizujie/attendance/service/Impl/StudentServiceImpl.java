package com.weizujie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weizujie.attendance.dto.LoginDTO;
import com.weizujie.attendance.entity.Student;
import com.weizujie.attendance.mapper.StudentMapper;
import com.weizujie.attendance.service.StudentService;
import com.weizujie.attendance.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageBean<Student> queryPage(Map<String, Object> paramMap) {
        PageBean<Student> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Student> datas = studentMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = studentMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int deleteStudent(List<Long> ids) {
        return studentMapper.deleteStudent(ids);
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public Student findById(Long sid) {
        return studentMapper.findById(sid);
    }

    @Override
    public int editStudent(Student student) {
        return studentMapper.editStudent(student);
    }

    @Override
    public Student login(LoginDTO loginDTO) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<Student>()
                .eq(Student::getUsername, loginDTO.getUsername())
                .eq(Student::getPassword, loginDTO.getPassword());
        return studentMapper.selectOne(wrapper);
    }

    @Override
    public boolean isStudentByClazzId(Long id) {
        List<Student> studentList = studentMapper.isStudentByClazzId(id);
        if (studentList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int editPswdByStudent(Student student) {
        return studentMapper.editPswdByStudent(student);
    }

    @Override
    public int findByName(String name) {
        return studentMapper.findByName(name);
    }
}