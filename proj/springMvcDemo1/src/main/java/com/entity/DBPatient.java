package com.entity;

import java.io.Serializable;

public class DBPatient implements Serializable {
    private int id;
    private String idNumber;
    private String healthCareType;
    private String name;
    private String sex;
    private int age;
    private String allergy; //π˝√Ù ∑

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getHealthCareType() {
        return healthCareType;
    }

    public void setHealthCareType(String healthCareType) {
        this.healthCareType = healthCareType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }
}
