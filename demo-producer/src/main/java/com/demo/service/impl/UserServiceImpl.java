package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.mapper.UserMapper;
import com.demo.model.User;
import com.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 业务层
 */
@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	// DAO层，已整合了Mybatis及通用Mapper,单表简单查询无需写SQL，可以自动与Model松散绑定
    private UserMapper userMapper;
	
	/**
	 * 	按照主键查询
	 * @param id
	 * @return
	 */
	@Override
    public User getUserById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 	按照其他字段非模糊查询，模糊查询需要自己写SQL
     * @param userName
     * @return
     */
	@Override
	public List<User> getUserByName(String userName){
		User query = new User();
		query.setUserName(userName);
        return userMapper.select(query);
    }
    
    /**
     *	查询所有数据
     * @param id
     * @return
     */
	@Override
    public List<User> listAllUser(){
        return userMapper.selectAll();
    }
    
    /**
     *	利用PageHelper分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
	@Override
    public PageInfo<User> pageAllUser(Integer pageNum, Integer pageSize){
    	// 固定写法 传入页数，每页条数
    	PageHelper.startPage(pageNum, pageSize);
    	// 执行数据查询，返回一个List集合即可
    	List<User> userList = userMapper.selectAll();
    	// 固定写法，创建结果对象，将查询结果传入构造器
    	PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return pageInfo;
    }
    
    /**
     *	执行自己写的SQL,本例实现模糊查询
     * @param userName
     * @return
     */
	@Override
    public List<User> searchUserByName(String userName){
        return userMapper.searchUserByName(userName);
    }
    
    /**
     * 	插入
     * @param user
     */
	@Override
	@Transactional
    public void saveUser(User user) {
    	userMapper.insert(user);
    }
    
    /**
     * 	修改
     * @param user
     */
	@Override
	@Transactional
    public void editUser(User user) {
    	userMapper.updateByPrimaryKeySelective(user);
    }
    
    /**
     * 	删除
     * @param user
     */
	@Override
	@Transactional
    public void deleteUserById(Integer id) {
    	userMapper.deleteByPrimaryKey(id);
    }
}