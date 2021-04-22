package com.weizujie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weizujie.attendance.dto.LoginDTO;
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
    public int deleteTeacher(List<Integer> ids) {
        return teacherMapper.deleteBatchIds(ids);
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    @Override
    public int editTeacher(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }

    @Override
    public Teacher login(LoginDTO loginDTO) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<Teacher>()
                .eq(Teacher::getUsername, loginDTO.getUsername())
                .eq(Teacher::getPassword, loginDTO.getPassword());
        return teacherMapper.selectOne(wrapper);
    }

    @Override
    public int editPswdByTeacher(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }
}
