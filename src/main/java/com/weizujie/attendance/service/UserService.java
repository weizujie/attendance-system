package com.weizujie.attendance.service;

import com.weizujie.attendance.entity.User;
import com.weizujie.attendance.utils.PageBean;
import com.weizujie.attendance.utils.R;

import java.util.List;
import java.util.Map;

/**
 * @author weizujie
 */
public interface UserService {

    /**
     * 修改密码
     * @param user 修改参数
     * @return 影响条数
     */
    int editPwd(User user);

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User getByUsername(String username);

    /**
     * 添加用户
     * @param user 添加参数
     * @return 是否成功
     */
    int addUser(User user);

    /**
     * 删除用户
     */
    int deleteUser(List<Integer> ids);

    /**
     * 分页展示学生列表
     * @param paramMap 查询参数
     * @return 学生列表
     */
    PageBean<User> getStudentPage(Map<String, Object> paramMap);

    /**
     * 分页展示教师列表
     * @param paramMap 查询参数
     * @return 教师列表
     */
    PageBean<User> getTeacherPage(Map<String, Object> paramMap);

    int updateUser(User user);


    boolean checkStudentInCourse(Integer id);
}
