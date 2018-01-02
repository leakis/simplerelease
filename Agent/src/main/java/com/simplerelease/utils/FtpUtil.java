package com.simplerelease.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simplerelease.agent.Main;

public class FtpUtil {

	private  Logger logger = LoggerFactory.getLogger(getClass());
	private String username;  
	private String pwd;  
	private String host;  
	private int port = 21; 
	private  FileOutputStream fos =null;
	private FTPClient ftp = new FTPClient();  
	
	
	public FtpUtil(String host,int port, String username,String pwd)
	{
		this.host=host;
		this.username=username;
		this.pwd=pwd;
		if(port>0)
		{
		this.port=port;
		}
	}
	
	

	public  boolean uploadFile(String remotepath,String localpath, String filename) { 
	    boolean success = false; 
	  
	    try { 
	    	connect();
	    	 logger.info("upload file"+filename);
	        ftp.changeWorkingDirectory(remotepath); 
	        String fullpath=localpath+File.separator+filename;
	        FileInputStream input=new FileInputStream(new File(fullpath)); 
	        ftp.storeFile(filename, input);          
	         
	        input.close(); 
	       
	        success = true; 
	    } catch (IOException e) { 
	    	 logger.error("upload file"+filename);
	        e.printStackTrace(); 
	    } finally { 
	       
	    	close(null);
	    } 
	    return success; 
	}
	
	
	
	
	public  boolean downFile( String remotePath,String localPath,String fileName) { 
	    boolean success = false; 
	    try { 
	       connect();
	       logger.info("down file"+fileName);
	       ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录 
	       File localfile = new File(localPath + File.separator  + fileName);  
	       fos = new FileOutputStream(localfile);  
	       ftp.retrieveFile(fileName, fos);  
	       success = true; 
	    } catch (IOException e) { 
	    	logger.error("down file error",e);
	        e.printStackTrace(); 
	    } finally { 
	    	close(fos);
	    } 
	    return success; 
	}
	
	
	
	
	private  boolean connect() throws SocketException, IOException
	{
		    boolean suc=true;
		    int reply; 
		    ftp.connect(host, port);//连接FTP服务器 
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        ftp.login(username, pwd);//登录 
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	            ftp.disconnect(); 
	            return false; 
	        } 
	        logger.info("open connect");
	        return suc;
	}
	
	
	/** 
	* 关闭输入输出流 
	*  
	* @param fos 
	*/  
	private void close(FileOutputStream fos) {  
	try {  
	if (fos != null) {  
	fos.close();  
	}  

	ftp.logout();  
	logger.info("退出登录");  
	 if (ftp.isConnected()) { 
	ftp.disconnect();
	 }
	logger.info("关闭连接");  
	} catch (IOException e) {  
	logger.error("", e);  
	}  
	}  
	
	
	
	
	
}
