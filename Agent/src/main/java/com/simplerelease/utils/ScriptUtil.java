package com.simplerelease.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ScriptUtil.class);
	public static String execScript(List<String> params,String dir)
	{
		
            String result="";
            StringBuilder sb=new StringBuilder();
            try
            {
           
		    ProcessBuilder processBuilder = new ProcessBuilder(params);
		    if(dir!=null&&!dir.isEmpty())
		    {
		    processBuilder.directory(new File(dir));
		    }
		    Process process = processBuilder.start();
		    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
		    String line;
		    while ((line = br.readLine()) != null) {
		        //System.out.println(line);
		        sb.append(line);
		    }
		    
		    int exitCode=process.waitFor();
		    logger.info("run script exitcode："+exitCode);
            }
            catch(Exception ex)
            {
              logger.error("exec script error",ex);
            }
            
            result=sb.toString();
            
            logger.info("exec script result: "+result);
            
            return result;
		
		
	}
	
	
	public static String execScript(String script)
	{
		
		//
		 String result="";
         StringBuilder sb=new StringBuilder();
		 try {
	            Process process = Runtime.getRuntime().exec(script);
	            InputStream is = process.getInputStream();
	            BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
	            String line;
	            while ((line = br.readLine()) != null) {
	            	sb.append(line);
	            }

	           int exitCode = process.waitFor();
	           logger.info("exec scriptexitCode: "+exitCode);
	           } catch (Exception e) {
	        	  logger.error("exec script error",e);
	           }
            
            result=sb.toString();
            
            logger.info("exec script result: "+result);
            
            return result;
		
		
	}
	
	

}
