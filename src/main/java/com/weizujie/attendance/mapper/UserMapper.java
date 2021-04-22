package com.weizujie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weizujie.attendance.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author weizujie
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    List<User> getStudentList(Map<String, Object> paramMap);

    List<User> getTeacherList(Map<String, Object> paramMap);
}
