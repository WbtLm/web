package com.dao;

import com.entity.DBPatient;
import com.entity.DBDoctor;

import java.util.List;

public interface PatientDao {
    /*
        ע�Ỽ��
     */
    Integer insertPatient(DBPatient patient);
    /*
        ���»�����Ϣ
     */
    Integer updatePatient(DBPatient patient);
    /*
        ��¼��ȡ����
     */
    String getPatientPassword(String account);
    /*
        ͨ������id��ȡ��Ϣ
     */
    DBPatient selectPatientById(int id);
    /*
        ��ȡҽ���б�
     */
    List<DBDoctor> getDoctorList();
}
