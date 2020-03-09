package com.back;

import com.alibaba.fastjson.JSONObject;

abstract public class UserBasicInfo implements Cloneable{
	private int id;
    private String IDCard;
    private String userName;
    private int age;
    private String tel;
    private String name;
    /**
     * �Ա� ��/Ů 1/2
     */
    private int sex;
    /**
     * ��ݣ�patient/doctor  1/2
     */ 
    private int capacity;
   
    public void deleteIDCard() {
    	IDCard=null;
    }
    public void deleteUserName() {
    	userName=null;
    }
    public void deleteAge() {
    	age=-1;
    }
    public void deleteTel(){
    	tel=null;
    }
    public void deleteName() {
    	name=null;
    }
    public void deleteSex() {
    	sex=0;
    }
    public void delteCapacity() {
    	capacity=0;
    }
    public String toJson() {
    	JSONObject json = new JSONObject();
    	json.put("id",id);
    	if(IDCard !=null)
    		json.put("IDCard",IDCard);
    	if(userName!=null)
    		json.put("userName",userName);
    	if(age!=-1)
    		json.put("age", age);
    	if(tel!=null)
    		json.put("tel", tel);
    	if(name!=null)
    		json.put("name", name);
    	if(isBoy()) {
    		json.put("sex", "boy");
    	}
    	else if(isGirl()){
			json.put("sex", "girl");
		}
    	if(isDoctor()) {
    		json.put("capacity", "doctor"	);
    	}
    	else if(isPatient()){
    		json.put("capacity", "patient");
    	}
		return json.toJSONString();
    	
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    protected void setDoctor() {
    	setCapacity(2);
    }
    protected void setPatient() {
    	setCapacity(1);
    }
    public boolean isDoctor() {
		return getCapacity()==2;
	}
    public boolean isPatient() {
    	return getCapacity()==1;
    }
    public void	setGirl() {
    	setSex(2);
    }
    public void setBoy(){
    	setSex(1);
    }
    public boolean isGirl() {
    	return getSex()==2;
    }
    public boolean isBoy() {
		return getSex()==1;
	}
    public int getId() {
    	return id;
    }
    public void setId(int id) {
    	this.id=id;
    }
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

    
}
