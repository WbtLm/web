package com.dao;

public interface CheckupTypeDao {
    /*
    通过id获取类型名
     */
    String getCheckupTypeNameById(int id);
    /*
    通过类型名获取id
     */
    int getCheckupTypeIdByName(String typeName);
}
