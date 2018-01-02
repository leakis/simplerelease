package com.simplerelease.netty;

import com.simplerelease.utils.EncryptUtils;

public class ReqData {
	
   private String data;
   private String md5Str;
	
   public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
		//
		 String md5=EncryptUtils.md5Hash(data);
		 setMd5Str(md5);
		//
	}
	public String getMd5Str() {
		return md5Str;
	}
	
	public String setMd5Str(String data) {
	     this.md5Str=data;
		return md5Str;
	}


}
