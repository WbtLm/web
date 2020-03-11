package com.back.info;

import com.alibaba.fastjson.JSONObject;

public class DoctorInfo extends UserBasicInfo{
    private int departmentID;
    private String title;
    public DoctorInfo() {
		// TODO Auto-generated constructor stub
    	super.setDoctor();
	}
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    
	public void deleteDepartmemtID() {
		departmentID=-1;
	}
	public void deleteTitle() {
		title=null;
	}
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public String toJson() {
		JSONObject jsonObject = JSONObject.parseObject(super.toJson());
		if(departmentID!=-1) {
			jsonObject.put("departmentID", departmentID);
		}
		if(title!=null) {
			jsonObject.put("title", title);
		}
		
		return jsonObject.toJSONString();
	}
}
