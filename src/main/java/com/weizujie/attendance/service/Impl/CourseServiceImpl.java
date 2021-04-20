package com.weizujie.attendance.service.Impl;

import com.weizujie.attendance.entity.Course;
import com.weizujie.attendance.mapper.CourseMapper;
import com.weizujie.attendance.service.CourseService;
import com.weizujie.attendance.utils.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author weizujie
 */
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

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
        return courseMapper.insert(course);
    }

    @Override
    public int editCourse(Course course) {
        return courseMapper.updateById(course);
    }

    @Override
    public int deleteCourse(List<Integer> ids) {
        return courseMapper.deleteBatchIds(ids);
    }

    @Override
    public List<Course> getCourseById(List<Integer> ids) {
        return courseMapper.getCourseById(ids);
    }
}
