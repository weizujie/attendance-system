package com.weizujie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weizujie.attendance.entity.User;
import com.weizujie.attendance.mapper.UserMapper;
import com.weizujie.attendance.service.UserService;
import com.weizujie.attendance.utils.PageBean;
import com.weizujie.attendance.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author weizujie
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int editPwd(User user) {
        return userMapper.updateById(user);
    }


    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public int addUser(User user) {
        String salt = SaltUtil.getSalt(8);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setSalt(salt);
        user.setPassword(md5Hash.toString());
        return userMapper.insert(user);
    }

    @Override
    public PageBean<User> getStudentPage(Map<String, Object> paramMap) {
        PageBean<User> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);

        // 获取所有学生列表
        List<User> users = userMapper.getStudentList(paramMap);
        pageBean.setDatas(users);

        Integer totalSize = userMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalSize);
        return pageBean;
    }

    @Override
    public PageBean<User> getTeacherPage(Map<String, Object> paramMap) {
        PageBean<User> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);

        // 获取所有学生列表
        List<User> users = userMapper.getTeacherList(paramMap);
        pageBean.setDatas(users);

        Integer totalSize = userMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalSize);
        return pageBean;
    }

    @Override
    public int deleteUser(List<Integer> ids) {
        return userMapper.deleteBatchIds(ids);
    }

}
