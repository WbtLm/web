package com.dao;
import com.entity.DBDoctor;
import com.entity.DBPatient;

public interface DoctorDao {
	/*
	  注册医师
	*/
    Integer insertDoctor(DBDoctor doctor);
    /*
        更新医师信息
    */
    Integer updateDoctor(DBDoctor doctor);
    /*
       登录获取密码
    */
    String getDoctorPassword(String account);
    /*
       通过医师id获取信息
    */
    DBPatient selectDoctorById(int d_id);
    /*
        通过医师id获取患者 
    */
    DBPatient getPatientList(int d_id);
    /*
        医师修改患者挂号状态 ,i为挂号状态
    */
    Integer updateRegisterStatus(int d_id,int p_id,int i);
    
    
}
