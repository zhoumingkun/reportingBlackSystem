package com.toughguy.reportingSystem.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * 对数字进行加密解密
 * @author BOBO
 *
 */
public class MyEncryptUtil {

	/**
	 * 数字 0 1 2 3 4 5 6 7 8 9
	 * 字母 c d z q w v s g b a
	 * @param str
	 * @return 数字转字母
	 */
	public static String getPhoneStr(String str) {
		String returnStr = "";
		if(str != null && str.length() > 0) {
			byte[] strs = str.getBytes();
			for(int i = 0; i < strs.length;i++) {
				returnStr += getLetter(String.valueOf(str.charAt(i)));
			}
		}
		return returnStr;
	}
	
	/**
	 * 字母转数字
	 * 字母 c d z q w v s g b a
	 * 数字 0 1 2 3 4 5 6 7 8 9
	 * @param str
	 * @return 字母转数字
 	 */
	public static String getPhoneNum(String str) {
		String returnStr = "";
		if(str != null && str.length() > 0) {
			if(str.substring(str.length()-1).equals("X") || str.substring(str.length()-1).equals("x")) {
				String newStr = str.substring(0,str.length()-1);
				byte[] strs = newStr.getBytes();
				for(int i = 0;i < strs.length; i++) {
					returnStr += getMapData().get(String.valueOf(newStr.charAt(i)));
				}
				returnStr += "X";
				return returnStr;
			} else {
				byte[] strs = str.getBytes();
				for(int i = 0;i < strs.length; i++) {
					returnStr += getMapData().get(String.valueOf(str.charAt(i)));
				}
				return returnStr;
			}
		} else {
			return returnStr;
		}
	}
	
	/**
	 * 换号码中两组数字；第三位和第九位交换，第五位和第八位交换，位数号按从左往右数
	 * 18035108304
	 * 18338105004
	 * @param str
	 * @return
	 */
	public static String changeNum(String val) {
		if(!MyEncryptUtil.isEmpty(val)){
			String str="";
			char [] phone=val.toCharArray();
			for(int i=0;i<phone.length;i++){
				if(i==2||i==4||i==7||i==8){
					if(i==2)
					    str+=phone[8];
					if(i==4)
						str+=phone[7];
					if(i==7)
						str+=phone[4];
					if(i==8)
						str+=phone[2];
				}else{
					str+=phone[i];
				}
			}
		    return str;
		}
		return val;

	}
	/**
	 * 通过Map值获取key
	 * @param str
	 * @return
	 */
	public static String getLetter(String str) {
		for(Entry<String, String> entry:getMapData().entrySet()) {
			if(str.equals(entry.getValue()))
				return String.valueOf(entry.getKey());
		}
		return str;
	}
	
	/**
	 * 字母 c d z q w v s g b a
	 * 数字 0 1 2 3 4 5 6 7 8 9
	 * @return Map
	 */
	public static Map<String, String> getMapData() {
		Map<String, String> map = new HashMap<String,String>();
		map.put("c","0");
		map.put("d","1");
		map.put("z","2");
		map.put("q","3");
		map.put("w","4");
		map.put("v","5");
		map.put("s","6");
		map.put("g","7");
		map.put("b","8");
		map.put("a","9");
		return map;
	}
	
	/**
	 * 字符串前增加“~”
	 * @param str
	 * @return
	 */
	public static String prefixStr(String str) {
		return "~" + str;
	}
	
	/**
	 * 过滤字符串前的“~”
	 * @param str
	 * @return
	 */
	public static String filterStr(String str) {
		if(str!=null && str.indexOf("~") > -1) {
			return str.substring(1, str.length());
		}
		if(str!=null && str.indexOf("%")> -1) {
			str = str.replace("%7E", "~").replace("%7e", "~");
			return str.substring(1,str.length());
		}
		return str;
	}
	
	/**
	 * 去除所有数字
	 * @param str
	 * @return
	 */
	public static String removeNum(String str) {
		return str.replaceAll("\\d+","");
	}
	
