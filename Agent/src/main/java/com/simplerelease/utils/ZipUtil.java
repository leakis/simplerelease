package com.simplerelease.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ZipUtil {

	private static  Logger logger = LoggerFactory.getLogger(ZipUtil.class);

    
    public static void zip(String zipFileName,String sourceFileName) 
    {
        //File zipFile = new File(zipFileName);
        
        logger.info("压缩中...");
        
        try
        {
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream( new FileOutputStream(zipFileName));
        
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        
        File sourceFile = new File(sourceFileName);
        
        //调用函数
        compress(out,bos,sourceFile,sourceFile.getName());
        
        bos.close();
        out.close();
        logger.info("压缩完成");
        }
        catch(Exception ex)
        {
        	logger.error("zip error ",ex);
        }
        
    }
    
    private static void compress(ZipOutputStream out,BufferedOutputStream bos,File sourceFile,String base)
    {
    	try
    	{
        //如果路径为目录（文件夹）
        if(sourceFile.isDirectory())
        {
        
            //取出文件夹中的文件（或子文件夹）
            File[] flist = sourceFile.listFiles();
            
            if(flist.length==0)//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入点
            {
            	logger.info(base+File.separator);
                out.putNextEntry(  new ZipEntry(base+File.separator) );
            }
            else//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for(int i=0;i<flist.length;i++)
                {
                    compress(out,bos,flist[i],base+File.separator+flist[i].getName());
                }
            }
        }
        else//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
        {
            out.putNextEntry( new ZipEntry(base) );
            FileInputStream fos = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fos);
            
            int tag;
            logger.info(base);
            //将源文件写入到zip文件中
            while((tag=bis.read())!=-1)
            {
                bos.write(tag);
            }
            bis.close();
            fos.close();
            
        }

        
    }
    catch(Exception ex)
    {
       logger.error("compress error",ex); 	
    }
    	
    }
    
    
    
    public static void unZip(String sourceFile, String descDir) {  
    	try
    	{
        File zipFile=new File(sourceFile);
        ZipFile  zip=new ZipFile(zipFile,Charset.forName("GBK"));//解决中文文件夹乱码  
       // String name = zip.getName().substring(zip.getName().lastIndexOf(File.separator)+1, zip.getName().lastIndexOf('.'));  
          
        File pathFile = new File(descDir);  
        if (!pathFile.exists()) {  
            pathFile.mkdirs();  
        }  
          
        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {  
            ZipEntry entry = (ZipEntry) entries.nextElement();  
            String zipEntryName = entry.getName();  
            InputStream in = zip.getInputStream(entry);  
            //String outPath = (descDir + name +File.separator+ zipEntryName).replaceAll("\\*", "/");  
            String outPath = (descDir +File.separator+ zipEntryName);  
            logger.info(outPath);
            // 判断路径是否存在,不存在则创建文件路径  
            File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));  
            if (!file.exists()) {  
                file.mkdirs();  
            }  
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压  
            if (new File(outPath).isDirectory()) {  
                continue;  
            }  
            // 输出文件路径信息  
//          System.out.println(outPath);  
  
            FileOutputStream out = new FileOutputStream(outPath);  
            byte[] buf1 = new byte[1024];  
            int len;  
            while ((len = in.read(buf1)) > 0) {  
                out.write(buf1, 0, len);  
            }  
            in.close();  
            out.close();  
        }  
        
        zip.close();
        logger.info("******************解压完毕********************");  
       
    	}
    	catch(Exception ex)
    	{
    		logger.error("unzip error",ex);
    	}

    	
    	
    }  
	
	
}
