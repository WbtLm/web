package com.ctrl;

import java.util.*;

public class SessionCtrl {
	HashMap<Integer, String> map=new HashMap<>();
	
	public String getSIDbyUID(int UID,int type) {
		if(map.containsKey(UID)) {
			return map.get(UID);
		}
		return encode(UID, type);
	}
	public int getUIDbySID(String SID) {
		return Integer.parseInt(SID.substring(20));
	}
	public int getTypebySID(String SID) {
		return (int)SID.indexOf(0);
	}
	public String getSIDbyLogin(int UID,int type) {
		if(map.containsKey(UID)) {
			return map.get(UID);
		}
		return encode(UID, type);
	}
	private String encode(int UID,int type) {
		String code=new String();
		Random r=new Random();
		int a;
		code+=(char)type;
		for(int i=0;i<20;i++) {
			a=r.nextInt(126-48+1)+48;
			code+=(char)a;
		}
		code+=new Integer(UID).toString();
		map.put(UID, code);
		return code;
	}
}
