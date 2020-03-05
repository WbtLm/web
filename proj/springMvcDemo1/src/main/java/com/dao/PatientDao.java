package com.dao;

import com.entity.Patient;
import com.entity.Doctor;

import java.util.List;

public interface PatientDao {
    /*
        注册患者
     */
    Integer insertPatient(Patient patient);
    /*
        更新患者信息
     */
    Integer updatePatient(Patient patient);
    /*
        登录获取密码
     */
    String getPatientPassword(String account);
    /*
        通过患者id获取信息
     */
    Patient selectPatientById(int id);
    /*
        获取医生列表
     */
    List<Doctor> getDoctorList();
}
