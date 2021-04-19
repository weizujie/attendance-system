package com.weizujie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.weizujie.attendance.entity.Course;
import com.weizujie.attendance.entity.SelectedCourse;
import com.weizujie.attendance.mapper.CourseMapper;
import com.weizujie.attendance.mapper.SelectedCourseMapper;
import com.weizujie.attendance.service.SelectedCourseService;
import com.weizujie.attendance.utils.PageBean;
import com.weizujie.attendance.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {

    @Autowired
    private SelectedCourseMapper selectedCourseMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageBean<SelectedCourse> queryPage(Map<String, Object> paramMap) {
        PageBean<SelectedCourse> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<SelectedCourse> datas = selectedCourseMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = selectedCourseMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    @Transactional
    public int addSelectedCourse(SelectedCourse selectedCourse) {
        LambdaQueryWrapper<SelectedCourse> wrapper = new LambdaQueryWrapper<SelectedCourse>()
                .eq(SelectedCourse::getStudentId, selectedCourse.getStudentId())
                .eq(SelectedCourse::getCourseId, selectedCourse.getCourseId());
        SelectedCourse selectOne = selectedCourseMapper.selectOne(wrapper);

        if (Objects.isNull(selectOne)) {
            int count = courseMapper.addStudentNum(selectedCourse.getCourseId());
            if (count == 1) {
                selectedCourseMapper.insert(selectedCourse);
                return count;
            }
            if (count == 0) {
                return count;
            }
        } else {
            return 2;
        }
        return 3;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSelectedCourse(Long selectedCourseId) {
        SelectedCourse selectedCourse = selectedCourseMapper.selectById(selectedCourseId);
        if (Objects.isNull(selectedCourse)) {
            return -1;
        }
        // 退课
        return selectedCourseMapper.deleteById(selectedCourseId);
    }

    @Override
    public boolean isStudentId(Long studentId) {
        LambdaQueryWrapper<SelectedCourse> wrapper = new LambdaQueryWrapper<SelectedCourse>()
                .eq(SelectedCourse::getStudentId, studentId);
        List<SelectedCourse> selectedCourseList = selectedCourseMapper.selectList(wrapper);
        return selectedCourseList.isEmpty();
    }

    @Override
    public List<SelectedCourse> getAllBySid(Long studentid) {
        LambdaQueryWrapper<SelectedCourse> wrapper = new LambdaQueryWrapper<SelectedCourse>()
                .eq(SelectedCourse::getStudentId, studentid);
        List<SelectedCourse> selectedCourses = selectedCourseMapper.selectList(wrapper);
        System.out.println(selectedCourses.toString());
        return selectedCourseMapper.getAllBySid(studentid);
    }
}
