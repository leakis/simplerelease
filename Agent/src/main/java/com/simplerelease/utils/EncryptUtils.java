package com.simplerelease.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	 /**
     * 获取MD5加密
     * 
     * @param pwd
     *            需要加密的字符串
     * @return String字符串 加密后的字符串
     */
    public static String md5Hash(String str) {
        try {
            // 创建加密对象
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs = digest.digest(str.getBytes());
            String hexString = "";
            for (byte b : bs) {
                int temp = b & 255;
                if (temp < 16 && temp >= 0) {
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
    
    public static String md5Hash(File file)
    {
    	String md5="";
    	 try {
             
             FileInputStream fis = new FileInputStream(file);
             MessageDigest md = MessageDigest.getInstance("MD5");
             byte[] buffer = new byte[1024];
             int length = -1;
             while ((length = fis.read(buffer, 0, 1024)) != -1) {
                 md.update(buffer, 0, length);
             }
             BigInteger bigInt = new BigInteger(1, md.digest());
             fis.close();
             md5=bigInt.toString(16);
             //System.out.println("文件md5值：" +md5);
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    	 
    	 return md5;
    }
	
}
