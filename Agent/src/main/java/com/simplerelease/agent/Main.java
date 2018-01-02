package com.simplerelease.agent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.simplerelease.netty.ReqData;
import com.simplerelease.utils.EncryptUtils;
import com.simplerelease.utils.FtpUtil;
import com.simplerelease.utils.ScriptUtil;
import com.simplerelease.utils.ZipUtil;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     logger.info("test");
    
     //ReqData data=new ReqData();
     //data.setData("123456");
     //logger.info(data.getMd5Str());
    //Gson gson=new Gson();
    //String json= gson.toJson(data);
    // FtpUtil ftp=new FtpUtil("127.0.0.1",21,"test","123456");
     //ftp.uploadFile("/", "E:\\remote", "index2.html");
     //ftp.downFile("/", "E:\\remote\\a", "index2.html");
     //logger.info(json);
     
     //ZipUtil.zip("E:\\导流平台订单接口.zip","E:\\remote\\导流平台订单接口.txt");
     //File file=new File("E:\\\\导流平台订单接口.zip");
     //String md5=EncryptUtils.md5Hash(file);
     //logger.info(md5);
     List<String> scripts=new ArrayList<String>();
     scripts.add("python");
     scripts.add("test2.py");
    // String result= ScriptUtil.execScript(scripts,"E:\\mytest\\py");
     String result= ScriptUtil.execScript("python E:\\mytest\\py\\test2.py");
     logger.info(result);
	}

}
