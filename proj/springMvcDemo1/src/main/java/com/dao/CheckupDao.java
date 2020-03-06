package com.dao;
import com.entity.DBAppointCheckUp;

public interface CheckupDao {
   /*
    * 预约体检
    */
   Integer insertCheckup(DBAppointCheckUp checkup);
   /*
    * 查询体检信息
    */
   DBAppointCheckUp selectCheckupById(int id);
   
}
