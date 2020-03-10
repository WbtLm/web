package com.dao;

import com.entity.DBPatientAccount;

public interface PatientAccountDao {
    /*
    通过患者账号取id
     */
    int getIdByAccount(String account);
    /*
    添加患者账号
     */
    Integer insertPatientAccount(DBPatientAccount patientAccount);
}
