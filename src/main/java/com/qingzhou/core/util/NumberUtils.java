package com.qingzhou.core.util;

public class NumberUtils {
	
	/**
	 * 加法运算
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static String addition(String param1,String param2)
	{
		return addition(param1 + "," + param2);
	}
	/**
	 * 加法运算，已逗号分隔
	 * @param param
	 * @return
	 */
	public static String addition(String param)
	{
		String[] params = numDefault(param).split(",");
		double total = 0;
		for(int i=0;i<params.length;i++)
		{
			total = total + Double.parseDouble(params[i]);
		}
		return Double.toString(total);
	}
	
	/**
	 * 如为空，默认为0
	 * @param param
	 * @return
	 */
	public static String numDefault(String param)
	{
		if (LogicUtils.isEmpty(param))
			return "0";
		else 
			return param;
	}
	
	public static void main(String[] args)
    {
		System.out.println(NumberUtils.addition("500,1000.01"));
    }

}
