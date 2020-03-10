package com.back.info;

import com.alibaba.fastjson.JSONObject;

public class PatientInfo extends UserBasicInfo {
	private String healthCareType;
	private String allergy;
	public PatientInfo() {
		// TODO Auto-generated constructor stub
		super.setPatient();
	}
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public String toJson() {
		JSONObject jsonObject = JSONObject.parseObject(super.toJson());
		if(healthCareType!=null) {
			jsonObject.put("healthCareType", healthCareType);
		}
		if(allergy!=null) {
			jsonObject.put("allergy", allergy);
		}
		
		return jsonObject.toJSONString();
	}
	public void deleteHealthCareType() {
		healthCareType=null;
	}
	public void  deleteAllergy() {
		allergy=null;
	}
	public String getHealthCareType() {
		return healthCareType;
	}

	public void setHealthCareType(String healthCareType) {
		this.healthCareType = healthCareType;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	
}
