package com.yht.shiro2.project.user.mapper;

import com.yht.shiro2.project.user.entity.User;

import java.util.List;

/**
 * 数据层 User
 */
public interface UserMapper {

    /**
     * 根据条件分页查找用户
     * @param user
     * @return
     */
    public List<User> selectAllUser(User user);

    /**
     * 根据用户ID查找用户
     * @param userId
     * @return
     */
    public User selectUserByUserId(Integer userId);

    /**
     * 根据登陆名查找用户
     * @param loginName
     * @return
     */
    public User selectUserByLoginName(String loginName);

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    public User selectUserByEmail(String email);

    /**
     * 根据电话号码查询用户
     * @param phonenumber
     * @return
     */
    public User selectUserByPhonenumber(String phonenumber);

    /**
     * 修改用户
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * 根据用户ID删除用户
     * @param userId
     * @return
     */
    public int deleteUserByUserId(Integer userId);

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    public int bacthDeleteUserByUserId(Integer[] ids);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * 校验邮箱唯一性
     */
    public User checkEmailUnique(String email);

    /**
     * 校验邮箱唯一性
     * @param phonenumber
     * @return
     */
    public User checkPhonenumberUnique(String phonenumber);


    /**
     * 校验登陆名唯一性
     */
    public int checkLoginNameUnique(String loginName);
}
