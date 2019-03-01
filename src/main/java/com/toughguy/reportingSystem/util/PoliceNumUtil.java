package com.toughguy.reportingSystem.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;

/**
 * 报警单号工具类
 * @author BOBO
 *
 */
public class PoliceNumUtil {
	/**
	 * 报警单号编码方法
	 * @param provinceId 省id
	 * @param cityId   市id
	 * @param districtId 区/县id
	 * @return
	 */
	public static String alarmNumber(int provinceId,int cityId,int districtId)  {
		String alarmNumber ="";
		alarmNumber += String.format("%02d", provinceId);
		alarmNumber += String.format("%02d", cityId);
		alarmNumber += String.format("%02d", districtId);
//		Calendar date = Calendar.getInstance();
//		String year = String.valueOf(date.get(Calendar.YEAR));
//		String month = String.valueOf(date.get(Calendar.MONTH));
//		String day = String.valueOf(date.get(Calendar.DATE));
//		String hour= String.valueOf(date.get(Calendar.HOUR));//小时
//        String minute=String.valueOf(date.get(Calendar.MINUTE));//分           
//        String second=String.valueOf(date.get(Calendar.SECOND));//秒  
//		
//        System.out.println(year);
//        System.out.println(month);
//        System.out.println(day);
//        System.out.println(hour);
//        System.out.println(minute);
//        System.out.println(second);
        
        String time=new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        System.out.println(time);
//        alarmNumber += String.format("%04d", year);
//        alarmNumber += String.format("%02d", month);
//        alarmNumber += String.format("%02d", day);
//        alarmNumber += String.format("%02d", hour);
//        alarmNumber += String.format("%02d", minute);
//        alarmNumber += String.format("%02d", second);
		
//        alarmNumber += String.valueOf(date.get(Calendar.YEAR));
//        alarmNumber += String.valueOf(date.get(Calendar.MONTH));
//        alarmNumber += String.valueOf(date.get(Calendar.DATE));
//        alarmNumber += String.valueOf(date.get(Calendar.HOUR));//小时
//        alarmNumber += String.valueOf(date.get(Calendar.MINUTE));//分        
//        alarmNumber += String.valueOf(date.get(Calendar.SECOND));//秒      
        int ranStr=(int)(Math.random()*900)+100; 
	    System.out.println(ranStr);
        alarmNumber += time+ranStr;
        System.out.println("alarmNumber" +  alarmNumber);
        return alarmNumber;
	}
}
