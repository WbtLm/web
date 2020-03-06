package com.dao;
import com.entity.DBRegister;

public interface RegisterDao {
	
	
	/*
	 * 预约挂号
	 */
	Integer insertRegister(DBRegister register);
	/*
	 * 查询挂号信息
	 */
	DBRegister selectRegisterById(int id);
	

}