	/**
	 * 去除e k f j m
	 * @param str
	 * @return
	 */
	public static String removeLetter(String str) {
		String[] i = {"e","k","f","j","m"};
		String s = "";
		for(int a =0;a<i.length;a++) {
			if(s.equals("")) {
				s = str.replaceAll(i[a], "");
			} else {
				s = s.replaceAll(i[a], "");
				
			}
		}
		return s;
	}
	
	/**
	 * 随机插入3个数字
	 * @param str
	 * @return
	 */
	public static String insertNum(String str) {
		String num = radomNum(3);
		//制定长度字符串加入随机数字
		for(int i=0;i<num.length();i++) {
			Integer inNum = Integer.parseInt(radomNum(1));
			String result=str.substring(0,inNum>str.length()?str.length():inNum)+num.charAt(i)+str.substring(inNum>str.length()?str.length():inNum,str.length());
			str=result;
		}
		return str;
		
		
	}
	/**
	 * 随机插入e k f j m
	 * @param str
	 * @return
	 */
	public static String insertLetter(String str) {
		String[] letter = {"e","k","f","j","m"};
		//将其他字母放入加密中
		for(int i=0;i<letter.length;i++) {
			Integer inNum = Integer.parseInt(radomNum(1));
			String result=str.substring(0,inNum>str.length()?str.length():inNum)+letter[i]+str.substring(inNum>str.length()?str.length():inNum,str.length());
			str=result;
		}
		return str;
		
	}
	
	/**
	 * 随机生成i位数字
	 * @param i
	 * @return
	 */
	public static String radomNum(int i) {
		Random random = new Random();
		String str = String.valueOf(random.nextInt());
		return str.substring(0,i).indexOf("-")>-1?radomNum(i):str.substring(0,i);
	}
	
	/**
	 * 加密步骤
	 * 1、换号码中两组数字；第二位和第六位交换，第三位和第五位交换，位数号按从左往右数
	 * 2、将全部号码转换成对应的字符
	 * 3、任意位置插入三个随机数
	 * 4、在步骤C之后的字符串前加上“~”
	 * @param phoneStr
	 * @return
	 */
	public static String encryptPhone(String phoneStr) {
		//1.换位号码中两组数字：第二位和第六位交换，第三位和第五位交换
		phoneStr=changeNum(phoneStr);
		//2.将全部号码转换为对应的字符
		phoneStr=getPhoneStr(phoneStr);
		//3.任意位置插入三个随机数字
		phoneStr=insertNum(phoneStr);
		//4.任意位置插入e k f j m
		phoneStr=insertLetter(phoneStr);
		//4.在步骤C之后的字符串前加上”~”
		phoneStr=prefixStr(phoneStr);
		return phoneStr;
	}
	
	/**
	 * 解密步骤
	 * 1、去除字符串前“~”
	 * 2、去除所有数字
	 * 3、将剩余字母全部转换为对应的数字
	 * 4、换号码中两组数字；第二位和第六位交换，第三位和第五位交换，位数号按从左往右数
	 * @param phoneStr
	 * @return
	 */
	public static String decryptPhone(String phoneStr) {
		if(!MyEncryptUtil.isNumber(phoneStr)){
			//1.去除字符串前”~”
			phoneStr=filterStr(phoneStr);
			//2.去除所有数字
			phoneStr=removeNum(phoneStr);
			//3.将所有e k f j m
			phoneStr=removeLetter(phoneStr);
			//3.将剩余字母全部转换为对应的数字
			phoneStr=getPhoneNum(phoneStr);
			//4.换位号码中两组数字：第二位和第六位交换，第三位和第五位交换
			phoneStr=changeNum(phoneStr);
		}
		return phoneStr;
	}
	/**
     * 正则表达式数字验证
     * 
     * @author crab
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str != null && !str.equals("")) {
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]*");
            java.util.regex.Matcher match = pattern.matcher(str);
            return match.matches();
        } else {
            return false;
        }
    }
 
    /**
     * 字符串非空非null判断 crab
     */
    public static boolean isEmpty(String val) {
        if (val == null || val.equals("") || val.equalsIgnoreCase("null")) {
            return true;
        } else {
            return false;
        }
    }
}
