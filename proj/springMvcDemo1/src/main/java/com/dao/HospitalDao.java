package com.dao;
import com.entity.DBHospital;

public interface HospitalDao {
	/*
	 * 预约住院
	 */
    Integer insertHospital(DBHospital hospital);
    /*
     * 查询住院信息
     */
    DBHospital InselectHospitalById(int id);
}
