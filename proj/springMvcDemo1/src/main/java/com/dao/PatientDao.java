package com.dao;

import com.entity.DBPatient;
import com.entity.DBDoctor;

import java.util.List;

public interface PatientDao {
    /*
        注册患者
     */
    Integer insertPatient(DBPatient patient);
    /*
        更新患者信息
     */
    Integer updatePatient(DBPatient patient);
    /*
        登录获取密码
     */
    String getPatientPassword(String account);
    /*
        通过患者id获取信息
     */
    DBPatient selectPatientById(int id);
    /*
        获取医生列表
     */
    List<DBDoctor> getDoctorList();
}
