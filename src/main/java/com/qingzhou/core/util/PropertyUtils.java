package com.qingzhou.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.qingzhou.core.exception.AppException;


/**
 * 属性文件读取工具
 * @author DC
 * @
 */
public class PropertyUtils {

	public PropertyUtils(String fileName)
	{
		this.fileName=fileName;
		PropertyUtils.loadProperty(this.fileName);
	}
	private String fileName;
	
	private static Properties config = new Properties();
	
	public static void loadProperty(String fileName)
	{
		InputStream in = null;
		in =PropertyUtils.class.getClassLoader().getResourceAsStream(fileName);
		try {
			config.load(in);
			config.list(System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String name) {
		String value = null;
		try {
			value = config.getProperty(name);
		} catch (Exception e) {
			AppException.getException("4515", e);
		}
		return value;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyUtils error=new PropertyUtils("error1.properties");
		error.config.list(System.out);
		try {
			System.out.println(new String(PropertyUtils.getProperty("error.4000").getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
