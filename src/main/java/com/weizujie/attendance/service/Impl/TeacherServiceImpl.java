package com.weizujie.attendance.service.Impl;

import com.weizujie.attendance.entity.Teacher;
import com.weizujie.attendance.mapper.TeacherMapper;
import com.weizujie.attendance.service.TeacherService;
import com.weizujie.attendance.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageBean<Teacher> queryPage(Map<String, Object> paramMap) {
        PageBean<Teacher> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Teacher> datas = teacherMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = teacherMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int deleteTeacher(List<Long> ids) {
        return teacherMapper.deleteTeacher(ids);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public Teacher findById(Long tid) {
        return teacherMapper.findById(tid);
    }

    @Override
    public int editTeacher(Teacher teacher) {
        return teacherMapper.editTeacher(teacher);
    }

    @Override
    public Teacher findByTeacher(Teacher teacher) {
        return teacherMapper.findByTeacher(teacher);
    }

    @Override
    public int editPswdByTeacher(Teacher teacher) {
        return teacherMapper.editPswdByTeacher(teacher);
    }
}
