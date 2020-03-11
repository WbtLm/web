package com.dao;
import com.entity.DBDoctor;
import com.entity.DBPatient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    DBDoctor selectDoctorById(int d_id);
    /*
        通过医师id获取患者 
    */
    List<DBPatient> getPatientList(int d_id);
    /*
        医师修改患者挂号状态 ,i为挂号状态
    */
    Integer updateRegisterStatus(@Param("d_id") int d_id,@Param("p_id") int p_id,@Param("i") int i);
    
    
}
