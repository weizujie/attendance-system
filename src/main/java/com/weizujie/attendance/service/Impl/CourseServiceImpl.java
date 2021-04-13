package com.weizujie.attendance.service.Impl;

import com.weizujie.attendance.entity.Course;
import com.weizujie.attendance.mapper.CourseMapper;
import com.weizujie.attendance.service.CourseService;
import com.weizujie.attendance.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageBean<Course> queryPage(Map<String, Object> paramMap) {
        PageBean<Course> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Course> datas = courseMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = courseMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public int editCourse(Course course) {
        return courseMapper.editCourse(course);
    }

    @Override
    public int deleteCourse(List<Long> ids) {
        return courseMapper.deleteCourse(ids);
    }

    @Override
    public List<Course> getCourseById(List<Long> ids) {
        return courseMapper.getCourseById(ids);
    }

    @Override
    public long findByName(String name) {
        return courseMapper.findByName(name);
    }

}
