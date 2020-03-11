package com.dao;

import com.entity.DBDoctorAccount;

public interface DoctorAccountDao {
    /*
    通过医师账号取id
     */
    int getIdByAccount(String account);
    /*
    添加医师账号
     */
    Integer insertDoctorAccount(DBDoctorAccount doctorAccount);
}
