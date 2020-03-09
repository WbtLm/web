package com.test;

import com.dao.PatientDao;
import com.entity.DBPatient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientDaoTest extends BaseTest {
    @Autowired
    private PatientDao patientDao;

    @Test
    public void TestSelectById() throws Exception {
        DBPatient patient = patientDao.selectPatientById(1);
        System.out.println(patient.getSex());
    }
}